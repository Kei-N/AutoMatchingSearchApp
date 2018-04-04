package auto_matching_search_app.keywords.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import auto_matching_search_app.keywords.domain.KeywordInfo;
import auto_matching_search_app.keywords.form.KeywordForm;
import auto_matching_search_app.keywords.service.DBKeywordsService;
import auto_matching_search_app.user.domain.LoginUserInfo;

/**
 * キーワード管理画面をコントロールする
 * Controllerクラス
 * リクエストURLにマッピングされている場合に呼び出され、モデルとビューの制御をし、必要に応じてビジネスロジックの呼び出しを行う
 * @author K.Nomoto
 */
@Controller
//セッションで管理するオブジェクトのキー名を指定
@SessionAttributes({"keywordList", "userInfoId"})
public class KeywordsController {
	
	/**
	 * キーワード登録、削除を行う
	 * DI(Dependency Injection)
	 * DBUserServiceクラスの自動インスタンス化
	 */
    @Autowired
    private DBKeywordsService keywordsService;
    
    /**
     * キーワードのリクエスト情報
     * リクエストの度にModelオブジェクトへクラスを自動追加する
     * @return new KeywordForm()
     */
    @ModelAttribute(value = "keywordForm") // キー名を指定（Modelオブジェクトには、「keywordForm」で追加される）
    public KeywordForm setForm() {
        return new KeywordForm();
    }
    
    /**
     * キーワード管理画面を表示
     * @return　"home/keywords" キーワード管理画面のパス
     */
    @RequestMapping(value = "/keywords_first", method = RequestMethod.GET)
    public String keywords_start(@SessionAttribute("loginUserInfo") LoginUserInfo loginUserInfo
    		, Model model) {
        
    	Integer userInfoId = loginUserInfo.getUserInfoId();

        // Modelオブジェクトへの値の追加
        model.addAttribute("keywordList", keywordsService.getKeywordList(userInfoId));
        model.addAttribute("userInfoId", userInfoId);
        
        return "home/keywords";
    }
    
    /**
     * 「登録」ボタン押下時、キーワードがDBに登録され、キーワード一覧に表示される
     * @return　"home/keywords" キーワード画面のパス
     */
    @RequestMapping(value = "/keywords_input", method = RequestMethod.GET)
    public String keywords_input(@Validated @ModelAttribute("keywordForm") KeywordForm form
    		, BindingResult result, Model model
    		, @SessionAttribute("userInfoId") Integer userInfoId) {

        // 入力エラー判定
    	if(keywordsService.isErrorKeyword(form, result)) {
        	
        // エラーが存在する場合(true)
            return "home/keywords";
        }
    	
    	// エラーが存在しない場合(false)
    	// DB登録処理
    	form.setUserInfoId(userInfoId);
    	keywordsService.insert(form);

    	// Modelオブジェクトへの値の追加
        model.addAttribute("keywordList", keywordsService.getKeywordList(userInfoId));
    	
        return "redirect:/keywords_input?reset";
    }
    
    /**
     * キーワード管理画面を表示
     * @return　"home/keywords" キーワード管理画面のパス
     */
    @RequestMapping(value = "/keywords_input", params = "reset" , method = RequestMethod.GET)
    public String keyword_input_reset() {
        
        return "home/keywords";
    }
    

    /**
     * 「削除」ボタン押下時、キーワードがDBから削除され、キーワード一覧が更新される
     * @return　"home/keywords" キーワード画面のパス
     */
    @RequestMapping(value = "/keywords_list", params = "delete_btn", method = RequestMethod.POST)
    public String keywords_list(@ModelAttribute("keywordList") List<KeywordInfo> keywordList
    		, HttpServletRequest request, Model model
    		, @SessionAttribute("userInfoId") Integer userInfoId) {
        
    	// htmlからリクエスト情報を取得(キーワードID)
    	String keywordId = request.getParameter("delete_btn");
    	
    	for(int i = 0; i < keywordList.size(); i++){
    		KeywordInfo keywordInfo = keywordList.get(i);
    		if(keywordInfo.getKeywordId() == Integer.parseInt(keywordId)){
    			// 削除処理
    			keywordsService.delete(keywordInfo);
    		}
    	}
    	
    	// Modelオブジェクトへの値の追加
        model.addAttribute("keywordList", keywordsService.getKeywordList(userInfoId));
    	
        return "home/keywords";
    }
    
}
