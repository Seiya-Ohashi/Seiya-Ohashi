package loop;

import java.util.Scanner;

public class Task3 {
	public static void main(String[] args) {
		System.out.print("数字を入力してください: ");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		sc.close();

		int digit = 0;
		while(num > 0){
			num /= 10;
			digit++;
		}
		System.out.println(digit);
	}
}