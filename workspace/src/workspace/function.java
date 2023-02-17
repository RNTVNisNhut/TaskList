package workspace;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class function {
	child_function x = new child_function();
	File file = new File("D:\\my_app\\TaskList\\data\\data.txt");

	// LOAD DATA
	public List loadData() {

		task[] listTask = null;
		List list;

		try {

			Scanner myReadFile = new Scanner(file);

			String numberLoad = myReadFile.nextLine();
			int numberInt = Integer.parseInt(numberLoad);

			listTask = new task[numberInt];
			list = new List(listTask);

			for (int i = 0; i < numberInt; i++) {

				String data = myReadFile.nextLine();

				x.convertDataFromFileToObj(data, list, i);

			}

		} catch (FileNotFoundException e) {
			System.out.println("Can't load data from file");
		}

		list = new List(listTask);

		return list;

	}

	public void changeData() {

		List temp = this.loadData();

		System.out.println("Position change ?");
		Scanner sc = new Scanner(System.in);
		// Position change
		String positionChangeString = sc.nextLine();
		int positionChange = Integer.parseInt(positionChangeString);
		// Change element is
		System.out.println("What do you want to change ? ( name - level - day end ) ?");
		String changeX = sc.nextLine();

		if (changeX.compareTo("name") == 0) {
			System.out.println("Change into ?");
			temp.listTask[positionChange - 1].setName(sc.nextLine());
			System.out.println("Successful Change !!!");
		}
		if (changeX.compareTo("level") == 0) {
			System.out.println("Change into ?");
			temp.listTask[positionChange - 1].setLevel(sc.nextLine());
			System.out.println("Successful Change !!!");
		}
		if (changeX.compareTo("day end") == 0) {
			System.out.println("With exactly time pass 1 or 0");
			String choose = sc.nextLine();
			int choose1 = Integer.parseInt(choose);
			if (choose1 == 1) {
				System.out.println("Day done ?");
				String dayDone = sc.nextLine();
				System.out.println("Time Done ?");
				String timeDone = sc.nextLine();
				String resultDay = dayDone.concat(">");
				resultDay = resultDay.concat(timeDone);
				temp.listTask[positionChange - 1].setDateEnd(x.convertStringToLocalDateTime(resultDay));
			} else {
				System.out.println("Day done ?");
				String dayDone = sc.nextLine();

				int index1 = dayDone.indexOf("/");
				int index2 = dayDone.lastIndexOf("/");
				String day = dayDone.substring(0, index1);
				String month = dayDone.substring(index1 + 1, index2);
				String year = dayDone.substring(index2 + 1);

				LocalDateTime dayResult = LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month),
						Integer.parseInt(day), 0, 0);
				temp.listTask[positionChange - 1].setDateEnd(dayResult);

			}
			System.out.println("Successful Change !!!");

		}
		this.saveData(temp);

	}

	// Load data from file
	public void addTask(String nameTask, String levelTask, LocalDateTime dayStart, LocalDateTime dayEnd) {

		List temp = this.loadData();

		task[] NewTask = new task[temp.listTask.length + 1];

		for (int i = 0; i < temp.listTask.length; i++) {
			NewTask[i] = new task(temp.listTask[i].getName(), temp.listTask[i].getLevel(),
					temp.listTask[i].getDateStart(), temp.listTask[i].getDateEnd());
		}
		NewTask[temp.listTask.length] = new task(nameTask, levelTask, dayStart, dayEnd);
		temp = new List(NewTask);
		this.saveData(temp);
		this.showData();

	}

	// Show data
	public void showData() {

		int k = 0;
		List temp = this.loadData();

		while (k < 3) {
			System.out.println(" ");
			k++;
		}

		System.out.println("TASK                            TYPE           TIME REMAINING");

		int count = 0;
		int int1 = 0;
		for (task task : temp.listTask) {
			if (temp.listTask[int1] != null) {
				count++;
			}
		}

		for (int i = 0; i < count; i++) {

			if (temp.listTask[i] != null) {
				LocalDateTime toDay = LocalDateTime.now();

				int j;

				System.out.print(i + 1 + "." + temp.listTask[i].getName());

				for (j = 0; j < (30 - (temp.listTask[i].getName().length())); j++) {
					System.out.print(" ");
				}

				System.out.print(temp.listTask[i].getLevel());
				for (j = 0; j < (15 - (temp.listTask[i].getLevel().length())); j++) {
					System.out.print(" ");
				}

				System.out.println(x.caculateTimeLeft(toDay, temp.listTask[i].getDateEnd()));

			} else
				break;
		}

		k = 0;
		while (k < 3) {
			System.out.println(" ");
			k++;
		}

	}

	// Delete task with index
	public void deleteTask() {

		List temp = this.loadData();

		temp.listTask[temp.listTask.length - 1] = null;

		this.saveData(temp);
	}

	public void saveData(List L) {
		File newFile = new File("D:\\my_app\\TaskList\\data\\data.txt");

		PrintWriter pw;
		try {
			pw = new PrintWriter(newFile);
			int count = 0, i = 0;
			for (task task : L.listTask) {
				if (L.listTask[i++] != null) {
					count++;
				}
			}
			pw.println(count);
			for (int j = 0; j < count; j++) {
				if (L.listTask[j] != null)
					pw.println(L.listTask[j].getDataSave());
			}
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
