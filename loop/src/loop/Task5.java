package loop;

public class Task5 {
	public static void main(String[] args) {
		for (int step = 1; step <= 9; step++) {
			for (int num = 1; num <= 9; num++) {
				if(step * num < 10){
					System.out.print("  " + step * num);
				}else{
					System.out.print(" " + step * num);
				}
			}
			System.out.println();
		}
	}
}