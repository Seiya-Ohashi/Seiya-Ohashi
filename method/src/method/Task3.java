package method;

public class Task3 {

	public static void main(String[] args) {
		System.out.println("--①文字列だけの場合---");
		System.out.println(add("文字列１", "文字列２"));
		System.out.println("--②整数だけの場合---");
		System.out.println(add(3, 5));
		System.out.println("--③小数だけの場合---");
		System.out.println(add(3.5F, 5.5F));

	}public static String add(String textA, String textB) {
		return textA + textB;
	}
	public static int add(int numA, int numB) {
		return  numA + numB;
	}
	public static float add(float numC, float numD) {
		return numC + numD;
	}
}
