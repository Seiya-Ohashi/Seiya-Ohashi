package branch;

import java.util.Scanner;

public class Task4 {
	public static void main(String[] args) {
		System.out.println("数字を入力してください");
		Scanner sc = new Scanner(System.in);
		System.out.print("numA: ");
		int numA = sc.nextInt();
		System.out.print("numB: ");
		int numB = sc.nextInt();
		sc.close();

		if(numA <= 0 || numB <= 0){
			System.out.println("正の数を入力してください");
		}else if(numA % numB == 0){
			System.out.println("numBはnumAの約数です");
		}else{
			System.out.println("numBはnumAの約数ではありません");
		}
	}
}