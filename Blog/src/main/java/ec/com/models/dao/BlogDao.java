package ec.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.models.entity.Blog;
import jakarta.transaction.Transactional;

//<Blogはentityのクラス名, Longはテーブルの主キーの属性>
@Repository
@Transactional
public interface BlogDao extends JpaRepository<Blog, Long> {
	//保存処理と更新処理 insertとupdate
	Blog save(Blog blog);
	
	//SELECT * FROM blog　（１、すべて検索「findAll」と同じ意味）
	//SELECT * FROM blog where account_id = ?　（２、アカウント分けて表示「findByAccountId」と同じ意味）
	//用途：ブログの一覧を表示させるときに使用(いっぱい複数を取るため。ｓｑｌ文１は「findAll」と同じ意味。ｓｑｌ文２は「findByAccountId」)
	List<Blog>findByAccountId(Long accounId);
	
	//SELECT * FROM blog WHERE category_name = ?
	//用途：ブログの登録チェックに使用（同じ商品名が登録されないようにするチェックに使用）
	Blog findByCategoryName(String categoryName);
	
	//SELECT * FROM blog WHERE blog_id = ?
	//用途：編集画面を表示する際に使用。（「blog_id = ?」この一つだけ）
	Blog findByBlogId(Long blogId);
	
	//DLETE FROM blog WHERE blog_id = ?
	//用途：削除使用します。
	void deleteByBlogId(Long blogId);
	
	
}
