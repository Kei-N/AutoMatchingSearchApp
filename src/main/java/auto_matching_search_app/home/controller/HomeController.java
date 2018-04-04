package auto_matching_search_app.home.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import auto_matching_search_app.bookmarks.form.BookmarkForm;
import auto_matching_search_app.bookmarks.service.DBBookmarksService;
import auto_matching_search_app.home.domain.MatchingInfo;
import auto_matching_search_app.home.domain.SearchInfo;
import auto_matching_search_app.home.form.SearchResultForm;
import auto_matching_search_app.home.service.DBCreateService;
import auto_matching_search_app.ranking.service.DBRankingService;
import auto_matching_search_app.user.domain.LoginUserInfo;

/**
 * ホーム画面をコントロールする
 * Controllerクラス
 * リクエストURLにマッピングされている場合に呼び出され、モデルとビューの制御をし、必要に応じてビジネスロジックの呼び出しを行う
 * @author K.Nomoto
 */
@Controller
//セッションで管理するオブジェクトのキー名を指定
@SessionAttributes({"userInfoId", "searchResultList", "matchingInfo"})
public class HomeController {
	
	/**
	 * Creat機能
	 * DI(Dependency Injection)
	 * DBCreateServiceクラスの自動インスタンス化
	 */
    @Autowired
    private DBCreateService createService;
    
	/**
	 * ブックマーク登録を行う
	 * DI(Dependency Injection)
	 * DBUserServiceクラスの自動インスタンス化
	 */
    @Autowired
    private DBBookmarksService bookmarksService;
    
	/**
	 * matching_scoreテーブルの挿入・更新・存在チェックをする
	 * DI(Dependency Injection)
	 * DBUserServiceクラスの自動インスタンス化
	 */
    @Autowired
    private DBRankingService rankingService;
    
    /**
     * Google検索結果のリクエスト情報
     * リクエストの度にModelオブジェクトへクラスを自動追加する
     * @return new KeywordForm()
     */
    @ModelAttribute(value = "srForm") // キー名を指定（Modelオブジェクトには、「srForm」で追加される）
    public SearchResultForm setForm() {
        return new SearchResultForm();
    }
    
    /**
     * ホーム画面を表示
     * @return　"home/home" ホーム画面のパス
     */
    @RequestMapping(value = "/home_first", method = RequestMethod.GET)
    public String home_start(@SessionAttribute("loginUserInfo") LoginUserInfo loginUserInfo
    		, Model model) {
        
    	Integer userInfoId = loginUserInfo.getUserInfoId();
    	List<SearchInfo> searchResultList = new ArrayList<SearchInfo>();
    	
        // Modelオブジェクトへの値の追加
        model.addAttribute("userInfoId", userInfoId);
        model.addAttribute("searchResultList", searchResultList);
        
        return "home/home";
    }
    
