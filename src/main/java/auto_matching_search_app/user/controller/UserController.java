package auto_matching_search_app.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import auto_matching_search_app.user.domain.LoginUserInfo;
import auto_matching_search_app.user.form.UserForm;
import auto_matching_search_app.user.service.DBUserService;

/**
 * ユーザー情報をコントロールする
 * Controllerクラス
 * リクエストURLにマッピングされている場合に呼び出され、モデルとビューの制御をし、必要に応じてビジネスロジックの呼び出しを行う
 * @author K.Nomoto
 */
@Controller
//セッションで管理するオブジェクトのキー名を指定
@SessionAttributes({"userForm", "loginUserInfo"})
public class UserController {

	/**
     * ログイン情報
     * DI(Dependency Injection)
	 * LoginUserInfoクラスの自動インスタンス化
     */
    @Autowired
    private LoginUserInfo loginUserInfo;
	
	/**
	 * DI(Dependency Injection)
	 * DBUserServiceクラスの自動インスタンス化
	 */
    @Autowired
    private DBUserService dbUserService;
    
    /**
     * ログイン・ユーザー登録のリクエスト情報
     * リクエストの度にModelオブジェクトへクラスを自動追加する
     * @return new UserForm()　UserFormのインスタンス化されたもの(UserForm)
     */
    @ModelAttribute(value = "userForm") // キー名を指定（Modelオブジェクトには、「userForm」で追加される）
    public UserForm setForm() {
        return new UserForm();
    }
    
    /**
     * ログイン画面にリダイレクト
     * @param sessionStatus SessionStatusオブジェクトのインスタンス
     * @return　"redirect:/auto_matching_search_app_login?reset" ログイン画面へのリダイレクトパス
     */
    @RequestMapping(value = "/auto_matching_search_app_login", method = RequestMethod.GET)
    public String app_start(SessionStatus sessionStatus) {
    	// @SessionAttributesで指定したオブジェクトをセッションから破棄
        sessionStatus.setComplete();
        
        return "redirect:/auto_matching_search_app_login?reset";
    }
    
    /**
     * ログイン画面を表示
     * @return　"user/login" ログイン画面のパス
     */
    @RequestMapping(value = "/auto_matching_search_app_login", params = "reset" , method = RequestMethod.GET)
    public String app_reset() {
        
        return "user/login";
    }
    
    /**
     * [ログイン画面] 「ログイン」ボタン押下時
     * 管理者：管理者メニュー画面に遷移(今はホーム画面に遷移)
     * 一般ユーザー：ホーム画面に遷移
     * @param form ログイン情報(UserFormのインスタンス)
     * @param result エラー情報(BindingResult)
     * @return "user/login" ログイン画面のパス(入力エラーあり)
     * "redirect:/admin_menu" CourseControllerへのリダイレクトパス(入力エラーなし：管理者)
     * "redirect:/user_input" ApplicationControllerへのリダイレクトパス(入力エラーなし：一般ユーザー)
     */
    @RequestMapping(value = "/user_act", params = "login_btn", method = RequestMethod.POST)
    public String user_login(@Validated @ModelAttribute("userForm") UserForm form
    		, BindingResult result, Model model) {
    	
        // 入力エラー判定
        if(dbUserService.isErrorLogin(form, result)) {
        
        // エラーが存在する場合(true) ********************************
            return "user/login";
        }
        
        // エラーが存在しない場合(false) ******************************
        //　ログイン情報をLoginUserInfoクラスにset
        loginUserInfo.setUserInfoId(dbUserService.getUserInfoId(form));
        loginUserInfo.setUserId(form.getUserId());
        loginUserInfo.setPassword(form.getPassword());
		
		model.addAttribute("loginUserInfo", loginUserInfo);
        
        if(dbUserService.isAuthority(form)){
        	// 管理者
        	return "redirect:/home_first";
        }
        //一般ユーザー
        return "redirect:/home_first";
    }
    
