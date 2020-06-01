package com.sakibarai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakibarai.login.domain.model.User;
import com.sakibarai.login.domain.repository.UserDao;

@Service
public class UserService{
	@Autowired
	UserDao dao;

	// ユーザー名重複確認
	public boolean selectDuplicationName(String userName){
		boolean nameExists = dao.selectDuplicationName(userName);
		return nameExists;
	}

	// メールアドレス重複確認
	public boolean selectDuplicationMail(String mailAddress){
		boolean mailExists = dao.selectDuplicationMail(mailAddress);
		return mailExists;
	}

	// insert
	public boolean insert(User user){
		// insert実行
		int rowNumber = dao.insertOne(user);
		// 判定用変数
		boolean result = false;
		if(rowNumber > 0){
			// insert成功
			result = true;
		}
		return result;
	}
	// 1件取得(ログイン後ホーム遷移時のみ使用)
	public User selectOne(String mailAddress){
		return dao.selectOne(mailAddress);
	}

	// 1件取得
	public User selectOneId(int userId){
		return dao.selectOneId(userId);
	}

	// 1件更新
	public boolean updateOne(User user){
		// 1件更新
		int rowNumber = dao.updateOne(user);
		// 判定用変数
		boolean result = false;
		if(rowNumber > 0){
			// insert同様、0より大きい値が入ってきたら
			// update成功
			result = true;
		}
		return result;
	}
	// 全件取得
   public List<User> selectMany() {
       // 全件取得
       return dao.selectMany();
   }

   // 1件削除
   public boolean deleteOne(int userId) {

       // 1件削除
       int rowNumber = dao.deleteOne(userId);

       // 判定用変数
       boolean result = false;

       if (rowNumber > 0) {
           // delete成功
           result = true;
       }
       return result;
   }
}
// サービスクラスのinsertメソッドでは、
// リポジトリークラスのinsertOneメソッドを呼び出す
// 戻り値が0より大きければ、insertが成功したという判定結果をリターンする