package branch;

import java.util.Scanner;

public class Task3 {
	public static void main(String[] args) {
		System.out.println("数字を入力してください");
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		sc.close();

		if(a > 0){
			System.out.println("入力された数字は正の数です。");
		}else if(a < 0){
			System.out.println("入力された数字は負の数です。");
		}else{
			System.out.println("入力された数字は0です。");
		}
	}
}