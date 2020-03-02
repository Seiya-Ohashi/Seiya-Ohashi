package branch;

import java.util.Scanner;

public class Task6 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("1〜5までの数字を入力してください");
		int num = sc.nextInt();
		sc.close();
		String romaNumber ="";

		switch(num){
		case 1:
			romaNumber = "Ⅰ";
			break;
		case 2:
			romaNumber = "Ⅱ";
			break;
		case 3:
			romaNumber = "Ⅲ";
			break;
		case 4:
			romaNumber = "Ⅳ";
			break;
		case 5:
			romaNumber = "Ⅴ";
			break;
		default:
			romaNumber = "unknown";
			break;
		};
		System.out.println(num + " -> " + romaNumber);
	}
}
