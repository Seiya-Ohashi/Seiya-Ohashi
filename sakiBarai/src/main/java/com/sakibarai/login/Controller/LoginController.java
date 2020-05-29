package com.sakibarai.login.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// http://localhost:8080/login
// 上記URLに GETメソッド、POSTメソッドでHTTPリクエストが来たら、
// ｢login｣フォルダー配下の｢login.html｣を表示する

@Controller
public class LoginController{
	// ログイン画面のGET用コントローラー
	// (http://localhost:8080/loginにGETメソッドでHTTPリクエストが送られて来たら)
	@GetMapping("/login")
	public String getLogin(){
		// login.htmlを表示(src/main/resources/templates/loginというフォルダーの中のlogin.htmlを表示)
		return"login/login";
	}
}