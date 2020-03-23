package branch;

import java.util.Scanner;

public class Task1 {
	public static void main(String[] args) {
		System.out.println("10以上の数字を入力してください");
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		sc.close();

		if(number >= 10){
			System.out.println("値: " + number);
		}else if(number < 10){
			System.out.println("値: " + number * 10);
		}
	}
}