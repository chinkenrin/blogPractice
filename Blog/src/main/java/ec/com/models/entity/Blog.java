package ec.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Blog {
//blog_idの設定
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long blogId;
	
	// blog_titleの設定
	private String blogTitle;
	
	// category_nameの設定
	private String categoryName;
	
	// blog_imageの設定
	private String blogImage;
	
	// ariticleの設定
	private String ariticle;
	
	// account_idの設定
	private Long accountId;

	// 空のコンストラクタ
	public Blog() {
	}

	// コンストラクタ
	public Blog(String blogTitle, String categoryName, String blogImage, String ariticle, Long accountId) {
		this.blogTitle = blogTitle;
		this.categoryName = categoryName;
		this.blogImage = blogImage;
		this.ariticle = ariticle;
		this.accountId = accountId;
	}
	
	// ゲッターとセッター
	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBlogImage() {
		return blogImage;
	}

	public void setBlogImage(String blogImage) {
		this.blogImage = blogImage;
	}

	public String getAriticle() {
		return ariticle;
	}

	public void setAriticle(String ariticle) {
		this.ariticle = ariticle;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	

	
	
	
	
	
	
	
}
