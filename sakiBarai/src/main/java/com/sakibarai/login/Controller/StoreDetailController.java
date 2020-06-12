package com.sakibarai.login.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sakibarai.login.domain.model.Store;
import com.sakibarai.service.StoreService;

@Controller
public class StoreDetailController {
	@Autowired
	StoreService storeService;
	@Autowired
	HttpSession session;

	@GetMapping("/storeDetail/{id}")
	public String getStoreDetail(@PathVariable("id")int storeId, Model model) {
		// 店舗情報を取得
		Store store = storeService.selectOneStore(storeId);
		model.addAttribute("store", store);
		model.addAttribute("adminContents", "store/storeDetail :: storeDetail_contents");
		if(session.getAttribute("role").equals("ROLE_ADMIN")) {
			return "login/adminHomeLayout";
		}else{
			return "store/storeDetail";
		}
	}
}
