package auto_matching_search_app.home.domain;

/**
 * マッチング結果取得用Bean
 * domainパッケージ：情報のかたまりとなるクラス
 * @author K.Nomoto
 */
public class MatchingInfo {

	// フィールド ***********************************
	/**
	 * ユーザー情報ID
	 */
	private Integer userInfoId;
	/**
	 * ランダムで取ってくるキーワード一つ目(keyword1)
	 */
	private String keyword1;
	/**
	 * ランダムで取ってくるキーワード二つ目(keyword2)
	 */
	private String keyword2;
	/**
	 * keyword1のkeyword_id
	 */
	private Integer keywordId1;
	/**
	 * keyword2のkeyword_id
	 */
	private Integer keywordId2;
	
	// getter,setter ****************************
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
	 * @return keyword1
	 */
	public String getKeyword1() {
		return keyword1;
	}
	/**
	 * @param keyword1をセットする
	 */
	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}
	/**
	 * @return keyword2
	 */
	public String getKeyword2() {
		return keyword2;
	}
	/**
	 * @param keyword2をセットする
	 */
	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}
	/**
	 * @return keywordId1
	 */
	public Integer getKeywordId1() {
		return keywordId1;
	}
	/**
	 * @param keywordId1をセットする
	 */
	public void setKeywordId1(Integer keywordId1) {
		this.keywordId1 = keywordId1;
	}
	/**
	 * @return keywordId2
	 */
	public Integer getKeywordId2() {
		return keywordId2;
	}
	/**
	 * @param keywordId2をセットする
	 */
	public void setKeywordId2(Integer keywordId2) {
		this.keywordId2 = keywordId2;
	}
}