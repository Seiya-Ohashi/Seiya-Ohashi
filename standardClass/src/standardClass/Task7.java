package standardClass;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Task7 {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat time= new SimpleDateFormat("yyyy年MM月dd日");
		System.out.println("現在日時: " + time.format(cal.getTime()));

		cal.add(Calendar.DAY_OF_MONTH, 7);
		System.out.println("１週間後: " + time.format(cal.getTime()));

		time.applyPattern("yyyy年MM月dd日(E)");
		cal.set(2021, 2, 14);
		System.out.println("１年後 : " + time.format(cal.getTime()));
	}

}
