package com.sakibarai.login.domain.model;

import lombok.Data;

//データベースから取得した値を、コントローラークラスやサービスクラスなどの間でやり取りする
//store_infoテーブルのカラムをフィールドに持つ

@Data
public class Store{
	//店舗ID
	private int storeId;
	//店舗ユーザーID
	private int storeUserId;
	//店舗ユーザー名
	private String storeName;
	//住所
	private String address1;
	private String address2;
	private String address3;
	private String address4;
	//営業時間
	private String businessHours;
	//定休日
	private String regularHoliday;
	//電話番号
	private String storePhoneNumber;
	//Webページ
	private String webpage;
}