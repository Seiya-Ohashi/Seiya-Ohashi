package calc;

import java.util.Scanner;

public class task5 {
	public static void main(String[] args) {
		String pc = "ノートPCの値段:";
		char unit = '円';
		int price = 89800;
		System.out.println(pc + price + unit);

		Scanner sc = new Scanner(System.in);
		String str1 = sc.nextLine();
		String str2 = sc.nextLine();
		sc.close();
		System.out.println(str1 + str2);
	}
}