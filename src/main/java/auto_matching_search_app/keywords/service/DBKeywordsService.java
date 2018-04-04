package auto_matching_search_app.keywords.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import auto_matching_search_app.keywords.domain.KeywordInfo;
import auto_matching_search_app.keywords.form.KeywordForm;
import auto_matching_search_app.keywords.persistence.KeywordMapper;

/**
 * キーワードテーブルに登録・削除を行う
 * DB関連のサービスクラス (DB関連のビジネスロジックを記述)
 * @author K.Nomoto
 */
@Service
public class DBKeywordsService {

	/**
	 * DI(Dependency Injection)
	 * KeywordMapperクラスの自動インスタンス化
	 */
    @Autowired
    private KeywordMapper keywordMapper;
	
    
    /**
     * DBからキーワード一覧取得処理メソッド
     * @param keywordInfo キーワード情報
     */
    public List<KeywordInfo> getKeywordList(Integer userInfoId) {
    	
    	// キーワード情報リスト取得
    	List<KeywordInfo> keywordList = keywordMapper.selectKeywordList(userInfoId);
        
        // キーワード情報リストを返却
        return keywordList;
    }
	
    /**
     * キーワードの重複チェック処理メソッド
     * @param keyword キーワード
     * @return false(重複していない) or true(重複している)
     */
    public boolean isDuplicateKeyword(String keyword) {
        int count = keywordMapper.getCountByKeyword(keyword);

         return count == 0 ? false : true;
    }
    
    /**
     * キーワード登録エラーチェックメソッド
     * @param form キーワード情報(KeywordFormのインスタンス)
     * @param result エラー情報(BindingResult)
     * @return result.hasErrors() 入力エラー:あり(true),なし(false)
     */
    public boolean isErrorKeyword(KeywordForm form, BindingResult result){
    	
    	// キーワードの必須入力チェック
        if(form.getKeyword().equals("")){
        	// エラーメッセージの設定
        	result.rejectValue("keyword", "errors.required.keyword");
        
        // キーワードの重複チェック
        } else if(isDuplicateKeyword(form.getKeyword())) {
        	// エラーメッセージの設定
            result.rejectValue("keyword", "errors.duplicate.keyword");
        }
        
		return result.hasErrors();
    }
    
    /**
	 * キーワードテーブル登録メソッド
	 * @param form キーワード登録のリクエスト情報
	 */
	@Transactional
	public void insert(KeywordForm form){
		
		// キーワードテーブル挿入
		keywordMapper.insertKeyword(form);
	}
    
	/**
	 * マッチング結果関心度テーブル、キーワードテーブル削除メソッド
	 * @param form キーワード削除のリクエスト情報
	 */
	@Transactional
	public void delete(KeywordInfo keywordInfo){
		
		// マッチング結果関心度テーブル削除
		keywordMapper.deleteMatchingScore(keywordInfo.getKeywordId());
		
		// キーワードテーブル削除
		keywordMapper.deleteKeywords(keywordInfo.getKeywordId());
	}
	
}