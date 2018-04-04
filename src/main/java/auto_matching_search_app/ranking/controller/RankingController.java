package auto_matching_search_app.ranking.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import auto_matching_search_app.ranking.form.RankingForm;
import auto_matching_search_app.ranking.service.DBRankingService;
import auto_matching_search_app.user.domain.LoginUserInfo;

/**
 * ランキング画面をコントロールする
 * Controllerクラス
 * リクエストURLにマッピングされている場合に呼び出され、モデルとビューの制御をし、必要に応じてビジネスロジックの呼び出しを行う
 * @author K.Nomoto
 */
@Controller
//セッションで管理するオブジェクトのキー名を指定
@SessionAttributes({"rankingList", "userInfoId", "matchingScoreId"})
public class RankingController {
	
	/**
	 * ランキングを取得、再検索をする
	 * DI(Dependency Injection)
	 * DBUserServiceクラスの自動インスタンス化
	 */
    @Autowired
    private DBRankingService rankingService;
    
    /**
     * ランキングのリクエスト情報
     * リクエストの度にModelオブジェクトへクラスを自動追加する
     * @return new KeywordForm()
     */
    @ModelAttribute(value = "rankingForm") // キー名を指定（Modelオブジェクトには、「rankingForm」で追加される）
    public RankingForm setForm() {
        return new RankingForm();
    }
    
    /**
     * ランキング画面を表示
     * @return　"home/ranking" ランキング画面のパス
     */
    @RequestMapping(value = "/ranking_first", method = RequestMethod.GET)
    public String ranking_start(@SessionAttribute("loginUserInfo") LoginUserInfo loginUserInfo
    		, Model model) {
        
    	Integer userInfoId = loginUserInfo.getUserInfoId();

        // Modelオブジェクトへの値の追加
        model.addAttribute("rankingList", rankingService.getRankingList(userInfoId));
        model.addAttribute("userInfoId", userInfoId);
        
        return "home/ranking";
    }
    
    /**
     * 「再検索」ボタン押下時、ホーム画面に遷移し再検索結果が表示される
     * @return　"home/keywords" ホーム画面へのリダイレクトパス
     */
    @RequestMapping(value = "/ranking_list", method = RequestMethod.GET)
    public String ranking_input(HttpServletRequest request, Model model) {

    	// htmlからリクエスト情報を取得(マッチング関心度ID)
    	String matchingScoreId = request.getParameter("research_btn");
    	
    	// Modelオブジェクトへの値の追加
        model.addAttribute("matchingScoreId", matchingScoreId);
    	
        return "redirect:/home_research";
    }
    
}
