package esparzat.Personnel;

import java.io.*;
import java.util.Scanner;

/**
 * This class contains the methods to check if the employee file exists, add
 * employee, and remove employee that the managers use.
 */
public class PersonnelControlDisplay {
	private static Scanner sc = new Scanner(System.in);
	private static Employee em;
	static File f;
	static PrintWriter pw = null;
	static String e = Employee.EMPLOYEE_LEVEL;
	static String m = Employee.MANAGER_LEVEL;
	static String a = Employee.ADMIN_LEVEL;

	/**
	 * This method prompts the manager to enter in the information to add a new
	 * employee. Once they enter in the information it appends to the
	 * employee.txt file.
	 */
	public static void addEmployee() {
		// todo: verify someone cannot enter a duplicate username.

		File f = new File("Personnel.txt");

		String choice = "y";
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));

			while (choice.equalsIgnoreCase("y")) {
				System.out
						.print("\nCreate new username: ");
				String username = sc.nextLine().toUpperCase();

				System.out
						.print("\nCreate new password:");
				char[] password = sc.nextLine().toCharArray();

				System.out
						.print("\nThe valid options are E for Employee or M for Manager")
						.print("\nEnter new employee access level:");
				String levelEntered = sc.nextLine();

				String employeeLevel = null;
				boolean successful = false;

				while (!successful) {
					switch (levelEntered.toUpperCase()) {
					case "E":
						employeeLevel = e;
						successful = true;
						break;
					case "M":
						employeeLevel = m;
						successful = true;
						break;
					default:
						System.out
								.print("\n ");
						levelEntered = sc.nextLine();
						successful = false;
					}
				}

				em = new Employee(username, password, employeeLevel);

				pw.write(em.getAccessLevel() + "\t");
				pw.write(em.getUsername() + "\t");
				pw.write(String.valueOf(em.getPassword()) + "\n");

				System.out.print("Would you like to add another user? Y or N ");
				choice = sc.nextLine();

				pw.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
									
		}
	}

	/**
	 * This method prompts the manager to enter in the information to remove a
	 * employee. Once they enter in the information it removes the line from
	 * employee.txt file. It does not allow anyone to remove the ADMIN user.
	 */

	public static void removeEmployee() {
		File f = new File("Personnel.txt");

		String choice = "y";

		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(f, false));

			while (choice.equalsIgnoreCase("y")) {

				System.out
						.print("\nUsername to delete: ");
				String username = sc.nextLine().toUpperCase();
				Boolean found = false;

				if (username.equalsIgnoreCase("ADMIN")) {
					System.out.println("You can not remove this user.");
					ManagersDisplay.initialMgrsDisplay();
				} else {
					for (Employee e : ExtractEmployees.employee) {

						if (e.getUsername().equalsIgnoreCase(username)) {
							ExtractEmployees.employee.remove(e);
							found = true;
							break;
						}
					}

					if (!found) {
						System.out.println("This user is not available.");
						ManagersDisplay.initialMgrsDisplay();
					} else {
						for (Employee e : ExtractEmployees.employee) {
							pw.write(e.getAccessLevel() + "\t");
							pw.write(e.getUsername() + "\t");
							pw.write(String.valueOf(e.getPassword()) + "\n");
							pw.flush();
						}
						pw.close();
						System.out
								.print("Would you like to remove another user? Y or N ");
						choice = sc.nextLine();

						ManagersDisplay.initialMgrsDisplay();
					}
				}

			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}
}