package com.sakibarai.login.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sakibarai.login.domain.model.User;
import com.sakibarai.login.domain.model.UserDetailForm;
import com.sakibarai.service.UserService;

@Controller
public class UserDetailController {
	@Autowired
	UserService userService;

	String saveName;//DB内の名前を保存(重複確認用)
	String saveMail;//DB内のメールアドレスを保存(重複確認用)

	// ユーザー詳細画面のGET用メソッド
	@GetMapping("/userDetail/{id}")
	public String getUserDetail(@PathVariable("id")int userId, @ModelAttribute UserDetailForm form,
			Model model) {
		if(form.getUserName() == null) {
			// ユーザー情報を取得
			User user = userService.selectOneId(userId);
			// Userクラスをフォームクラスに変換
			form.setUserId(user.getUserId());//ユーザーID
			form.setUserName(user.getUserName());//ユーザー名
			form.setMailAddress(user.getMailAddress());//メールアドレス

			model.addAttribute("userId", userId);
			model.addAttribute("signupForm", form);
			saveName = user.getUserName();
			saveMail = user.getMailAddress();

		}else {
			model.addAttribute("userId", userId);
			model.addAttribute("signupForm", form);
		}
		return "user/userDetail";
	}

	// ボタン名によるメソッド判定
	// ユーザー更新用処理
	// ｢更新｣ボタン押下時
	@PostMapping("/userDetail/{id}")
	public String postUserDetailUpdate(@PathVariable("id")int userId,
			@ModelAttribute @Validated UserDetailForm form,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()){
			// GETリクエスト用のメソッドを呼び出して、
			// プロフィール編集画面に戻る
			return getUserDetail(userId, form, model);
		}
		User id = userService.selectOneId(userId);
		// Userインスタンスの生成
		User user = new User();
		user.setUserId(id.getUserId());
		user.setUserName(form.getUserName());
		user.setMailAddress(form.getMailAddress());
		user.setPassword(form.getPassword());
		//重複チェック(重複していたらtrue)
		boolean nameExists = userService.selectDuplicationName(user.getUserName());
		boolean mailExists = userService.selectDuplicationMail(user.getMailAddress());
		//入力したユーザー名とメールアドレスが、
		//別のアカウントと被っていたらエラー
		if(nameExists == true && mailExists == true
			&& !saveName.equals(user.getUserName())
			&& !saveMail.equals(user.getMailAddress())){
			model.addAttribute("nameExists", "ユーザー名が重複しています");
			model.addAttribute("mailExists", "メールアドレスが重複しています");
			return getUserDetail(userId, form, model);
		//入力したユーザー名が別のアカウントと被っていたらエラー
		}else if(nameExists == true && !saveName.equals(user.getUserName())){
			model.addAttribute("nameExists", "ユーザー名が重複しています");
			return getUserDetail(userId, form, model);
		//入力したメールアドレスが別のアカウントと被っていたらエラー
		}else if(mailExists == true && !saveMail.equals(user.getMailAddress())){
			model.addAttribute("mailExists", "メールアドレスが重複しています");
			return getUserDetail(userId, form, model);
		}else{
			// 更新実行
			boolean result = userService.updateOne(user);
			// パスワード欄未入力→パスワード以外更新 入力済→すべて更新
			if (result == true) {
				if(user.getPassword() == "") {
					model.addAttribute("result", "パスワード以外の更新が完了しました");
				}else {
					model.addAttribute("result", "更新が完了しました");
				}
			} else {
				model.addAttribute("result", "更新に失敗しました");
			}
		}
		return getUserDetail(userId, form, model);
	}
}