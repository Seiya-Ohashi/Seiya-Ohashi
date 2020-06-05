package com.sakibarai.login.Controller;

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

	@GetMapping("/storeDetail/{id}")
	public String getStoreDetail(@PathVariable("id")int storeId, Model model) {
		// 店舗情報を取得
		Store store = storeService.selectOneStore(storeId);
		model.addAttribute("store", store);
		return "store/storeDetail";
	}
}
