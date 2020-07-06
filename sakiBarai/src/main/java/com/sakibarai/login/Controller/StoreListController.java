package com.sakibarai.login.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sakibarai.login.domain.model.Store;
import com.sakibarai.service.StoreService;
@Controller
public class StoreListController {
	@Autowired
	StoreService storeService;
	@Autowired
	HttpSession session;
	//店舗一覧画面のGET用メソッド
	//a@GetMapping("/storeList")
	//apublic String getStoreList(Model model){
	//店舗一覧の生成
	//aList<Store> storeList =  storeService.selectManyStore();
	//Modelにユーザーリストを登録
	//amodel.addAttribute("storeList",storeList);
	//areturn "store/storeList";
	//a}

	// 6件まで表示(表示の上限)
	int limitNum = 6;
	// LIMIT句に使う(○番目以降のデータを表示させるか指定する)
	int dataNum;

	// 店舗一覧画面のGET用メソッド
	@GetMapping("/storeList")
	public String getStoreListLimit(Model model){
		// 店舗数÷上限で店舗一覧を何ページ用意するか決める
		// (例: 19件 ÷ 表示上限6件 = 3.1666... → 店舗一覧画面を4ページ用意する)
		int pageTotal = (int)Math.ceil(storeService.storeCount() / (double)limitNum);
		int[] pageArray = new int[pageTotal];
		// LIMIT句に使う(○番目以降のデータを表示させるか指定する)
		dataNum = 0;
		// 店舗一覧の生成
		List<Store> storeList =  storeService.selectManyStoreLimit(dataNum,limitNum);
		//Modelに店舗リストを登録
		model.addAttribute("pagePrev",0);
		model.addAttribute("pageArray",pageArray);
		model.addAttribute("storeList",storeList);
		model.addAttribute("pageNext",2);
		model.addAttribute("pageTotal",pageTotal);
		model.addAttribute("pageCurrent",1);
		model.addAttribute("adminContents", "store/storeList :: storeList_contents");
		if(session.getAttribute("role").equals("ROLE_ADMIN")){
			return "login/adminHomeLayout";
		}else{
			return "store/storeList";
		}
	}
	@GetMapping("/storeList/{result}")
	public String getStoreListaaa(@PathVariable("result") String searchResult, Model model) {
		model.addAttribute("aaa",searchResult);
		System.out.print(searchResult);

		return "store/storeList";
	}

	@RequestMapping("/storeList/{arrayStat.count}")
	public String postStoreListLimit(@PathVariable("arrayStat.count")int page, Model model){
		// 店舗数÷上限で店舗一覧を何ページ用意するか決める
		// (例: 19件 ÷ 表示上限6件 = 3.1666... → 店舗一覧画面を4ページ用意する)
		int pageTotal = (int)Math.ceil(storeService.storeCount() / (double)limitNum);
		int[] pageArray = new int[pageTotal];
		// LIMIT句に使う(○番目以降のデータを表示させるか指定する)
		dataNum = limitNum * (page-1);
		// 1ページ目から｢6｣番目以降のデータが表示されることを回避したい(page-1で回避出来たからコメントアウト)
		//dataNum -= 6;
		// 店舗一覧の生成
		List<Store> storeList =  storeService.selectManyStoreLimit(dataNum,limitNum);
		// Modelに店舗一覧画面用の各値を登録
		model.addAttribute("pagePrev",page-1);		// ｢前へ｣ボタン用の値を登録
		model.addAttribute("pageArray",pageArray);	// ページャー用の値を登録
		model.addAttribute("storeList",storeList);	// 店舗リストを登録
		model.addAttribute("pageNext",page+1);		// ｢次へ｣ボタン用の値を登録
		model.addAttribute("pageTotal",pageTotal);	// ｢次へ｣ボタン用の値を登録(最後のページで押せないようにする為)
		model.addAttribute("pageCurrent",page);		// ボタンをアクティブ表示にする為
		model.addAttribute("adminContents", "store/storeList :: storeList_contents");
		if(session.getAttribute("role").equals("ROLE_ADMIN")){
			return "login/adminHomeLayout";
		}else{
			return "store/storeList";
		}
	}
}
