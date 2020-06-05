package com.sakibarai.login.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sakibarai.login.domain.model.Store;
import com.sakibarai.service.StoreService;
@Controller
public class StoreListController {
	@Autowired
	StoreService storeService;
	//ユーザー一覧画面のGET用メソッド
	@GetMapping("/storeList")
	public String getStoreList(Model model){
	//ユーザー一覧の生成
	List<Store> storeList =  storeService.selectManyStore();
	//Modelにユーザーリストを登録
	model.addAttribute("storeList",storeList);
	return "store/storeList";
	}
}