    //  「Create」ボタン押下時処理 **********************************************************************
    /**
     * 「Create」ボタン押下時処理
     * @param userInfoId
     * @param model
     * @return "redirect:/create_after?do" ホーム画面へのリダイレクトパス
     */
    @RequestMapping(value = "/home_create", method = RequestMethod.GET)
    public String home_create(@SessionAttribute("userInfoId") Integer userInfoId
    		, Model model) {
        
		/** 処理内容
		 * ①DBに格納しているキーワードをランダムに取得
		 * ②キーワードを元にgoogle検索
		 * ③検索結果10件を取得し、サイト名とURLを取得
		 * ④取得したサイト名とURLをランダムに3件抜き出す
		 * ⑤スクレイピング結果画面に出力
		 */
	
    	// DBのキーワードをランダムに2件取得
    	MatchingInfo matchingInfo = createService.getTwoKeywordsRandomly(userInfoId);
    	
		// Modelオブジェクトへの値の追加
    	matchingInfo.setUserInfoId(userInfoId);
        model.addAttribute("matchingInfo", matchingInfo);
        String keyword1 = matchingInfo.getKeyword1();  // 取得したキーワード一つ目
        String keyword2 = matchingInfo.getKeyword2();  // 取得したキーワード二つ目
    	
		try {
			// Google検索結果をスクレイピング
			List<SearchInfo> searchResultList = createService.scrapingOfGoogleSearchResults(keyword1, keyword2);

			// Modelオブジェクトへの値の追加
	        model.addAttribute("searchResultList", searchResultList);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        return "redirect:/create_after?do";
    }
    
    /**
     * ホーム画面を表示
     * @return　"home/home" ホーム画面のパス
     */
    @RequestMapping(value = "/create_after", params = "do", method = RequestMethod.GET)
    public String create_after() {
        
        return "home/home";
    }
    
    // Webサイトが新しいタブで開く仕様にしたい*****************************************************
    /**
     * 「表示」ボタン押下時、matching_scoreテーブルに1点追加し、表示ボタンを押下したWebサイトへ遷移
     * @return　"redirect:"+url WebサイトURLへのリダイレクトパス
     */
    @RequestMapping(value = "/home_search_result", params = "view_btn", method = RequestMethod.POST)
    public String matching_score_1point(@SessionAttribute("userInfoId") Integer userInfoId
    		, @SessionAttribute("searchResultList") List<SearchInfo> searchResultList
    		, @SessionAttribute("matchingInfo") MatchingInfo matchingInfo
    		, HttpServletRequest request, Model model) {

    	// htmlからリクエスト情報を取得(WebサイトID)
    	String searchId = request.getParameter("view_btn");
    	
    	// matching_scoreテーブルに1点追加
    	rankingService.addMatchingScore1Point(matchingInfo);
    	
    	// 遷移先URL
		String url = null;
    	
    	// 表示ボタンが押されたsearchIdと合致するものを抽出する
    	for(int i = 0; i < searchResultList.size(); i++){
    		SearchInfo searchInfo = searchResultList.get(i);
    		if(searchInfo.getSearchId() == Integer.parseInt(searchId)){
    			
    			// URLを抽出
    			url = searchInfo.getUrl();
    		}
    	}
    	// 表示ボタンを押下したURLへリダイレクト
        return "redirect:" + url;
    }
    
    /**
     * 「保存」ボタン押下時、、ブックマークがbookmarksテーブルに登録され、matching_scoreテーブルに3点追加する
     * @param userInfoId
     * @param model
     * @return "redirect:/home_input_bookmark_after?do" ホーム画面へのリダイレクトパス
     */
    @RequestMapping(value = "/home_search_result", params = "save_btn", method = RequestMethod.POST)
    public String home_input_bookmarks(@SessionAttribute("userInfoId") Integer userInfoId
    		, @SessionAttribute("searchResultList") List<SearchInfo> searchResultList
    		, @SessionAttribute("matchingInfo") MatchingInfo matchingInfo
    		, HttpServletRequest request, Model model
    		, @Validated @ModelAttribute("bookmarkForm") BookmarkForm bookmarkForm
    		, BindingResult result) {

    	// htmlからリクエスト情報を取得(WebサイトID)
    	String searchId = request.getParameter("save_btn");
    	
    	// 保存ボタンが押されたsearchIdと合致するものを抽出する
    	for(int i = 0; i < searchResultList.size(); i++){
    		SearchInfo searchInfo = searchResultList.get(i);
    		if(searchInfo.getSearchId() == Integer.parseInt(searchId)){
    			// ブックマークフォームに値をセット
    	    	bookmarkForm.setUserInfoId(userInfoId);
    	    	bookmarkForm.setTitle(searchInfo.getTitle());
    	    	bookmarkForm.setUrl(searchInfo.getUrl());
    			
    			// 初期化
    			result = new DataBinder(bookmarkForm).getBindingResult();
    	    	
    	    	// ブックマークの重複確認
    	    	if(bookmarksService.isErrorBookmark(bookmarkForm, result)){
    	    		
    	    	// 重複している場合
    	    		return "home/home";
    	    	}
    	    	// 重複していない場合
    	    	// ブックマークを登録
    	    	bookmarksService.insert(bookmarkForm);
    	    	// マッチングワードの関心度点数加算(3点)
    	    	rankingService.addMatchingScore3Point(matchingInfo);
    		}
    	}
		
        return "home/home";
    }
    
    /**
     * 「再検索」ボタン押下時処理
     * @param userInfoId
     * @param model
     * @return "redirect:/create_after?do" ホーム画面へのリダイレクトパス
     */
    @RequestMapping(value = "/home_research", method = RequestMethod.GET)
    public String home_research(@SessionAttribute("userInfoId") Integer userInfoId
    		, @SessionAttribute("matchingScoreId") Integer matchingScoreId
    		, Model model) {
	
    	// 再検索キーワードペアを取得
    	MatchingInfo matchingInfo = rankingService.getMatchingKeywords(matchingScoreId);
    	
		// Modelオブジェクトへの値の追加
    	matchingInfo.setUserInfoId(userInfoId);
        model.addAttribute("matchingInfo", matchingInfo);
        String keyword1 = matchingInfo.getKeyword1();  // 取得したキーワード一つ目
        String keyword2 = matchingInfo.getKeyword2();  // 取得したキーワード二つ目
    	
		try {
			
			// Google検索結果をスクレイピング
			List<SearchInfo> searchResultList = createService.scrapingOfGoogleSearchResults(keyword1, keyword2);

			// Modelオブジェクトへの値の追加
	        model.addAttribute("searchResultList", searchResultList);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        return "redirect:/create_after?do";
    }
    
    /**
     * ログアウトコントローラーに遷移
     * @return　"redirect:/ranking_first" ログアウトコントローラーへのリダイレクトパス
     */
    @RequestMapping(value = "/home_logout", method = RequestMethod.GET)
    public String home_logout() {
        
        return "redirect:/user_logout";
    }
    
}
