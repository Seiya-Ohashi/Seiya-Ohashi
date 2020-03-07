package vrietyArray;

import java.util.ArrayList;

public class Task2 {
	public static void main(String[] args) {
		int[] numbers= new int[]{21,3,32,6,99,72,78,51,1,26,87,11,48,60};
		ArrayList<Integer> oddNumber = new ArrayList<>();
		ArrayList<Integer> eveNumber = new ArrayList<>();
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] % 2 == 0){
				eveNumber.add(numbers[i]);
			}else{
				oddNumber.add(numbers[i]);
			}
		}System.out.print("奇数:");
		System.out.println(oddNumber);
		System.out.print("偶数:");
		System.out.println(eveNumber);
	}
}
