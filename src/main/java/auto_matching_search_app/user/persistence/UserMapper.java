package auto_matching_search_app.user.persistence;

import auto_matching_search_app.user.form.UserForm;


/**
 * データベースへのアクセスに使うMyBatisのマッパーインターフェース
 * @author K.Nomoto
 */
public interface UserMapper {

	/**
	 * アカウント(userId,password)の存在チェックメソッドのインターフェース
	 * @param form
	 */
	public int getCountByAccount(UserForm form);
	
	/**
	 * ユーザー情報テーブルのIDの取得
	 * @param userId
	 */
	public Integer selectUserInfoId(UserForm form);
	
	/**
	 * 権限の取得メソッドのインターフェース
	 * @param form
	 */
	public int getAuthority(UserForm form);
	
	/**
	 * userIdの重複チェックメソッドのインターフェース
	 * @param userId
	 */
	public int getCountByUserId(String userId);
	
	/**
	 * ユーザテーブル登録メソッドのインターフェース
	 * @param form
	 */
	public void insertUserInfo(UserForm form);
}
