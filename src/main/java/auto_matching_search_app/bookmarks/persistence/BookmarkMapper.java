package auto_matching_search_app.bookmarks.persistence;

import java.util.List;

import auto_matching_search_app.bookmarks.domain.BookmarkInfo;
import auto_matching_search_app.bookmarks.form.BookmarkForm;

/**
 * データベースへのアクセスに使うMyBatisのマッパーインターフェース
 * @author K.Nomoto
 */
public interface BookmarkMapper {
	
	/**
	 * bookmarkの重複チェックメソッドのインターフェース
	 * @param keyword
	 */
	public int getCountByBookmark(BookmarkForm form);
	
	/**
	 * ブックマークテーブル登録メソッドのインターフェース
	 * @param form
	 */
	public void insertBookmark(BookmarkForm form);
	
	/**
	 * ブックマーク一覧取得メソッドのインターフェース
	 * @param form
	 */
	public List<BookmarkInfo> selectBookmarkList(Integer userInfoId);
	
	/**
	 * ブックマークテーブル削除メソッドのインターフェース
	 * @param form
	 */
	public void deleteBookmarks(Integer bookmarkId);
	
}
