package auto_matching_search_app.keywords.domain;

/**
 * キーワード情報Bean
 * domainパッケージ：情報のかたまりとなるクラス
 * @author K.Nomoto
 */
public class KeywordInfo {

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
