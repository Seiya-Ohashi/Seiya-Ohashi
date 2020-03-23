package standardClass;

public class Task3 {

	public static void main(String[] args) {
		String text = "文字列 :ABCDEFG";
		System.out.println(text);
		for(int num = text.length()-1; num >= 0; num--) {
			System.out.print(text.charAt(num));
		}
	}

}
