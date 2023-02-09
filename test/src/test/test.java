package test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class test {
	public static void main(String[] args) {
		Date d1 = new Date(2023,2,9);
        Date d2 = new Date(2023,2,10);
        System.out.println(d1.getDate() + " " + d1.getMonth() + " " + d1.getYear());
//        long d3 = d2.getTime()-d1.getTime(); 
//        double TotalDays = Math.ceil(d3 / (1000 * 3600 * 24));
//        int day = (int)TotalDays;
//        System.out.println(day + " days left");
//        System.out.println(d2.getDay() + " " + d2.getMonth() + " " + d2.getYear());
//        System.out.println();
//        System.out.println(TimeUnit.DAYS.convert(d3, TimeUnit.MILLISECONDS));
	}
}
