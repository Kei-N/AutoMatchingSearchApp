package auto_matching_search_app.user.domain;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * ログイン情報を保持するクラス
 * @author K.Nomoto
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class LoginUserInfo implements Serializable {

	// フィールド*************************************************
	/**
	 * インターフェースjava.io.Serializableを実装した場合に必要となる
	 */
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Integer userInfoId;
    /**
     * ユーザID
     */
    private String userId;
    /**
     * パスワード
     */
    private String password;
    /**
     * 権限   0：管理者, 1：一般ユーザ
     */
    private Integer authority;
    
    //　getter,setter******************************************
	/**
	 * @return userInfoId
	 */
	public Integer getUserInfoId() {
		return userInfoId;
	}
	/**
	 * @param userInfoIdをセットする
	 */
	public void setUserInfoId(Integer userInfoId) {
		this.userInfoId = userInfoId;
	}
	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userIdをセットする
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param passwordをセットする
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return authority
	 */
	public Integer getAuthority() {
		return authority;
	}
	/**
	 * @param authorityをセットする
	 */
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}
	/**
	 * @return serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}