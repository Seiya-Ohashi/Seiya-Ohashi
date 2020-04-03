package object;

public class Person {
	//名前
	private String name;
	//年齢
	private int age;
	//お財布
	static int wallet;

	public String getName() {
		  return this.name;
		}

	public int getAge() {
		  return this.age;
		}

	public void setName(String name){
		this.name = name;
		}

	public void setAge(int age){
		this.age = age;
		}

	//コンストラクタ 引数なし
	public Person(){
	}

	//コンストラクタ 引数あり
	public Person(String name, int age, int wallet){
		this.name = name;
		this.age = age;
		this.wallet = wallet;
	}

	public void myProfile() {
		System.out.println("私の名前は" + this.name + "です。");
		System.out.println("私の年齢は" + this.age + "です。");
		System.out.println("よろしくお願いします。");
	}
}
