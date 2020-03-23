package branch;

import java.util.Scanner;

public class Task5 {
	public static void main(String[] args) {
		int max = 0;
		System.out.println("数字を入力してください");
		Scanner sc = new Scanner(System.in);
		System.out.print("numA: ");
		int numA = sc.nextInt();
		System.out.print("numB: ");
		int numB = sc.nextInt();

		if(numA < numB){
			max = numB;
		}else{
			max = numA;
		}
		System.out.print("numC: ");
		int numC = sc.nextInt();
		if(max < numC){
			max = numC;
		}
		System.out.println("最大値 " + max);
		sc.close();
	}
}