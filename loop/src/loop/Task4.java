package loop;

import java.util.Scanner;

public class Task4 {
	public static void main(String[] args) {
		System.out.println("正の整数を入力してください");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		sc.close();
		System.out.print("正の約数:");

		for(int count = 1; count <= num; count++){
			if(num % count == 0  && count < num){
				System.out.print(count + ",");
			}
		}
		System.out.print(num);
	}
}
