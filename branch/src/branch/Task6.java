package branch;

import java.util.Scanner;

public class Task6 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		sc.close();
		String str = String.valueOf(i);

		switch(i){
		case 1:
			str = "Ⅰ";
			break;
		case 2:
			str = "Ⅱ";
			break;
		case 3:
			str = "Ⅲ";
			break;
		case 4:
			str = "Ⅳ";
			break;
		case 5:
			str = "Ⅴ";
			break;
		default:
			str = "unknown";
			break;
		};
		System.out.println(i + " -> " + str);
	}
}
