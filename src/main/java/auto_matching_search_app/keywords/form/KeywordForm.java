package auto_matching_search_app.keywords.form;

/**
 * キーワード情報フォーム
 * Formクラス　 リクエスト情報を格納する
 * @author K.Nomoto
 */
public class KeywordForm {

	// フィールド ********************************************
	/**
	 * ユーザー情報ID
	 */
	private Integer userInfoId;
	/**
	 * キーワードID
	 */
	private Integer keywordId;
	/**
	 * キーワード（興味関心のあるワード）
	 */
	private String keyword;
	
	// getter,setter *************************************
	/**
	 * @return userInfoId
	 */
	public Integer getUserInfoId() {
		return userInfoId;
	}
	/**
	 * @param userInfoIdをセットする
	 */
	public void setUserInfoId(Integer userInfoId) {
		this.userInfoId = userInfoId;
	}
	/**
	 * @return keywordId
	 */
	public Integer getKeywordId() {
		return keywordId;
	}
	/**
	 * @param keywordIdをセットする
	 */
	public void setKeywordId(Integer keywordId) {
		this.keywordId = keywordId;
	}
	/**
	 * @return keyword
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * @param keywordをセットする
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
