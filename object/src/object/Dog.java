package object;

public class Dog extends Animal{
	double weight;

	public Dog(String name, int age, double weight){
		this.name = name;
		this.age = age;
		this.weight = weight;
	}
	public void run() {
		System.out.println(this.name + "は走った");
	}
	public void sleep() {
		Animal sleepA = new Animal();
		super.sleep(sleepA.name);
		System.out.println(this.name + "は眠った");
	}
}
