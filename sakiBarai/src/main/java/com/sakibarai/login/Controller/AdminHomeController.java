package com.sakibarai.login.Controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sakibarai.login.domain.model.User;
import com.sakibarai.service.UserService;

@Controller
public class AdminHomeController {
	@Autowired
	UserService userService;
	@Autowired
	HttpSession session;

	@GetMapping("/admin")
	public String getAdmin(Model model) {
		User user = userService.selectOneId((Integer) session.getAttribute("userId"));//ユーザーIDでユーザー名を取得
		model.addAttribute("name", user.getUserName());//ユーザー名を登録
		return "login/admin";
	}
}
