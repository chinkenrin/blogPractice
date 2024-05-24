package ec.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ec.com.models.entity.Account;
import ec.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogDeleteController {
    @Autowired
    private BlogService blogService;
    
    @Autowired
    private HttpSession session;
    
    @PostMapping("/product/delete")
    public String blogDelete(@RequestParam Long blogId) {
        // セッションからログインしている人の情報をaccountという変数に格納
        Account account = (Account) session.getAttribute("loginAccountInfo");
        // もしaccount==null ログイン画面にリダイレクトする
        if (account == null) {
            return "redirect:/account/login";
        } else {
            // もしdeleteBlogの結果がtrueの時に、ブログ一覧にリダイレクトする
            // そうでない場合、編集画面にリダイレクトする
            if (blogService.deleteBlog(blogId)) {
                return "redirect:/product/delete/process/result";
            } else {
                return "redirect:/blog/edit/" + blogId;
            }
        }
    }
    
    // 削除完了画面の表示
    @GetMapping("/product/delete/process/result")
    public String showDeleteResult() {
        return "delete";
    }
}
