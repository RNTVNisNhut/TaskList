package workspace;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Scanner;

public class function {
	File file = new File("D:\\my_app\\app\\console-program_task_list(02)\\data\\data.txt");

	public void addTask(List L, String nameTask, String levelTask, String dateTask) {

// First is save in data ram
		L.listTask[L.listTask.length - 1] = new task(nameTask, levelTask, dateTask);
//	Setup to save data
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
		listTaskNew[listTaskNew.length] = new task(nameTask, levelTask, dateTask);

// File save
		File newFile = new File("D:\\my_app\\app\\console-program_task_list(02)\\data\\data.txt");
//		File file1 = new File("D:\\my_app\\app\\console-program_task_list(02)\\data\\data.txt");

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
// Change Name
//		try {
//			Files.move(newFile.toPath(), file1.toPath(), StandardCopyOption.REPLACE_EXISTING);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public task[] createNewListTask() {
		task[] listTask = null;
		try {
			Scanner myReadFile = new Scanner(file);

			// Load number task
			String number = myReadFile.nextLine();

			// +1 task in here
			int number1 = Integer.parseInt(number)+1;

			listTask = new task[number1];

			// Load task
			for (int i = 0; i < number1 - 1; i++) {
//				Date dayTemp = null;
				String data = myReadFile.nextLine();

				int indexName = data.indexOf('-');
				int indexName2 = data.lastIndexOf('-');

				String nameTask = data.substring(0, indexName);
				String level = data.substring(indexName + 1, indexName2);
				String day = data.substring(indexName2 + 1);
//				if (day != null) {
//					dayTemp = this.splitDay(day);
//				}
//				System.out.println("Name task is : " + nameTask + "\nLevel is : " + level + "\nDay is : " + day);
				listTask[i] = new task(nameTask, level, day);

			}

		} catch (FileNotFoundException e) {
			System.out.println("Can't load data from file");
		}
		return listTask;
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
			
			System.out.println(L.listTask[i].getDateShow());

//			System.out.println(this.dayLeft(L, i));
//			System.out.println(number + " " + L.listTask[i].getName() + "  " + L.listTask[i].getLevel() + "   "
//					+ L.listTask[i].getDate());

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
				listTask[i] = new task(nameTask, level, day);

			}

		} catch (FileNotFoundException e) {
			System.out.println("Can't load data from file");
		}

		list = new List(listTask);

		return list;

	}
}