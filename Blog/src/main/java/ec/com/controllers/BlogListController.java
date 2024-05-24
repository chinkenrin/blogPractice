package ec.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ec.com.models.entity.Account;
import ec.com.models.entity.Blog;
import ec.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogListController {
	@Autowired
	private HttpSession session;
	
  @Autowired
	private BlogService blogService;
	
	// ブログ一覧画面を表示する
	@GetMapping("/product/blogList")
	public String getBlogList(Model model) {
		// セッションからログインしている人の情報を取得
		Account account = (Account) session.getAttribute("loginAccountInfo");
		// もし、Account == null ログイン画面にリダイレクトする
		// そうでない場合⇒　ログインしている人の名前の情報を画面に渡してブログ一覧のblogList.htmlを表示。
		
		if (account == null) {
			return "redirect:/account/login";
		} else {
			// ブログの情報を取得する。
			List<Blog> blogList = blogService.selectAllBlogList(account.getAccountId());
			model.addAttribute("accountName", account.getAccountName());
			model.addAttribute("blogList", blogList);
			return "blogList.html";
		}
	}

}
