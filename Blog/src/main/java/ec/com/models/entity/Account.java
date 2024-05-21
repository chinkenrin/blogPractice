package ec.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {
//account_idの設定
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId;
	
	//account_email
	private String accountEmail;
	
	//account_name
	private String accountName;
	
	//password
	private String password;

	// 空のコンストラクタ
	public Account() {
	}

	// コンストラクタ
	public Account(String accountEmail, String accountName, String password) {
		this.accountEmail = accountEmail;
		this.accountName = accountName;
		this.password = password;
	}

	// ゲッターとセッター
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	
	
}
