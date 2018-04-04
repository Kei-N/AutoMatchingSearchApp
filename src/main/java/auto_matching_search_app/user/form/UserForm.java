package auto_matching_search_app.user.form;

/**
 * ログイン情報フォーム
 * Formクラス　 リクエスト情報を格納する
 * @author K.Nomoto
 */
public class UserForm {

	// フィールド***************************************
	/**
	 * ユーザID
	 */
	private String userId;
	/**
	 * パスワード
	 */
	private String password;
	/**
	 * パスワード（確認用）
	 */
	private String checkPw;
	/**
	 * 権限  0：管理者, 1：一般ユーザ
	 */
	private Integer authority;
	
	// getter,setter********************************
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
	 * @return checkPw
	 */
	public String getCheckPw() {
		return checkPw;
	}
	/**
	 * @param checkPwをセットする
	 */
	public void setCheckPw(String checkPw) {
		this.checkPw = checkPw;
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
	
}
