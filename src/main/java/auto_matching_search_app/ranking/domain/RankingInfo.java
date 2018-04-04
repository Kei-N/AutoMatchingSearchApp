package auto_matching_search_app.ranking.domain;

/**
 * ランキングBean
 * domainパッケージ：情報のかたまりとなるクラス
 * @author K.Nomoto
 */
public class RankingInfo {

	// フィールド ********************************************
	/**
	 * ランキング順位
	 */
	private Integer ranking;
	/**
	 * マッチング関心度ID
	 */
	private Integer matchingScoreId;
	/**
	 * キーワードID1
	 */
	private Integer keywordId1;
	/**
	 * キーワードID2
	 */
	private Integer keywordId2;
	/**
	 * 関心度（点数）
	 * URLクリック1点、ブックマーク保存3点
	 */
	private Long score;
	/**
	 * キーワード1
	 */
	private String keyword1;
	/**
	 * キーワード2
	 */
	private String keyword2;
	
	// getter,setter *************************************
	/**
	 * @return ranking
	 */
	public Integer getRanking() {
		return ranking;
	}
	/**
	 * @param rankingをセットする
	 */
	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}
	/**
	 * @return matchingScoreId
	 */
	public Integer getMatchingScoreId() {
		return matchingScoreId;
	}
	/**
	 * @param matchingScoreIdをセットする
	 */
	public void setMatchingScoreId(Integer matchingScoreId) {
		this.matchingScoreId = matchingScoreId;
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
	/**
	 * @return score
	 */
	public Long getScore() {
		return score;
	}
	/**
	 * @param scoreをセットする
	 */
	public void setScore(Long score) {
		this.score = score;
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
	
}
