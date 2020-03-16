package method;

public class Task4 {

	public static void main(String[] args) {
		String textA[] = {"a", "b", "c"};
		String textB[] = {"a", null, "c"};

		System.out.println("---配列にnullがない場合---");
		System.out.println(nullCheck(textA));
		System.out.println("---配列にnullがある場合---");
		System.out.println(nullCheck(textB));
	}
	public static boolean nullCheck(String[] text) {
		boolean result = false;
		for(String textC : text) {
			if(textC == null) {
				result = true;
				break;
			}
		}
		return result;
	}
}
