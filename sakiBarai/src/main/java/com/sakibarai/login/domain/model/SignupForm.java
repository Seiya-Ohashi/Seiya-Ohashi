package com.sakibarai.login.domain.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

//ユーザー登録用フォームクラス
//@NotBlank	文字列がnull、空文字、空白スペースのみでないことをチェック
//@Email	文字列がメールアドレス形式かどうか
//@Pattern	指定した正規表現に一致するかどうか
//@Length	文字列の長さが指定した範囲内であるか
//@GroupSequenceの挙動が思ってたのと違う為、エラー順番表示は保留
@Data//getterやsetterなどを自動で作成(lombok)
public class SignupForm {
	//ユーザーID
	private int userId;

	@NotBlank//(groups = ValidGroup1.class)//未記入チェック
	@Length(min = 1, max = 32)//, groups = ValidGroup2.class)//文字数
	//ユーザー名
	private String userName;

	@NotBlank//(groups = ValidGroup1.class)//未記入チェック
	@Email//(groups = ValidGroup2.class)//メールアドレスかどうか
	private String mailAddress;

	@NotBlank//(groups = ValidGroup1.class)//未記入チェック
	@Length(min = 1, max = 16)//, groups = ValidGroup2.class)//文字数
	@Pattern(regexp = "^[a-zA-Z0-9]+$")//, groups = ValidGroup3.class)//英数字のみ
	//パスワード
	private String password;

	//アカウントカテゴリー
	private int userCategoryFlag;
}