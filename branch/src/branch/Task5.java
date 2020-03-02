package branch;

import java.util.Scanner;

public class Task5 {
	public static void main(String[] args) {
		System.out.println("数字を入力してください");
		Scanner sc = new Scanner(System.in);
		System.out.print("numA: ");
		int numA = sc.nextInt();
		System.out.print("numB: ");
		int numB = sc.nextInt();
		if(numA < numB){
			numA = numB;
		}System.out.print("numC: ");
		int numC = sc.nextInt();
		if(numA < numC){
			numA = numC;
		}System.out.println("最大値 " + numA);
		sc.close();
	}
}