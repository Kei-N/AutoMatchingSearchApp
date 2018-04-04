package auto_matching_search_app.bookmarks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import auto_matching_search_app.bookmarks.domain.BookmarkInfo;
import auto_matching_search_app.bookmarks.form.BookmarkForm;
import auto_matching_search_app.bookmarks.persistence.BookmarkMapper;

/**
 * ブックマークテーブルに登録・削除を行う
 * DB関連のサービスクラス (DB関連のビジネスロジックを記述)
 * @author K.Nomoto
 */
@Service
public class DBBookmarksService {

	/**
	 * DI(Dependency Injection)
	 * CourseInfoMapperクラスの自動インスタンス化
	 */
    @Autowired
    private BookmarkMapper bookmarkMapper;
	
    
    /**
     * DBからブックマーク一覧取得処理メソッド
     * @param keywordInfo キーワード情報
     */
    public List<BookmarkInfo> getBookmarkList(Integer userInfoId) {
    	
    	// ブックマーク情報リスト取得
    	List<BookmarkInfo> bookmarkList = bookmarkMapper.selectBookmarkList(userInfoId);
        
        // ブックマーク情報リストを返却
        return bookmarkList;
    }
	
    /**
     * ブックマークの重複チェック処理メソッド
     * @param keyword キーワード
     * @return false(重複していない) or true(重複している)
     */
    public boolean isDuplicateBookmark(BookmarkForm form) {
        int count = bookmarkMapper.getCountByBookmark(form);

         return count == 0 ? false : true;
    }
    
    /**
     * ブックマーク登録エラーチェックメソッド
     * @param form キーワード情報(KeywordFormのインスタンス)
     * @param result エラー情報(BindingResult)
     * @return result.hasErrors() 入力エラー:あり(true),なし(false)
     */
    public boolean isErrorBookmark(BookmarkForm form, BindingResult result){
    	
        // キーワードの重複チェック
        if(isDuplicateBookmark(form)) {
        	// エラーメッセージの設定
            result.reject("errors.duplicate.bookmark");
        }
        
		return result.hasErrors();
    }
    
    /**
	 * ブックマークテーブル登録メソッド
	 * @param form ブックマーク登録のリクエスト情報
	 */
	@Transactional
	public void insert(BookmarkForm form){
		
		// ブックマークテーブル挿入
		bookmarkMapper.insertBookmark(form);
	}
    
	/**
	 * ブックマークテーブル削除メソッド
	 * @param form ブックマーク削除のリクエスト情報
	 */
	@Transactional
	public void delete(BookmarkInfo bookmarkInfo){
		
		// ブックマークテーブル削除
		bookmarkMapper.deleteBookmarks(bookmarkInfo.getBookmarkId());
	}
	
}