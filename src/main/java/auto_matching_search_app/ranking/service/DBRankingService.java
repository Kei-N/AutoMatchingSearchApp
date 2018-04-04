package auto_matching_search_app.ranking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import auto_matching_search_app.home.domain.MatchingInfo;
import auto_matching_search_app.ranking.domain.RankingInfo;
import auto_matching_search_app.ranking.persistence.RankingMapper;

/**
 * マッチング結果の関心度テーブルの一覧表示(上位10件)
 * DB関連のサービスクラス (DB関連のビジネスロジックを記述)
 * @author K.Nomoto
 */
@Service
public class DBRankingService {

	/**
	 * DI(Dependency Injection)
	 * CourseInfoMapperクラスの自動インスタンス化
	 */
    @Autowired
    private RankingMapper rankingMapper;
    
    /**
     * DBからランキング一覧取得処理メソッド
     * @param keywordInfo キーワード情報
     */
    public List<RankingInfo> getRankingList(Integer userInfoId) {
    	
    	// ランキング情報リスト取得
    	List<RankingInfo> rankingList = rankingMapper.selectRankingList(userInfoId);
    	
    	// キーワードIDに紐づくキーワードを格納
    	for(int i = 0; i < rankingList.size(); i++){
    		RankingInfo rankingInfo = rankingList.get(i);
    		rankingInfo.setRanking(i + 1);
    		rankingInfo.setKeyword1(rankingMapper.selectKeyword(rankingInfo.getKeywordId1()));
    		rankingInfo.setKeyword2(rankingMapper.selectKeyword(rankingInfo.getKeywordId2()));
    	}
        
        // キーワード情報リストを返却
        return rankingList;
    }
    
    /**
     * DBからマッチングキーワードペア取得処理メソッド
     * @param matchingScoreId マッチング関心度ID
     */
    public MatchingInfo getMatchingKeywords(Integer matchingScoreId) {
    	
    	// キーワードID1,2格納
    	MatchingInfo matchingInfo = rankingMapper.selectMatchingKeywordId(matchingScoreId);
    	// キーワード1,1格納
    	matchingInfo.setKeyword1(rankingMapper.selectKeyword(matchingInfo.getKeywordId1()));
    	matchingInfo.setKeyword2(rankingMapper.selectKeyword(matchingInfo.getKeywordId2()));
    	
        // キーワード情報リストを返却
        return matchingInfo;
    }
    
    /**
     * マッチングキーワードペアの存在チェック処理メソッド
     * @param keyword キーワード
     * @return false(存在していない) or true(存在している)
     */
    public boolean isDuplicateMatchingKeywords(MatchingInfo matchingInfo) {
        int count = rankingMapper.getCountByMatcningKeywords(matchingInfo);

         return count == 0 ? false : true;
    }
    
    /**
     * matching_scoreテーブルへのマッチング関心度1点加算処理
     * @param userInfoId
     * @param keywordId1
     * @param keywordId2
     */
    @Transactional
    public void addMatchingScore1Point(MatchingInfo matchingInfo){
    	
    	// マッチングキーワードペアの存在チェック
    	if(isDuplicateMatchingKeywords(matchingInfo)){
    	
    	// 存在している
    		rankingMapper.updateMatchingScore1Point(matchingInfo);
    		
    	} else {
    	// 存在していない
    		rankingMapper.insertMatchingScore1Point(matchingInfo);
    	}
    }
    
    /**
     * matching_scoreテーブルへのマッチング関心度3点加算処理
     * @param userInfoId
     * @param keywordId1
     * @param keywordId2
     */
    @Transactional
    public void addMatchingScore3Point(MatchingInfo matchingInfo){
    	
    	// マッチングキーワードペアの存在チェック
    	if(isDuplicateMatchingKeywords(matchingInfo)){
    	
    	// 存在している
    		rankingMapper.updateMatchingScore3Point(matchingInfo);
    		
    	} else {
    	// 存在していない
        	rankingMapper.insertMatchingScore3Point(matchingInfo);
    	}
    }
	
}