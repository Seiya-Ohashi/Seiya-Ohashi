package com.sakibarai.login.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sakibarai.login.domain.SignupForm;
import com.sakibarai.login.domain.model.User;
import com.sakibarai.service.UserService;

@Controller
public class HomeController {
	@Autowired
	UserService userService;
	//ホームのGET用メソッド
	//ホーム遷移時、Principalからログイン時に入力したメールアドレスを取得
	//メールアドレスをもとに、
	//SELECT文でユーザー名を取得してホームにユーザー名を表示
	@GetMapping("/home")
	public String getHome(Model model, Principal principal){
		String mailAddress = principal.getName();//メールアドレスを取得
		User user = userService.selectOne(mailAddress);//ユーザー名を取得
		model.addAttribute("name", user.getUserName());//ユーザー名を登録
		System.out.println(model.getAttribute("name"));//確認用
		return "login/home";
	}
	// ユーザー詳細画面のGET用メソッド
	@GetMapping("/userDetail")
	public String getUserDetail(@ModelAttribute SignupForm form,
			Model model, Principal principal) {
		String mailAddress = principal.getName();
		//メールアドレスのチェック
		if (mailAddress != null && mailAddress.length() > 0) {
			//ユーザー情報を取得
			User user = userService.selectOne(mailAddress);
			//Userクラスをフォームクラスに変換
			form.setUserId(user.getUserId());//ユーザー名
			form.setUserName(user.getUserName());//ユーザー名
			form.setMailAddress(user.getMailAddress());//メールアドレス
			form.setPassword(user.getPassword());//パスワード
			//Modelに登録
			model.addAttribute("signupForm", form);
		}
		return "login/userDetail";
	}
	//---------------------------------------------------------------
	// ボタン名によるメソッド判定
	// ユーザー更新用処理	5/22時点で未完成･現在着手している所
	//｢更新｣ボタン押下時
	@PostMapping(value = "/userDetail", params = "update")
	public String postUserDetailUpdate(@ModelAttribute SignupForm form,
			Model model){
		System.out.println(form);
		// Userインスタンスの生成
		User user = new User();
		// フォームクラスをUserクラスに変換
		user.setUserId(form.getUserId());
		user.setUserName(form.getUserName());
		user.setMailAddress(form.getMailAddress());
		user.setPassword(form.getPassword());
		// 更新実行
		boolean result = userService.updateOne(user);
		if(result == true){
			model.addAttribute("result","更新成功");
			}else{
			model.addAttribute("result","更新失敗");
			}
		System.out.println(user+"@@"+result);//確認用
		//ユーザー一覧画面を表示
		return "login/userDetail";
	}
	//---------------------------------------------------------------
	//ログアウト用メソッド
	@GetMapping("/logout")
	public String postLogout() {
		//ログイン画面にリダイレクト
		return "redirect:/login";
	}
}
