package instance;

public class Person{
	//名前
	String name = "";
	//年齢
	int age = 0;

	public void myProfile() {
		System.out.println("私の名前は" + this.name + "です。");
		System.out.println("年齢は" + this.age + "です。");
		System.out.println("よろしくお願いします。");
	}
}
