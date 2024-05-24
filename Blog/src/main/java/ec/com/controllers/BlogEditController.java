package ec.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ec.com.models.entity.Account;
import ec.com.models.entity.Blog;
import ec.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogEditController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private HttpSession session;
	
	//編集画面の表示
	@GetMapping("/blog/edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId, Model model) {
		// セッションからログインしている人の情報をaccountという変数に格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
		// もしaccount==null ログイン画面にリダイレクトする	
		if(account == null) {
			return "redirect:/account/login";
		}else {
			// 編集画面に表示させる情報を変数に格納 blog
			Blog blog = blogService.blogEditCheck(blogId);
			// もしblog==nullだったら、ブログ一覧ページにリダイレクトする
			// そうでない場合、編集画面に編集する内容を渡す
			// 編集画面を表示
			if (blog == null) {
				return "redirect:/product/blogList";
			} else {
				model.addAttribute("accountName", account.getAccountName());
				model.addAttribute("blog", blog);
				return "edit.html";
			}

		}
	}
	
	// 更新処理をする
	@PostMapping("/product/edit/process")
	public String blogUpdate(@RequestParam String blogTitle, @RequestParam String categoryName,
			@RequestParam MultipartFile blogImage, @RequestParam String ariticle,
			@RequestParam Long blogId) {
		// セッションからログインしている人の情報をaccountという変数に格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
		// もし、account == nullだったら、ログイン画面にリダイレクトする
		// そうでない場合:
		// 現在の日時情報を元に、ファイル名を作成しています。SimpleDateFormatクラスを使用して、日時のフォーマットを指定している。
		// 具体的には、"yyyy-MM-dd-HH-mm-ss-"の形式でフォーマットされた文字列を取得している。
		// その後、blogImageオブジェクトから元のファイル名を取得し、フォーマットされた日時文字列と連結して、fileName変数に代入

		// ファイルの保存
		// もし、blogUpdateの結果がtrueの場合　⇒　「ブログ一覧」にリダイレクトする
		// そうでない場合　⇒　「ブログ編集画面」にリダイレクトする
		if (account == null) {
			return "redirect:/account/login";
		} else {
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/product-img/" + fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//blogUpdate（変数）⇐必ず順番に書く
			if (blogService.blogUpdate(blogId, blogTitle, categoryName, fileName, ariticle,
					account.getAccountId())) {
				return "redirect:/product/edit/process/result";
			} else {
				return "redirect:/product/edit" + blogId;
			}
		}

	}
	
	// 更新完了画面の表示
	@GetMapping("/product/edit/process/result")
		public String showProcessResult() {
		return "update";
	}
}