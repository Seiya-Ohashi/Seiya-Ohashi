package com.sakibarai.login.domain.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

//プロフィール編集用フォームクラス
//下記の目的の為に作成
//パスワードが空文字の時→パスワード以外を更新
//パスワード欄に半角英数字1文字以上→パスワードも更新

@Data//getterやsetterなどを自動で作成(lombok)
public class UserDetailForm {
	//ユーザーID
	private int userId;

	@NotBlank//(groups = ValidGroup1.class)//未記入チェック
	@Length(min = 1, max = 32)//, groups = ValidGroup2.class)//文字数
	//ユーザー名
	private String userName;

	@NotBlank//(groups = ValidGroup1.class)//未記入チェック
	@Email//(groups = ValidGroup2.class)//メールアドレスかどうか
	private String mailAddress;

	@NotNull//(groups = ValidGroup1.class)//未記入チェック
	@Length(min = 0, max = 16)//, groups = ValidGroup2.class)//文字数
	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	//@Pattern(regexp = "^[a-zA-Z0-9]+$")//, groups = ValidGroup3.class)//英数字のみ
	//パスワード
	private String password;

	//アカウント権限
	private String role;
}