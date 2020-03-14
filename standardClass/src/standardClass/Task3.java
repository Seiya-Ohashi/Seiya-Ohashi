package standardClass;

public class Task3 {

	public static void main(String[] args) {
		System.out.println("文字列: ABCDEFG");
		String text = "文字列 :ABCDEFG";
		for(int num = text.length()-1; num >= text.indexOf("文"); num--) {
			System.out.print(text.charAt(num));
		}
	}

}
