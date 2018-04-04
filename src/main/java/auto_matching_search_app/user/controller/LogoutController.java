package auto_matching_search_app.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * ログイン情報をコントロールする
 * Controllerクラス
 * リクエストURLにマッピングされている場合に呼び出され、モデルとビューの制御をし、必要に応じてビジネスロジックの呼び出しを行う
 * @author K.Nomoto
 */
@Controller
//セッションで管理するオブジェクトのキー名を指定
@SessionAttributes("scopedTarget.loginUserInfo")
public class LogoutController {
	
	/**
     * ログアウト処理（ログイン画面にリダイレクト）
     * @param sessionStatus SessionStatusオブジェクトのインスタンス
     * @return　"redirect:/user_login?reset" ログイン画面へのリダイレクトパス
     */
    @RequestMapping(value = "/user_logout", method = RequestMethod.GET)
    public String user_logout(SessionStatus sessionStatus) {
    	// @SessionAttributesで指定したオブジェクトをセッションから破棄
        sessionStatus.setComplete();
        
        return "redirect:/auto_matching_search_app_login";
    }

}