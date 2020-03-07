package vrietyArray;

import java.util.ArrayList;
import java.util.HashMap;

public class Task4 {

	public static void main(String[] args) {
		int[] numbers= new int[]{21,3,32,6,99,72,78,51,1,26,87,11,48,60,29,5,46,74,14,193,215,83};
		ArrayList<Integer> oddNumber = new ArrayList<>();
		ArrayList<Integer> eveNumber = new ArrayList<>();
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] % 2 == 0){
				eveNumber.add(numbers[i]);
			}else{
				oddNumber.add(numbers[i]);
			}
		}
		HashMap<String,ArrayList<Integer>>numberEveOdd  = new HashMap<>();
		numberEveOdd.put("偶数: ",eveNumber);
		numberEveOdd.put("%n  奇数: ",oddNumber);
		System.out.printf(numberEveOdd + "%n");
	}
}
