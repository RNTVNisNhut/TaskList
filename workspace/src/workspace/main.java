package workspace;

import java.util.Date;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		
		function x = new function();
		List L = x.loadData();
		x.showData(L);
		int choose = 0;
		Scanner sc = new Scanner(System.in);
		
		do {
			int select = 0;
			System.out.println("1 -> Add task"
					+ "\n2 -> Show all Taks"
					+ "\n-1 -> end");
			select = Integer.parseInt(sc.nextLine());
			if(select == 1) {
				System.out.println("Pass name task");
				String nameTask = sc.nextLine();
				System.out.println("Pass level task");
				String levelTask = sc.nextLine();
				System.out.println("Pass Date task");
				String dateTask = sc.nextLine();
				Date dateTemp = x.splitDay(dateTask);

				x.addTask(L, nameTask, levelTask, dateTask);
				
			}
			if(select == 2) {
				x.showData(L);
			}
			if(select == -1) choose = 1;
		}
		while(choose == 0);

	}
}