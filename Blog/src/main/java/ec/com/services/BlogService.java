package ec.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.models.dao.BlogDao;
import ec.com.models.entity.Blog;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao; 
	
	// ブログ一覧のチェック
	// もしaccountId　==　null 戻り値としてnull
	// findAll内容をコントローラークラスに渡す
	public List<Blog> selectAllBlogList(Long accountId){
	if(accountId == null) {
		return null;
	}else {
		return blogDao.findByAccountId(accountId);
		}
	}	
	
	// ブログの登録処理チェック
	// もし、findByCategoryNameが==nullだったら、true　⇒保存処理
	// そうでない場合、false　⇒処理しない
	
	public boolean createBlog(String blogTitle, 
			String categoryName, 
			String blogImage, 
			String ariticle, 
			Long accountId) {
		if(blogDao.findByCategoryName(categoryName)==null) {
			blogDao.save(new Blog(blogTitle,categoryName,blogImage,ariticle,accountId));
			return true;
		}else {
			return false;
		}
	}
	
	//編集画面を表示するときのチェック
	//もし、blogId == null 表示できない、状況はnull反映
	//そうでない場合　⇒　findByBlogIdの情報をコントローラークラスに渡す
	public Blog blogEditCheck(Long blogId) {
		if(blogId == null) {
			return null;
		}else {
			return blogDao.findByBlogId(blogId);
		}
	}
	
	// 更新処理のチェック:
	// もし、blogId==nullだったら、更新処理はしない ⇒ false
	// そうでない場合、更新処理をする
	// コントローラークラスからもらった、blogIdを使って、編集する前の、データを取得
	// 変更するべきところだけ、セッターを使用してデータの更新をする。
	// trueを返す
	public boolean blogUpdate(Long blogId, String blogTitle, String categoryName, String blogImage,
			String ariticle, Long accountId) {
		if (blogId == null) {
			return false;
		} else {
			Blog blog = blogDao.findByBlogId(blogId);
			blog.setBlogTitle(blogTitle);
			blog.setCategoryName(categoryName);
			blog.setBlogImage(blogImage);
			blog.setAriticle(ariticle);
			blog.setAccountId(accountId);
			blogDao.save(blog);
			return true;
		}
	}
	// 削除処理のチェック
	// もし、コントローラークラスから受け取ったblogIdがnullであれば
	// false削除
	// そうでない場合、deleteByBlogIdを使って削除処理
	// true
	public boolean deleteBlog(Long blogId) {
		if (blogId == null) {
			return false;
		} else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
}
