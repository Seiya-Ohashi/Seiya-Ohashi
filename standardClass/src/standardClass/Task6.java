package standardClass;

import java.util.Calendar;

public class Task6 {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();

		System.out.print(cal.get(Calendar.YEAR) + "年");
		System.out.print(cal.get(Calendar.MONTH) +1 + "月");
		System.out.println(cal.get(Calendar.DAY_OF_MONTH) + "日");

		System.out.print(cal.get(Calendar.YEAR) + "年");
		System.out.print(cal.get(Calendar.MONTH) +1 + "月");
		System.out.print(cal.get(Calendar.DAY_OF_MONTH) + "日");
		System.out.print(cal.get(Calendar.HOUR_OF_DAY) + "時");
		System.out.print(cal.get(Calendar.MINUTE) + "分");

	}

}
