package com.sakibarai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakibarai.login.domain.model.User;
import com.sakibarai.login.domain.repository.UserDao;

@Service
public class UserService{
	@Autowired
	UserDao dao;

	// insert用メソッド
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
	// 1件取得用メソッド
	public User selectOne(String mailAddress){
		return dao.selectOne(mailAddress);
	}

	//１件更新メソッド
	public boolean updateOne(User user){
		//１件更新
		int rowNumber = dao.updateOne(user);
		//判定用変数
		boolean result = false;
		if(rowNumber > 0){
			// insert同様、0より大きい値が入ってきたら
			// update成功
			result = true;
		}
		return result;
	}
}
// サービスクラスのinsertメソッドでは、
// リポジトリークラスのinsertOneメソッドを呼び出す
// 戻り値が0より大きければ、insertが成功したという判定結果をリターンする