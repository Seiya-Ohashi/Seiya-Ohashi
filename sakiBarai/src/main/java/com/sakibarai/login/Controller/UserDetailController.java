package com.sakibarai.login.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sakibarai.login.domain.model.User;
import com.sakibarai.login.domain.model.UserDetailForm;
import com.sakibarai.service.UserService;

@Controller
public class UserDetailController {
	@Autowired
	UserService userService;
	@Autowired
	HttpSession session;
	// ユーザー詳細画面のGET用メソッド
	@GetMapping("/userDetail")
	public String getUserDetail(@ModelAttribute UserDetailForm form,
			Model model) {
		//System.out.println(session.getAttribute("userId"));
		//ユーザー情報を取得
		User user = userService.selectOneId((Integer)session.getAttribute("userId"));
		//Userクラスをフォームクラスに変換
		form.setUserId(user.getUserId());//ユーザーID
		form.setUserName(user.getUserName());//ユーザー名
		form.setMailAddress(user.getMailAddress());//メールアドレス
		//form.setPassword(user.getPassword());//パスワード
		//Modelに登録
		model.addAttribute("signupForm", form);
		return "user/userDetail";
	}

	// ボタン名によるメソッド判定
	// ユーザー更新用処理
	//｢更新｣ボタン押下時
	@PostMapping("/userDetail")
	public String postUserDetailUpdate(@ModelAttribute @Validated UserDetailForm form,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()){
			//GETリクエスト用のメソッドを呼び出して、
			// プロフィール編集画面に戻る
			System.out.println(bindingResult);
			return getUserDetail(form, model);
		}
		User id = userService.selectOneId((Integer) session.getAttribute("userId"));
		// Userインスタンスの生成
		User user = new User();
		user.setUserId(id.getUserId());
		user.setUserName(form.getUserName());
		user.setMailAddress(form.getMailAddress());
		user.setPassword(form.getPassword());
		// 更新実行
		boolean result = userService.updateOne(user);
		if (result == true) {
			if(user.getPassword() == "") {
				model.addAttribute("result", "パスワード以外の更新が完了しました");
			}else {
				model.addAttribute("result", "更新が完了しました");
			}
		} else {
			model.addAttribute("result", "更新に失敗しました");
		}
		//ユーザー一覧画面を表示
		return "user/userDetail";
	}
}
//@Validated(GroupOrder.class)