package auto_matching_search_app.home.persistence;

import java.util.List;

import auto_matching_search_app.keywords.domain.KeywordInfo;
import auto_matching_search_app.keywords.form.KeywordForm;

/**
 * データベースへのアクセスに使うMyBatisのマッパーインターフェース
 * @author K.Nomoto
 */
public interface CreateMapper {
	
	/**
	 * keywordをランダムに2件取得するインターフェース
	 * @param userInfoId
	 */
	public List<KeywordInfo> selectTwoKeywordsRandomly(Integer userInfoId);
	
	/**
	 * キーワードテーブル登録メソッドのインターフェース
	 * @param form
	 */
	public void insertKeyword(KeywordForm form);
	
	/**
	 * キーワード一覧取得メソッドのインターフェース
	 * @param form
	 */
	public List<KeywordInfo> selectKeywordList(Integer userInfoId);
	
	/**
	 * マッチング結果関心度テーブル削除メソッドのインターフェース
	 * @param form
	 */
	public void deleteMatchingScore(Integer keywordId);
	
	/**
	 * キーワードテーブル削除メソッドのインターフェース
	 * @param form
	 */
	public void deleteKeywords(Integer keywordId);
	
}
