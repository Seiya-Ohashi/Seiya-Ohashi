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
		for(HashMap.Entry<String, Integer> num : fruit.entrySet()) {
			System.out.println(num.getKey() + ":" + num.getValue() + "円");
		}
	}

}
