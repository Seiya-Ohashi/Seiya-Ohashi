package calc;

public class task2 {
	public static void main(String[] args) {
		int a = 10;
		int b = 12;
		int c = 13;
		int total = a + b + c;
		int averageInt = ((a + b + c) / 3);
		Double averageDouble =((a + b + c) / 3.0);

		System.out.println("合計:" + total);
		System.out.println("平均(3で割った時):" + averageInt);
		System.out.println("平均(3.0で割った時):" + averageDouble);
	}
}