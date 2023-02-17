package workspace;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		
		function x = new function();
		child_function y = new child_function();
		
		x.showData();
		
		int endOrcontinue = 0;
		Scanner sc = new Scanner(System.in);

		do {
			
			String select ="";
			
			System.out.println("                    add       : Add new Task !");
			System.out.println("                    show      : Show All Task !");
			System.out.println("                    done      : Done Last Task !");
			System.out.println("                    change    : Change Data !");

			select = sc.nextLine();

			// Add task
			if (select.compareTo("add") == 0) {

				System.out.println("Pass name task");
				String nameTask = sc.nextLine();
				 
				System.out.println("Pass level task");
				String levelTask = sc.nextLine();
				
				System.out.println("Day Done ? ( Ex: 1/1/2023)");
				String dateDone = sc.nextLine();
				
				System.out.println("Time Done ? ( Ex: 11:11:00");
				String timeDone = sc.nextLine();
				
				// Noi ngay va thoi gian ket thuc
				String endDayString = dateDone.concat(">");
				endDayString = endDayString.concat(timeDone);
				
				LocalDateTime endDay = y.convertStringToLocalDateTime(endDayString);
				LocalDateTime today = LocalDateTime.now();
				
				x.addTask(nameTask, levelTask, today, endDay);
				
			}
			
			if (select.compareTo("show") == 0) {
				x.showData();
			}
			
			if (select.compareTo("done") == 0) {
				x.deleteTask();
				System.out.println("successful delete");
			}
			
			if (select.compareTo("change") == 0) {
				x.changeData();
			}
			
			if (select.compareTo("exit") == 0)
				endOrcontinue = 1;
			
		} while (endOrcontinue == 0);

	}
}
