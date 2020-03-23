package standardClass;

import java.util.Scanner;

public class Task4 {

	public static void main(String[] args) {
		System.out.print("探したい文字: ");
		Scanner scan = new Scanner(System.in);
		String keyword = scan.nextLine();
		String text = "ABCDGOPQRSYZ";
		scan.close();
		if(text.indexOf(keyword) >= 0) {
			System.out.println(text + "は" + keyword + "を含む");
		}else{
			System.out.println(text + "は" + keyword + "を含まない");
		}
	}

}
