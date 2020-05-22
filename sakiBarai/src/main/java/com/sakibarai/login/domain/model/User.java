package com.sakibarai.login.domain.model;

import lombok.Data;

//データベースから取得した値を、コントローラークラスやサービスクラスなどの間でやり取りする
//ユーザーテーブルのカラムをフィールドに持つ

@Data
public class User{
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