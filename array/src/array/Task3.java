package array;

import java.util.Random;

public class Task3 {
	public static void main(String[] args) {
		int max = 0;
		int min = 0;
		int[] numbers = new int[6];
		Random random = new Random();
		System.out.print("[");

		for(int i = 0; i < numbers.length; i++){
			if(i == 0){
				numbers[i] = random.nextInt(9);
				min = numbers[i];
			}else{
				numbers[i] = random.nextInt(9);
			}if(numbers[i] > max){
				max = numbers[i];
			}else if(numbers[i] < min){
				min = numbers[i];
			}
		}for(int j = 0; j < numbers.length -1 ; j++){
			System.out.print(numbers[j] + ",");
		}System.out.println(numbers[5] + "]");
		System.out.println("最大値: " + max);
		System.out.println("最小値: " + min);
	}
}
