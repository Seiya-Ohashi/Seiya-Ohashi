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
		User user = userService.selectOne(principal.getName());//ユーザー名を取得
		model.addAttribute("name", user.getUserName());//ユーザー名を登録
		return "login/home";
	}
	// ユーザー詳細画面のGET用メソッド
	@GetMapping("/userDetail")
	public String getUserDetail(@ModelAttribute SignupForm form,
		Model model, Principal principal) {
		//ユーザー情報を取得
		User user = userService.selectOne(principal.getName());
		//Userクラスをフォームクラスに変換
		form.setUserId(user.getUserId());//ユーザーID
		form.setUserName(user.getUserName());//ユーザー名
		form.setMailAddress(user.getMailAddress());//メールアドレス
		form.setPassword(user.getPassword());//パスワード
		//Modelに登録
		model.addAttribute("signupForm", form);
		return "login/userDetail";
	}
	//---------------------------------------------------------------
	// ボタン名によるメソッド判定
	// ユーザー更新用処理	5/22時点で未完成･現在着手している所
	//｢更新｣ボタン押下時
	@PostMapping(value = "/userDetail", params = "update")
	public String postUserDetailUpdate(@ModelAttribute SignupForm form,
		Model model, Principal principal){
		User id = userService.selectOne(principal.getName());
		// Userインスタンスの生成
		User user = new User();
		user.setUserId(id.getUserId());
		user.setUserName(form.getUserName());
		user.setMailAddress(form.getMailAddress());
		user.setPassword(form.getPassword());
		// 更新実行
		boolean result = userService.updateOne(user);
		if(result == true){
			model.addAttribute("result","更新成功");
			}else{//
			model.addAttribute("result","更新失敗");
			}
		System.out.println(user+"@@"+result);//確認用
		//ユーザー一覧画面を表示
		return "login/userDetail";
	}
	@PostMapping(value = "/userDetail", params = "back")
	public String postHome(Model model, Principal principal){
		User user = userService.selectOne(principal.getName());//ユーザー名を取得
		model.addAttribute("name", user.getUserName());//ユーザー名を登録
		//ホーム画面を表示
		return "login/home";
	}
	//---------------------------------------------------------------
	//ログアウト用メソッド
	@GetMapping("/logout")
	public String postLogout() {
		//ログイン画面にリダイレクト
		return "redirect:/login";
	}
}
