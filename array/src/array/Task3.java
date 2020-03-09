package array;

import java.util.Random;

public class Task3 {
	public static void main(String[] args) {
		int max = 0;
		int min = 0;
		int[] numbers = new int[6];
		Random random = new Random();
		System.out.print("[");

		for(int i = 0; i < numbers.length; i++){	//配列にランダムな値を格納
			if(i == 0){
				numbers[i] = random.nextInt(9);
				min = numbers[i];					//最小値0が確定になるのを防止
			}else{
				numbers[i] = random.nextInt(9);
			}if(numbers[i] > max){
				max = numbers[i];					//最大値判定
			}else if(numbers[i] < min){
				min = numbers[i];					//最小値判定
			}
		}
		for(int j = 0; j < numbers.length -1 ; j++){
			System.out.print(numbers[j] + ",");		//要素5個カンマ区切りで表示
		}
		System.out.println(numbers[5] + "]");
		System.out.println("最大値: " + max);
		System.out.println("最小値: " + min);
	}
}
