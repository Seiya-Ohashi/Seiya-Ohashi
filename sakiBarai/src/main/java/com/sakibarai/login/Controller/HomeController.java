package com.sakibarai.login.Controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sakibarai.login.domain.model.User;
import com.sakibarai.service.UserService;

@Controller
public class HomeController {
	@Autowired
	UserService userService;
	@Autowired
	HttpSession session;

	//ホームのGET用メソッド
	//初回ホーム遷移時、Principalからログイン時に入力したメールアドレスを取得
	//メールアドレスをもとに、
	//SELECT文でユーザー名を取得してホームにユーザー名を表示
	//2回目以降のホーム遷移時は、ユーザーIDでユーザー名を取得して表示
	@GetMapping("/home")
	public String getHome(Model model, Principal principal) {
		System.out.println("ユーザーID" + session.getAttribute("userId"));//確認用
		//ホーム初回遷移時
		//(sessionに｢ユーザーID｣を入れる、model(name)に｢ユーザー名｣を入れる)
		if (session.getAttribute("userId") == null) {
			User user = userService.selectOne(principal.getName());//メールアドレスでユーザー名を取得
			session.setAttribute("userId", user.getUserId());
			model.addAttribute("name", user.getUserName());//ユーザー名を登録
		} else {
			//ホーム2回目以降遷移時
			User user = userService.selectOneId((Integer) session.getAttribute("userId"));//ユーザーIDでユーザー名を取得
			model.addAttribute("name", user.getUserName());//ユーザー名を登録
		}
		return "login/home";
	}

	//ログアウト用メソッド
	@GetMapping("/logout")
	public String postLogout() {
		session.invalidate();
		//ログイン画面にリダイレクト
		return "redirect:/login";
	}

	@GetMapping("/admin")
	public String getAdmin() {
		return "login/admin";
	}
}
