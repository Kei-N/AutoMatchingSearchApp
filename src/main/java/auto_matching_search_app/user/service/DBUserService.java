package auto_matching_search_app.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import auto_matching_search_app.user.form.UserForm;
import auto_matching_search_app.user.persistence.UserMapper;

/**
 * ユーザー情報テーブル関連のサービスクラス
 * DB関連のビジネスロジックを記述
 * @author K.Nomoto
 */
@Service
public class DBUserService {

	/**
	 * DI(Dependency Injection)
	 * UseruserMapperクラスの自動インスタンス化
	 */
    @Autowired
    private UserMapper userMapper;
	
    /**
     * アカウントの存在チェックメソッド
     * @param form ログイン情報(UserFormのインスタンス)
     * @return false(存在していない) or true(存在する)
     */
    public boolean isAccount(UserForm form){
    	int account = userMapper.getCountByAccount(form);
    	
    	return account == 0 ? false : true;
    }
    
    /**
     * ユーザーIDの重複チェック処理メソッド
     * @param userId ユーザID
     * @return false(重複していない) or true(重複している)
     */
    public boolean isDuplicateUserId(String userId) {
        int count = userMapper.getCountByUserId(userId);

         return count == 0 ? false : true;
    }
    
    public Integer getUserInfoId(UserForm form){
    	Integer userInfoId = userMapper.selectUserInfoId(form);
    	
    	return userInfoId;
    }
    
    /**
     * 権限取得メソッド
     * @param form ログイン情報(UserFormのインスタンス)
     * @return false(管理者) or true(一般ユーザー)
     */
    public boolean isAuthority(UserForm form) {
        int authority = userMapper.getAuthority(form);

         return authority == 0 ? true : false;
    }
    
	/**
     *　ログインエラーチェックメソッド
     * @param form ログイン情報(UserFormのインスタンス)
     * @param result エラー情報(BindingResult)
     * @return result.hasErrors() 入力エラー:あり(true),なし(false)
     */
    public boolean isErrorLogin(UserForm form, BindingResult result){
    	
        // ユーザIDとパスワードの必須入力チェック
        if(form.getUserId().equals("") || form.getPassword().equals("")){
        	// エラーメッセージの設定
        	result.reject("errors.required.userId.password");
        	
        } else if(!form.getUserId().equals("") && !form.getPassword().equals("")){
        	if(!isAccount(form)){
        	// エラーメッセージの設定
        	result.reject("errors.notexist.account");
        	}
        }
    	
		return result.hasErrors();
    }
    
    /**
     * ユーザー登録エラーチェックメソッド
     * @param form ユーザー情報(UserFormのインスタンス)
     * @param result エラー情報(BindingResult)
     * @return result.hasErrors() 入力エラー:あり(true),なし(false)
     */
    public boolean isErrorUser(UserForm form, BindingResult result){
    	
    	// ユーザIDの必須入力チェック
        if(form.getUserId().equals("")){
        	// エラーメッセージの設定
        	result.rejectValue("userId", "errors.required.userId");
        
        // ユーザIDの重複チェック
        } else if(isDuplicateUserId(form.getUserId())) {
        	// エラーメッセージの設定
            result.rejectValue("userId", "errors.duplicate.userId");
        }
        
        // パスワードの必須入力チェック
        if(form.getPassword().equals("")){
        	// エラーメッセージの設定
        	result.rejectValue("password", "errors.required.password");
        	
        // パスワードとパスワード（確認用）の一致チェック
        } else if(form.getCheckPw().equals("")){
        	// エラーメッセージの設定
        	result.reject("errors.disagree.password");
        	
        } else if(!form.getPassword().equals(form.getCheckPw())){
        	// エラーメッセージの設定
            result.reject("errors.disagree.password");
            
        }
        
		return result.hasErrors();
    }
    
    /**
     *　ユーザテーブル登録処理メソッド
     * @param form ユーザ登録情報
     */
    @Transactional
    public void insertUser(UserForm form) {
        
        // ユーザテーブル登録
        userMapper.insertUserInfo(form);
    }
}