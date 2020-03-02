package calc;

import java.util.Scanner;

public class task3 {
	public static void main(String[] args) {
		System.out.println("入力");
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		sc.close();
		int total = a + b + c;
		int averageInt = ((a + b + c) / 3);
		Double averageDouble =((a + b + c) / 3.0);

		System.out.println("合計:" + total);
		System.out.println("平均(3で割った時):" + averageInt);
		System.out.println("平均(3.0で割った時):" + averageDouble);
	}
}