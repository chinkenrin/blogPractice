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
	
	//SELECT * FROM blog
	//用途：商品の一覧を表示させるときに使用(いっぱい複数を取るため。「findAll」は同じ意味)
	List<Blog>findAll();
	
	//SELECT * FROM blog WHERE category_name = ?
	//用途：商品の登録チェックに使用（同じ商品名が登録されないようにするチェックに使用）
	Blog findByCategoryName(String categoryName);
	
	//SELECT * FROM blog WHERE blog_id = ?
	//用途：編集画面を表示する際に使用。（「blog_id = ?」この一つだけ）
	Blog findByBlogId(Long blogID);
	
	//DLETE FROM blog WHERE blog_id = ?
	//用途：削除使用します。
	void deleteByBlogId(Long blogID);
	
	
}
