package auto_matching_search_app.home.domain;

/**
 * Google検索結果取得用Bean
 * domainパッケージ：情報のかたまりとなるクラス
 * @author K.Nomoto
 */
public class SearchInfo {
	
	// フィールド ***********************************
	/**
	 *WebサイトID
	 */
	private Integer searchId;
	/**
	 * Webサイトタイトル
	 */
	private String title;
	/**
	 * WebサイトURL
	 */
	private String url;
	
	// getter,setter ****************************
	/**
	 * @return searchId
	 */
	public Integer getSearchId() {
		return searchId;
	}
	/**
	 * @param searchIdをセットする
	 */
	public void setSearchId(Integer searchId) {
		this.searchId = searchId;
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
