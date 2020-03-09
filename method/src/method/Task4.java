package method;

public class Task4 {

	public static void main(String[] args) {
		String textA[] = {"a", "b" ,"c"};
		String textB[] = {"a", null ,"c"};

		System.out.println("---配列にnullがない場合---");
		System.out.println(nullcheck(textA));
		System.out.println("---配列にnullがある場合---");
		System.out.println(nullcheck(textB));
	}
	public static boolean nullcheck(String[] text) {
		boolean result = false;
		for(int count = 0; count < text.length; count++) {
			if(text[count] == null) {
				result = true;
				break;
			}
		}
		return result;
	}
}
