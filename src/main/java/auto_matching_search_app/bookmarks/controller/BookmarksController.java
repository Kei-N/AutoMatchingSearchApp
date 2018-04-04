package auto_matching_search_app.bookmarks.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import auto_matching_search_app.bookmarks.domain.BookmarkInfo;
import auto_matching_search_app.bookmarks.form.BookmarkForm;
import auto_matching_search_app.bookmarks.service.DBBookmarksService;
import auto_matching_search_app.user.domain.LoginUserInfo;

/**
 * ブックマーク画面をコントロールする
 * Controllerクラス
 * リクエストURLにマッピングされている場合に呼び出され、モデルとビューの制御をし、必要に応じてビジネスロジックの呼び出しを行う
 * @author K.Nomoto
 */
@Controller
//セッションで管理するオブジェクトのキー名を指定
@SessionAttributes({"bookmarkList", "userInfoId"})
public class BookmarksController {
	
	/**
	 * ブックマーク削除を行う
	 * DI(Dependency Injection)
	 * DBUserServiceクラスの自動インスタンス化
	 */
    @Autowired
    private DBBookmarksService bookmarksService;
    
    /**
     * ブックマークのリクエスト情報
     * リクエストの度にModelオブジェクトへクラスを自動追加する
     * @return new KeywordForm()
     */
    @ModelAttribute(value = "bookmarkForm") // キー名を指定（Modelオブジェクトには、「bookmarkForm」で追加される）
    public BookmarkForm setForm() {
        return new BookmarkForm();
    }
    
    /**
     * ブックマーク管理画面を表示
     * @return　"home/keywords" ブックマーク管理画面のパス
     */
    @RequestMapping(value = "/bookmarks_first", method = RequestMethod.POST)
    public String bookmarks_start(@SessionAttribute("loginUserInfo") LoginUserInfo loginUserInfo
    		, Model model) {
        
    	Integer userInfoId = loginUserInfo.getUserInfoId();

        // Modelオブジェクトへの値の追加
        model.addAttribute("bookmarkList", bookmarksService.getBookmarkList(userInfoId));
        model.addAttribute("userInfoId", userInfoId);
        
        return "home/bookmarks";
    }
    
    /**
     * 「削除」ボタン押下時、ブックマークがDBから削除され、ブックマーク一覧が更新される
     * @return　"home/bookmarks" キーワード画面のパス
     */
    @RequestMapping(value = "/bookmarks_list", params = "delete_btn", method = RequestMethod.GET)
    public String bookmarks_list(@ModelAttribute("bookmarkList") List<BookmarkInfo> bookmarkList
    		, HttpServletRequest request, Model model
    		, @SessionAttribute("userInfoId") Integer userInfoId) {
        
    	// htmlからリクエスト情報を取得(キーワードID)
    	String bookmarkId = request.getParameter("delete_btn");
    	
    	for(int i = 0; i < bookmarkList.size(); i++){
    		BookmarkInfo bookmarkInfo = bookmarkList.get(i);
    		if(bookmarkInfo.getBookmarkId() == Integer.parseInt(bookmarkId)){
    			// 削除処理
    			bookmarksService.delete(bookmarkInfo);
    		}
    	}
    	
    	// Modelオブジェクトへの値の追加
        model.addAttribute("bookmarkList", bookmarksService.getBookmarkList(userInfoId));
    	
        return "home/bookmarks";
    }
    
}
