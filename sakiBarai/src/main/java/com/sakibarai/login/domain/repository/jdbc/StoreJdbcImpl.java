package com.sakibarai.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.sakibarai.login.domain.model.Store;
import com.sakibarai.login.domain.repository.StoreDao;

//インターフェースを実装したクラス
//JdbcTemplateはSpringが用意しているため、
//既にBean定義がされている
//そのため、@Autowiredするだけで使えるようになる
//このクラスのメソッドを使って、SQLを実行していく

@Repository
public class StoreJdbcImpl implements StoreDao{
	@Autowired
	NamedParameterJdbcTemplate jdbc;

	//store_infoテーブルのデータを1件取得
	@Override
	public Store selectOneStore(int storeId)throws DataAccessException{
		//SQL文
		String sql = "SELECT * FROM store_info WHERE store_id = :storeId";
		//パラメーター
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("storeId", storeId);
		//SQL実行
		Map<String, Object> map = jdbc.queryForMap(sql, params);
		//結果返却用のインスタンスを生成
		Store store = new Store();
		//取得したデータを結果返却用の変数にセットしていく
		store.setStoreId((Integer)map.get("store_id"));//店舗ID
		store.setStoreUserId((Integer)map.get("store_user_id"));//店舗ユーザーID
		store.setStoreName((String)map.get("store_name"));//店舗名
		store.setAddress1((String)map.get("address1"));//住所1
		store.setAddress2((String)map.get("address2"));//住所2
		store.setAddress3((String)map.get("address3"));//住所3
		store.setAddress4((String)map.get("address4"));//住所4
		store.setBusinessHours((String)map.get("business_hours"));//営業時間
		store.setRegularHoliday((String)map.get("regular_holiday"));//定休日
		store.setStorePhoneNumber((String)map.get("store_phone_number"));//電話番号
		store.setWebpage((String)map.get("wabpage"));//Webページ
		return store;
	}

	//store_infoテーブルのデータを全件取得
	@Override
	public List<Store>selectManyStore()throws DataAccessException{
		// 複数件のselect
		String sql = "SELECT * FROM store_info";
		SqlParameterSource params = new MapSqlParameterSource();
		// SQL実行
		List<Map<String,Object>> getList = jdbc.queryForList(sql, params);
		// 結果返却用のList
		List<Store>storeList = new ArrayList<>();
		// 取得したデータを結果返却用のListに格納していく
		for(Map<String,Object>map:getList){
		// Storeインスタンスの生成
		Store store = new Store();
		// storeインスタンスに取得したデータをセットする
		store.setStoreId((Integer)map.get("store_id"));//店舗ID
		store.setStoreUserId((Integer)map.get("store_user_id"));//店舗ユーザーID
		store.setStoreName((String)map.get("store_name"));//店舗名
		store.setAddress1((String)map.get("address1"));//住所1
		store.setAddress2((String)map.get("address2"));//住所2
		store.setAddress3((String)map.get("address3"));//住所3
		store.setAddress4((String)map.get("address4"));//住所4
		store.setBusinessHours((String)map.get("business_hours"));//営業時間
		store.setRegularHoliday((String)map.get("regular_holiday"));//定休日
		store.setStorePhoneNumber((String)map.get("store_phone_number"));//電話番号
		store.setWebpage((String)map.get("wabpage"));//Webページ
		//結果返却用のListに追加
		storeList.add(store);
		}
		return storeList;
	}
}