package auto_matching_search_app.home.service;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auto_matching_search_app.home.domain.MatchingInfo;
import auto_matching_search_app.home.domain.SearchInfo;
import auto_matching_search_app.home.persistence.CreateMapper;
import auto_matching_search_app.keywords.domain.KeywordInfo;

/**
 * Create機能を行う
 * DB関連のサービスクラス (DB関連のビジネスロジックを記述)
 * @author K.Nomoto
 */
@Service
public class DBCreateService {

	/**
	 * DI(Dependency Injection)
	 * CourseInfoMapperクラスの自動インスタンス化
	 */
    @Autowired
    private CreateMapper createMapper;
    
    /**
     * DBからキーワードをランダムに2取得処理メソッド
     * @param KeywordInfo キーワード情報
     */
    public MatchingInfo getTwoKeywordsRandomly(Integer userInfoId) {
    	
    	// キーワード情報リスト取得(2件)
    	List<KeywordInfo> KeywordList = createMapper.selectTwoKeywordsRandomly(userInfoId);
        
    	// 取得したキーワード情報をMatchingInfoに格納
    	MatchingInfo matchingInfo = new MatchingInfo();
    	KeywordInfo KeywordInfo = new KeywordInfo();
    	// 取得したキーワードの一つ目
		KeywordInfo = KeywordList.get(0);
		matchingInfo.setKeyword1(KeywordInfo.getKeyword());
		matchingInfo.setKeywordId1(KeywordInfo.getKeywordId());
		// 取得したキーワードの二つ目
		KeywordInfo = KeywordList.get(1);
		matchingInfo.setKeyword2(KeywordInfo.getKeyword());
		matchingInfo.setKeywordId2(KeywordInfo.getKeywordId());
    	
        // キーワード情報リストを返却
        return matchingInfo;
    }
    
    /**
     * keyword1,2をGoogleでAND検索を行い、検索結果を最大10件スクレイピング
     * @param keyword1
     * @param keyword2
     * @return searchResultList
     * @throws IOException
     */
    public List<SearchInfo> scrapingOfGoogleSearchResults(String keyword1, String keyword2) 
    		throws IOException{
    	
    	// Googleの検索用URL
        String url = "https://www.google.co.jp/search?&num=10"; // 検索クエリ数10件
    	
        // Documentクラスの変数を作成し、その変数に取得したHTML情報を代入
        Document document = Jsoup.connect(url).data("query", keyword1, "query", keyword2).timeout(3000).get();
        
        // 取得したタグ情報を取得する
        Elements elements = document.select("h3 a");
        
        // 格納用リスト生成
        List<SearchInfo> searchResultList = new ArrayList<SearchInfo>();
        
        Integer searchId = 1;
        // HTMLのテキストを取得する「textメソッド」
        // 属性の値を取得する「attrメソッド」
        for (Element element : elements) {
            // Google検索結果を格納する
            SearchInfo searchInfo = new SearchInfo();
            // WebサイトIDを付与
            searchInfo.setSearchId(searchId);
            searchId++;
            // タイトルを取得
            searchInfo.setTitle(URLDecoder.decode(element.text(), "UTF-8"));
            // URLを取得
            searchInfo.setUrl(element.attr("href"));
            searchResultList.add(searchInfo);
        }
        
        return searchResultList;
    }

}
