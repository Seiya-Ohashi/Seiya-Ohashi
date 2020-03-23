package standardClass;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Task6 {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat time= new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat time2= new SimpleDateFormat("yyyy年MM月dd日HH時mm分");
		System.out.println(time.format(cal.getTime()));
		System.out.println(time2.format(cal.getTime()));
	}

}
