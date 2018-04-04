package auto_matching_search_app.ranking.persistence;

import java.util.List;

import auto_matching_search_app.home.domain.MatchingInfo;
import auto_matching_search_app.ranking.domain.RankingInfo;

/**
 * データベースへのアクセスに使うMyBatisのマッパーインターフェース
 * @author K.Nomoto
 */
public interface RankingMapper {
	
	/**
	 * ランキング(上位10件)取得メソッドのインターフェース
	 * @param form
	 */
	public List<RankingInfo> selectRankingList(Integer userInfoId);
	
	/**
	 * keyword取得メソッドのインターフェース
	 * @param keyword_id
	 * @return String
	 */
	public String selectKeyword(Integer keywordId);
	
	/**
	 * keywordId取得メソッドのインターフェース
	 * @param matchingScoreId
	 * @return RankingInfo
	 */
	public MatchingInfo selectMatchingKeywordId(Integer matchingScoreId);
	
	/**
	 * マッチングキーワード存在チェック
	 * (特定のuser_info_idにおいて)
	 * @param userInfoId
	 * @param keywordId1
	 * @param keywordId2
	 * @return
	 */
	public int getCountByMatcningKeywords(MatchingInfo matchingInfo);
	
	/**
	 * マッチングスコア登録URLクリック時1点加算
	 * 新規キーワードペア （特定のuser_info_idにおいて）
	 * @param userInfoId
	 * @param keywordId1
	 * @param keywordId2
	 * @return
	 */
	public void insertMatchingScore1Point(MatchingInfo matchingInfo);
	
	/**
	 * マッチングスコア登録ブックマーク保存時3点加算
	 * 新規キーワードペア (特定のuser_info_idにおいて)
	 * @param userInfoId
	 * @param keywordId1
	 * @param keywordId2
	 */
	public void insertMatchingScore3Point(MatchingInfo matchingInfo);
	
	/**
	 * マッチングスコア更新URLクリック時1点加算
	 * 既存キーワードペア (特定のuser_info_idにおいて)
	 * @param userInfoId
	 * @param keywordId1
	 * @param keywordId2
	 */
	public void updateMatchingScore1Point(MatchingInfo matchingInfo);
	
	/**
	 * マッチングスコア更新ブックマーク保存時3点加算
	 * 既存キーワードペア (特定のuser_info_idにおいて)
	 * @param userInfoId
	 * @param keywordId1
	 * @param keywordId2
	 */
	public void updateMatchingScore3Point(MatchingInfo matchingInfo);
    
}
