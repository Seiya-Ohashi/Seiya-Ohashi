package variable;

public class task3 {

	public static void main(String[] args) {
		System.out.println("変換前");
		int intNum = 219;
		double doubleNum = 364.2;
		long longNum =123456L;
		System.out.println(intNum);
		System.out.println(doubleNum);
		System.out.println(longNum);

		System.out.println("変換後");
		long longIntNum = intNum;
		float floatDoubleNum = (float) doubleNum;
		double doubleLongNum = longNum;
		System.out.println(longIntNum);
		System.out.println(floatDoubleNum);
		System.out.println(doubleLongNum);
	}
}