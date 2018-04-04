package auto_matching_search_app.home.form;

/**
 * Google検索結果情報フォーム
 * Formクラス　 リクエスト情報を格納する
 * @author K.Nomoto
 */
public class SearchResultForm {

	// フィールド ***********************************
	/**
	 * Webサイトタイトル
	 */
	private String title;
	/**
	 * WebサイトURL
	 */
	private String href;
	
	// getter,setter ****************************
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
	 * @return href
	 */
	public String getHref() {
		return href;
	}
	/**
	 * @param hrefをセットする
	 */
	public void setHref(String href) {
		this.href = href;
	}

}
