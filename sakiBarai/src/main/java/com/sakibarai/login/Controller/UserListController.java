package com.sakibarai.login.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sakibarai.login.domain.model.User;
import com.sakibarai.service.UserService;

@Controller
public class UserListController {

	@Autowired
	UserService userService;
	//ユーザー一覧画面のGET用メソッド
	@GetMapping("/userList")
	public String getUserList(Model model){
	//ユーザー一覧の生成
	List<User> userList =  userService.selectMany();
	//Modelにユーザーリストを登録
	model.addAttribute("userList",userList);
	model.addAttribute("adminContents", "user/userList :: userList_contents");
	return "login/adminHomeLayout";
	}

	@PostMapping("/userList")
	public String postUserDelete(@RequestParam("id")int userId, Model model) {
		userService.selectOneId(userId);
		// 削除実行
		boolean result = userService.deleteOne(userId);
		if (result == true) {
			model.addAttribute("result", "削除に成功しました");
		} else {
			model.addAttribute("result", "削除に失敗しました");
		}
		return getUserList(model);
	}
}
