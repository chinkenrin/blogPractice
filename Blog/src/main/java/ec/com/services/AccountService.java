package ec.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.models.dao.AccountDao;
import ec.com.models.entity.Account;

@Service
public class AccountService {
	@Autowired
	private AccountDao accountDao;
	// 保存処理（登録処理）
	// もし、findByAccountEmail==nullだったら登録処理をします。
	// saveメソッドを使用して登録処理をする
	// 保存ができたらtrueを返す（大丈夫の意味）
	// そうでない場合、保存処理失敗 false

	public boolean createAccount(String accountEmail, String accountName, String password) {
		if (accountDao.findByAccountEmail(accountEmail) == null) {
			accountDao.save(new Account(accountEmail, accountName, password));
			return true;
		} else {
			return false;
		}
	}

	// ログイン処理（存在しないデータがログインできるとまずい！）
	// もし、emailとpasswordがfindByAccountEmailAndPasswordを使用して存在しなかった場合==nullの場合。（存在しないので、nullに表示）
	// その場合は、存在しないnullであることをコントローラークラスに知らせる
	// そうでない場合ログインしている人の情報をコントローラークラスに渡す
	public Account loginCheck(String accountEmail, String password) {
		Account account = accountDao.findByAccountEmailAndPassword(accountEmail, password);
		if (account == null) {
			return null;
		} else {
			return account;
		}
	}

}
