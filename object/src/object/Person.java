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
	Person(){
	}

	//コンストラクタ 引数あり
	Person(String name, int age, int wallet){
		this.name = name;
		this.age = age;
		this.wallet = wallet;
	}

	public void myProfile() {
		System.out.println("私の名前は" + this.name + "です。");
		System.out.println("私の年齢は" + this.age + "です。");
		System.out.println("よろしくお願いします。");
	}
	public static void main(String[] args) {
		System.out.println("インスタンスを生成していない状態");
		System.out.println(Person.wallet);

		//インスタンスを２つ生成
		Person hoge01 = new Person("大橋晟也", 29, 1000);
		Person hoge02 = new Person("田中太郎", 22, 2000);

		System.out.println("インスタンスhoge01でwalletにアクセス");
		System.out.println(hoge01.wallet);
		System.out.println("インスタンスhoge02でwalletにアクセス");
		System.out.println(hoge02.wallet);
		System.out.println("クラスPersonでwalletにアクセス");
		System.out.println(Person.wallet);
	}
}
