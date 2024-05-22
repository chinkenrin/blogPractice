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
		return blogDao.findAll();
		}
	}	
	
	// ブログの登録処理チェック
	// もし、findByProductNameが==nullだったら、true　⇒保存処理
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
	
	

}
