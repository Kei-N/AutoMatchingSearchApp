package auto_matching_search_app.bookmarks.domain;

/**
 * キーワード情報Bean
 * domainパッケージ：情報のかたまりとなるクラス
 * @author K.Nomoto
 */
public class BookmarkInfo {

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
