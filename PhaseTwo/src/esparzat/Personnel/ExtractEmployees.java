package esparzat.Personnel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConnectToEmployees {

	public static List<Employee> employee = new ArrayList<>();

	/**
	 * This method reads out the content of the .txt file to list.
	 * 
	 * @return employee
	 */
	public static List<Employee> readEmployeeFile() {

		File f = new File("Personnel.txt");

		Scanner sc = null;

		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("Could not open file.");
		}

		while (sc.hasNextLine()) {
			Employee e = new Employee();
			String in = sc.nextLine();
			String[] fields = in.split("\t", -1);
			e.setUsername(fields[1]);
			e.setPassword(fields[2].toCharArray());
			e.setAccessLevel(fields[0]);
			employee.add(e);

		}

		return employee;

	}

	/**
	 * This method checks to see if the Personnel.txt file exists
	 */

	public static void CheckIfFileExist() {
		f = new File("Personnel.txt");

		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				System.out.println("Not able to create file.");
				System.exit(-1);
			}
		}

		try {
			pw = new PrintWriter(new FileOutputStream(f, true));
		} catch (FileNotFoundException e) {
			System.out.println("Not able to locate file.");
		}
	}
}