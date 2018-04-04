package auto_matching_search_app.bookmarks.form;

/**
 * キーワード情報フォーム
 * Formクラス　 リクエスト情報を格納する
 * @author K.Nomoto
 */
public class BookmarkForm {

	// フィールド ********************************************
	/**
	 * ユーザー情報ID
	 */
	private Integer userInfoId;
	/**
	 * ブックマークID
	 */
	private Integer bookmarkId;
	/**
	 * Webサイトタイトル
	 */
	private String title;
	/**
	 * WebサイトURL
	 */
	private String url;
	
	// getter,setter *************************************
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
	 * @return bookmarkId
	 */
	public Integer getBookmarkId() {
		return bookmarkId;
	}
	/**
	 * @param bookmarkIdをセットする
	 */
	public void setBookmarkId(Integer bookmarkId) {
		this.bookmarkId = bookmarkId;
	}
	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param titleをセットする
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param urlをセットする
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
}
