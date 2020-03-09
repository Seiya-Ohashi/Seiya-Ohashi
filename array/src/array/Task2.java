package array;

public class Task2 {
	public static void main(String[] args) {
		int[] numbers = new int[7];

		for (int i = 7; i > 0; i--) {
			numbers[i-1] = i;
			if(i <= numbers.length && i > 1 ) {
				System.out.print(numbers[i-1] + ",");
			}else{
				System.out.println(numbers[i-1]);
			}
		}
		for (int num : numbers){
			if(num < numbers.length) {
				System.out.print(num + ",");
			}else{
				System.out.print(num);
			}
		}
	}
}
