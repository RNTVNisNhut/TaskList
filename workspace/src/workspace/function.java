package workspace;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class function {
	File file = new File("D:\\my_app\\app\\console-program_task_list(02)\\data\\data.txt");

	public void addTask(List L, String nameTask, String levelTask, Date dateTask) {

		// First is save in data ram
		L.listTask[L.listTask.length - 1] = new task(nameTask, levelTask, dateTask);

		// Setup to save data
		int number = 0;

		// we have list task

		task[] listTaskNew = this.createNewListTask();
		try {
			Scanner myReadFile = new Scanner(file);
			// we have number task
			number = Integer.parseInt(myReadFile.nextLine()) + 1;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// update new task
		listTaskNew[listTaskNew.length - 1] = new task(nameTask, levelTask, dateTask);

		// File save
		File newFile = new File("D:\\my_app\\app\\console-program_task_list(02)\\data\\data.txt");

		// File file1 = new
		// File("D:\\my_app\\app\\console-program_task_list(02)\\data\\data.txt");

		try {
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Can't create new file ");
			}
			PrintWriter pw = new PrintWriter(newFile);
			pw.println(number);
			for (int i = 0; i < number; i++) {
				pw.println(listTaskNew[i].getAlltast());
			}
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public task[] createNewListTask() {
		task[] listTask = null;
		try {
			Scanner myReadFile = new Scanner(file);

			// Load number task
			String number = myReadFile.nextLine();

			// +1 task in here
			int number1 = Integer.parseInt(number) + 1;

			listTask = new task[number1];

			// Load task
			for (int i = 0; i < number1 - 1; i++) {
				String data = myReadFile.nextLine();

				int indexName = data.indexOf('-');
				int indexName2 = data.lastIndexOf('-');

				String nameTask = data.substring(0, indexName);
				String level = data.substring(indexName + 1, indexName2);

				// Setup day input of task
				String day = data.substring(indexName2 + 1);
				Date date1;
				{
					int index1 = day.indexOf('/');
					int index2 = day.lastIndexOf('/');

					String day1 = day.substring(0, index1);
					String month1 = day.substring(index1 + 1, index2);
					String year1 = day.substring(index2 + 1);

					int day2 = Integer.parseInt(day1);
					int month2 = Integer.parseInt(month1);
					int year2 = Integer.parseInt(year1);

					date1 = new Date(year2, month2, day2);

				}
				listTask[i] = new task(nameTask, level, date1);

			}

		} catch (FileNotFoundException e) {
			System.out.println("Can't load data from file");
		}
		return listTask;
	}

	public Date convertToDate(String stringDate) {

		int index1 = stringDate.indexOf('/');
		int index2 = stringDate.lastIndexOf('/');

//		int indexNextIsTime = stringDate.indexOf('|');
//		int indexHours = stringDate.indexOf(':');
//		int indexMinute = stringDate.lastIndexOf(':');

		String day = stringDate.substring(0, index1);
		String month = stringDate.substring(index1 + 1, index2);
		String year = stringDate.substring(index2 + 1);

//		String hours = stringDate.substring(indexNextIsTime + 1, indexHours);
//		String minute = stringDate.substring(indexHours + 1, indexMinute);
//		String second = stringDate.substring(indexMinute + 1);

		int day1 = 0;
		int month1 = 0;
		int year1 = 0;
//		int hours1 = Integer.parseInt(hours);
//		int minute1 = Integer.parseInt(minute);
//		int second1 = Integer.parseInt(second);

		if (!day.equals("")) {
			day1 = Integer.parseInt(day);
		}

		if (!month.equals("")) {
			month1 = Integer.parseInt(month);
		}
		if (!year.equals("")) {
			year1 = Integer.parseInt(year);
		}
		Date data = new Date(year1, month1, day1);

		return data;

	}

//	Spit day to dayTemp
	public Date splitDay(String data) {
		Date date;

		int dayConvert = 0;
		int monthConvert = 0;
		int yearConvert = 0;

		int index1 = data.indexOf('/');
		int index2 = data.lastIndexOf('/');

		String day = data.substring(0, index1 + 1);
		if (day.equals(" ")) {
			dayConvert = Integer.parseInt(day);
		}
		String month = data.substring(index1 + 1, index2 + 1);
		if (month.equals(" ")) {
			monthConvert = Integer.parseInt(month);
		}
		String year = data.substring(index2 + 1);
		if (month.equals(" ")) {
			yearConvert = Integer.parseInt(year);
		}

		date = new Date(yearConvert, monthConvert, dayConvert);
		return date;
	}

// Show data
	public void showData(List L) {

		for (int u = 0; u < 3; u++)
			System.out.println("\n");

		System.out.println("=============== List task ===============");
		System.out.println(this.numberOfData(L));
		for (int i = 0; i < this.numberOfData(L); i++) {
			int number = i + 1;

// set up to count char
			char[] nameTask = this.convertToChar(L.listTask[i].getName());
			char[] levelTask = this.convertToChar(L.listTask[i].getLevel());

			System.out.print(number + ". " + L.listTask[i].getName());
			for (int j = 0; j < 35 - nameTask.length; j++) {
				System.out.print(" ");
			}

			System.out.print(L.listTask[i].getLevel());
			for (int k = 0; k < 10 - levelTask.length; k++) {
				System.out.print(" ");
			}
			// Setup day "today "
			Date dateCompare = new Date();
			int monthToday = dateCompare.getMonth()+1;
			int yearToday = dateCompare.getYear() + 1900;

			String dateCompare1 = dateCompare.getDay() + "/" + monthToday + "/" + yearToday + " "
					+ dateCompare.getHours() + ":" + dateCompare.getMinutes() + ":" + dateCompare.getSeconds();
//			System.out.println(dateCompare1 + " " + L.listTask[i].getDateShowLeft());
			this.findDifference(dateCompare1, L.listTask[i].getDateShowLeft());

		}

// add space beautifull
		for (int u = 0; u < 3; u++)
			System.out.println("\n");

	}

//	public int dayLeft(List L, int index) {
//
//		Date today = new Date();
//		Date saveInput = L.listTask[index].getDateTemp();
//
//		long lastDayLeft = saveInput.getTime() - today.getTime();
//		double TotalDays = Math.ceil(lastDayLeft / (1000 * 3600 * 24));
//		int dayReturn = (int) TotalDays;
//
//		return dayReturn;
//	}

	public char[] convertToChar(String string) {
		char[] convert = new char[string.length()];
		for (int i = 0; i < string.length(); i++) {
			convert[i] = string.charAt(i);
		}
		return convert;
	}

	public int numberOfData(List L) {
		int count = 0;
		for (task task : L.listTask)
			count++;
		return count;
	}

// Load data
	public List loadData() {

		task[] listTask = null;
		List list;
//	Load data

		try {
			Scanner myReadFile = new Scanner(file);

			// Load number task
			String number = myReadFile.nextLine();
			int number1 = Integer.parseInt(number);
			listTask = new task[number1];

			// Load task
			for (int i = 0; i < number1; i++) {

				String data = myReadFile.nextLine();

				int indexName = data.indexOf('-');
				int indexName2 = data.lastIndexOf('-');

				String nameTask = data.substring(0, indexName);
				String level = data.substring(indexName + 1, indexName2);
				String day = data.substring(indexName2 + 1);

//				Date dayTemp = this.splitDay(day);
//				System.out.println("Name task is : " + nameTask + "\nLevel is : " + level + "\nDay is : " + day);
				listTask[i] = new task(nameTask, level, this.convertToDate(day));

			}

		} catch (FileNotFoundException e) {
			System.out.println("Can't load data from file");
		}

		list = new List(listTask);

		return list;

	}

	public void findDifference(String start_date, String end_date) {

		// SimpleDateFormat converts the
		// string format to date object
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		// Try Block
		try {

			// parse method is used to parse
			// the text from a string to
			// produce the date
			Date d1 = sdf.parse(start_date);
			Date d2 = sdf.parse(end_date);

			// Calculate time difference
			// in milliseconds

			long difference_In_Time = d2.getTime() - d1.getTime();

			// Calculate time difference in
			// seconds, minutes, hours, years,
			// and days
			long difference_In_Seconds = (difference_In_Time / 1000) % 60;

			long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;

			long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;

			long difference_In_Years = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));

			long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

			// Print the date difference in
			// years, in days, in hours, in
			// minutes, and in seconds
//			System.out.println();
//			System.out.print("Difference " + "between two dates is: ");

			System.out.println(difference_In_Years + " years, " + difference_In_Days + " days, " + difference_In_Hours
					+ " hours, " + difference_In_Minutes + " minutes, " + difference_In_Seconds + " seconds");
		}

		// Catch the Exception
		catch (ParseException e) {
			e.printStackTrace();
		}

	}

}