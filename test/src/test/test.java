package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class test {
		public static void main(String[] args) {
//			Setup day "today "
			Date dateCompare = new Date();
			int monthToday = dateCompare.getMonth() + 1;
			int yearToday = dateCompare.getYear() + 1900;
			String dateCompare1 = dateCompare.getDay() + "/" + monthToday + "/" + yearToday
					+ " " + dateCompare.getHours() + ":" + dateCompare.getMinutes() + ":" + dateCompare.getSeconds();
			System.out.println(dateCompare1);
//			System.out.println(dateCompare1 + " ---- " + L.listTask[i].getDateShowLeft());
//			Date date = new Date();
//			int day = date.getDate();
//			int month = date.getMonth()+1;
//			int year = date.getYear() +1900;
//			System.out.println(day + " " + month + " " + year + date.getHours() + " " + date.getMinutes()+ " " + date.getSeconds());
//		}

	}
}