package method;

public class Task1 {

	public static void main(String[] args) {
		String name = "コラボ";
		int    age = 30;
		float  height = 178F;
		myProfile(name,age,height);
	}
	public static void myProfile(String name, int age, float height){
		System.out.println("私の名前は" + name + "です。");
		System.out.println("年齢は" + age  + "歳です。");
		System.out.println("身長は" + height + "cmです。");
		System.out.println("よろしくお願いします。");
	}
}
