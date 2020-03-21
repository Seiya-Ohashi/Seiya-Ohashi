package object;

public class Main {
	public static void main(String[] args) {
		Dog pochi = new Dog("ポチ", 5, 12.3);
		System.out.println("名前：" + pochi.name);
		System.out.println("年齢：" + pochi.age + "歳");
		System.out.println("体重：" + pochi.weight + "kg");

		pochi.run();
		pochi.sleep();
	}
}
