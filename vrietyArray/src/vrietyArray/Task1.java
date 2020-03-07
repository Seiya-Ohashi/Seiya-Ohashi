package vrietyArray;

import java.util.ArrayList;

public class Task1{
	public static void main(String[] args){
		ArrayList<String> areaJapan = new ArrayList<>();
		areaJapan.add("北海道");
		areaJapan.add("東北");
		areaJapan.add("関東");
		areaJapan.add("中部");
		areaJapan.add("近畿");
		areaJapan.add("中国");
		areaJapan.add("九州");

		for(String area : areaJapan){
			System.out.println(area);
		}
	}
}
