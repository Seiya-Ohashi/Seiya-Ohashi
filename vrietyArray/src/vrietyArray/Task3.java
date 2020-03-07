package vrietyArray;

import java.util.HashMap;
import java.util.Map;

public class Task3 {

	public static void main(String[] args) {
		Map<String, Integer> fruit = new HashMap<>();
		fruit.put("りんご" ,130);
		fruit.put("メロン" ,6000);
		fruit.put("みかん" ,120);
		fruit.put("バナナ" ,98);
		System.out.println("りんご:" + fruit.get("りんご") + "円");
		System.out.println("メロン:" + fruit.get("メロン") + "円");
		System.out.println("みかん:" + fruit.get("みかん") + "円");
		System.out.println("バナナ:" + fruit.get("バナナ") + "円");
	}

}
