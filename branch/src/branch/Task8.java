package branch;

import java.util.Scanner;

public class Task8 {
	public static void main(String[] args) {
		System.out.println("数字を入力してください");
		Scanner sc = new Scanner(System.in);
		int numA = sc.nextInt();
		int numB = sc.nextInt();
		int numC = sc.nextInt();
		sc.close();

		System.out.println("numA: " + numA);
		System.out.println("numB: " + numB);
		System.out.println("numC: " + numC);

		if(numA >= numB && numA <= numC){
			System.out.println("中央値: " + numA);
		}else if(numA <= numB && numA >= numC){
			System.out.println("中央値: " + numA);

		}if(numB >= numA && numB <= numC){
			System.out.println("中央値: " + numB);
		}else if(numB <= numA && numB >= numC){
			System.out.println("中央値: " + numB);

		}if(numC >= numA && numC <= numB){
			System.out.println("中央値: " + numC);
		}else if(numC <= numA && numC >= numB){
			System.out.println("中央値: " + numC);
		}
	}
}