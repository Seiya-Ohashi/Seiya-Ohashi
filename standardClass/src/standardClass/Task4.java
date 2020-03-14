package standardClass;

import java.util.Scanner;

public class Task4 {

	public static void main(String[] args) {
		System.out.print("探したい文字: ");
		String keyword = new Scanner(System.in).nextLine();
		String text = "ABCDGOPQRSYZ";
		System.out.println(keyword);
		if(text.indexOf(keyword) >= 0) {
			System.out.println(text + "は" + keyword + "を含む");
		}else{
			System.out.println(text + "は" + keyword + "を含まない");
		}
	}

}