    /**
     * [ログイン画面] 「ユーザー登録」ボタン押下時、ユーザー登録画面に遷移
     * @param form UserFormのインスタンス
     * @return "redirect:/user_act?reset" ユーザー登録画面へのリダイレクトパス
     */
    @RequestMapping(value = "/user_act", params = "user_btn", method = RequestMethod.POST)
    public String user_input(SessionStatus sessionStatus) {
    	// @SessionAttributesで指定したオブジェクトをセッションから破棄
        sessionStatus.setComplete();
    
        return "redirect:/user_act?reset";
    }

    /**
     * ユーザー登録画面を表示
     * @return　"user/user_regi" ユーザー登録画面のパス
     */
    @RequestMapping(value = "/user_act", params = "reset" , method = RequestMethod.GET)
    public String user_input_reset() {
        
        return "user/user_regi";
    }
    
    /**
     * [ユーザー登録画面] 「戻る」ボタン押下時、ログイン画面に遷移
     * @param sessionStatus SessionStatusオブジェクトのインスタンス
     * @return "user/user_regi" ログイン画面のパス
     */
    @RequestMapping(value = "/user_regi", params = "back_btn", method = RequestMethod.POST)
    public String user_regi_back(SessionStatus sessionStatus) {
    	// @SessionAttributesで指定したオブジェクトをセッションから破棄
        sessionStatus.setComplete();
        
        return "user/login";
    }
    
    /**
     * [ユーザー登録画面] 「確認」ボタン押下時、ユーザー登録確認画面に遷移
     * @param form ユーザー登録情報(UserFormのインスタンス)
     * @param result エラー情報(BindingResult)
     * @return "user/user_regi" ユーザー登録画面のパス(入力エラーあり)
     * "user/user_conf" ユーザー登録確認画面のパス(入力エラーなし：管理者)
     */
    @RequestMapping(value = "/user_regi", params = "conf_btn", method = RequestMethod.POST)
    public String user_regi(@Validated @ModelAttribute("userForm") UserForm form
    		, BindingResult result) {
    	
        // 入力エラー判定
        if(dbUserService.isErrorUser(form, result)) {
        
        // エラーが存在する場合(true)
            return "user/user_regi";
        }

        // エラーが存在しない場合(false)
        return "user/user_conf";
    }
    
    /**
     * [ユーザー登録確認画面] 「戻る」ボタン押下時、ユーザー登録画面に遷移
     * @return "user/user_regi" ログイン画面のパス
     */
    @RequestMapping(value = "/user_conf", params = "back_btn", method = RequestMethod.GET)
    public String user_conf_back() {
        
        return "user/user_regi";
    }
    
    /**
     * [ユーザー登録確認画面] 「登録」ボタン押下時、ユーザー登録完了画面へリダイレクト
     * @param form 入力されたユーザー登録情報(UserFormのインスタンス)
     * @return "redirect:/user_conf?finish"　リダイレクトパス
     */
    @RequestMapping(value = "/user_conf", params = "insert_btn", method = RequestMethod.GET)
    public String user_conf(@ModelAttribute("userForm") UserForm form) {
    	
        // データ登録を行うためのサービス処理呼び出し
    	dbUserService.insertUser(form);

        // 登録完了画面へリダイレクト
        return "redirect:/user_conf?finish";
    }
    
    /**
     * リダイレクト後に呼び出され、ユーザー登録完了画面に遷移
     * @param sessionStatus SessionStatusオブジェクトのインスタンス
     * @return "total/user_end" ユーザー登録完了画面のパス
     */
    @RequestMapping(value = "/user_conf", params = "finish", method = RequestMethod.GET)
    public String endFinish() {
        
        return "user/user_end";
    }
    
    /**
     * [ユーザー登録完了画面] 「ログインメニューへ」ボタン押下時、ログイン画面に遷移
     * @return "redirect:/user_input" 講座申込み画面へのリダイレクトパス
     */
    @RequestMapping(value = "/user_end", params = "menu_btn", method = RequestMethod.GET)
    public String user_home() {
        
        return "redirect:/auto_matching_search_app_login";
    }
    
}
