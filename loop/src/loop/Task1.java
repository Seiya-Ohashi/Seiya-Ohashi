package loop;

import java.util.Scanner;

public class Task1 {
	public static void main(String[] args) {
		System.out.print("数字を入力してください: ");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		sc.close();
		for(int count = 1, total =0 ; count <= num; count++) {
			total += count;
			if(count == num) {
				System.out.println("1〜" + num + "までの合計: " + total);
			}
		}
	}
}