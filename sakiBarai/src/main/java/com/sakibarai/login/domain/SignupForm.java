package com.sakibarai.login.domain;

import lombok.Data;

//ユーザー登録用フォームクラス

@Data//getterやsetterなどを自動で作成(lombok)
public class SignupForm {
	//ユーザーID
	private int userId;
	//ユーザー名
	private String userName;
	//メールアドレス
	private String mailAddress;
	//パスワード
	private String password;
	//アカウントカテゴリー
	private int userCategoryFlag;
}