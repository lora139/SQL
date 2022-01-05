package person;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Main {
	public static Scanner scanner = new Scanner (System.in);
	public static Connection conn = Connerctor.Connect();
	public static PreparedStatement ps = null;
	public static ResultSet rs = null;
	public static DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
	//public static String sql = "SELECT * FROM email JOIN person ON person.id=email.id_person;";
	public final static String search ="SELECT CONCAT(p.first_name,' ', p.mid_name,' ',p.last_name) AS 'name', e.email, p.create_at FROM Person AS p " +
			"JOIN email AS e ON p.id= e.id_person HAVING e.email LIKE ? OR 'name' LIKE ? ;";
	public final static String delete = "DELETE FROM Person WHERE CONCAT(first_name,' ',mid_name,' ',last_name) = ?";
	public final static String updateName = "UPDATE Person SET first_name = ? WHERE CONCAT(first_name,' ',mid_name,' ',last_name) = ?;";
	public final static String updateMidName = "UPDATE Person SET mid_name = ? WHERE CONCAT(first_name,' ',mid_name,' ',last_name) = ?;";
	public final static String updateLastName = "UPDATE Person SET last_name = ? WHERE CONCAT(first_name,' ',mid_name,' ',last_name) = ?;";
	public final static String updateEmail = "UPDATE Email SET email = ? WHERE id_person=(SELECT id FROM person WHERE CONCAT(first_name,' ',mid_name,' ',last_name) = ?);";
	public final static String insertPerson = "INSERT INTO Person (first_name, mid_name, last_name, created_at) VALUES(?, ?, ?, now());";
	public final static String insertEmail = "INSERT INTO Email(email, id_person) VALUES(?, (SELECT id FROM Person\n" +
			                                  "WHERE CONCAT(first_name,' ',mid_name,' ',last_name) = ?));";

	public static final int Admin_Password = 65432;
	public static final int Reader_Password = 23456;


	private static void AdminInf(){
		System.out.printf("Admin:%nSearch,Update,Insert and Delete from the DataBase:%nIf you want to exit type Stop%nCommand:");
		String command = scanner.nextLine();
		while (!command.equals("End")) {
			switch (command) {
				case "Update":
					System.out.printf("What would you like to update?%nFirst Name, Middle Name, Last Name or Email?%n");
					String updateType = scanner.nextLine();
					System.out.println("Who would you like to make changes to(All the three names):");
					String fullName = scanner.nextLine();
					System.out.println("With what would you like to replace it with");
					String toChange = scanner.nextLine();
					Update(updateType, fullName, toChange);
					break;
				case "Delete":
					System.out.println("Enter the name of the person you want to delete:");
					String name = scanner.nextLine();
					Delete(name);
					break;
				case "Insert":
					System.out.println("Enter the three names of the person and his email seperated with spaces:");
					String[] info = scanner.nextLine().split(" ");
					Insert(info);
					break;
				case "Search":
					String nameOrEmail = scanner.nextLine();
					Search(nameOrEmail);
					break;
			}
			System.out.print("Command:");
			command = scanner.nextLine();
		}
	}
	private static void ReaderInf(){
		System.out.println("Reader: %nSearch for someone (Name or Email) %n");
		String nameOrEmail = scanner.nextLine();
		Search(nameOrEmail);

	}
	private static void Update(String type, String name, String toChange) {
		switch (type) {
			case "First Name" -> UpdateFirstName(name, toChange);
			case "Middle Name" -> UpdateMidName(name, toChange);
			case "Last Name" -> UpdateLastName(name, toChange);
			case "Number" -> UpdateEmail(name, toChange);
			default -> System.out.println("Not a valid command");
		}
	}

	private static void UpdateFirstName(String name, String toChange) {
		try {
			ps = conn.prepareStatement(updateName);
			ps.setString(1, toChange);
			ps.setString(2, name);
			ps.executeUpdate();
			System.out.println("Update was successful!");
		} catch (Exception e) {
			System.out.println("Update failed to execute");
		}
	}

	private static void UpdateMidName(String name, String toChange) {
		try {
			ps = conn.prepareStatement(updateMidName);
			ps.setString(1, toChange);
			ps.setString(2, name);
			ps.executeUpdate();
			System.out.println("Update was successful!");
		} catch (Exception e) {
			System.out.println("Update failed to execute");
		}
	}

	private static void UpdateLastName(String name, String toChange) {
		try {
			ps = conn.prepareStatement(updateLastName);
			ps.setString(1, toChange);
			ps.setString(2, name);
			ps.executeUpdate();
			System.out.println("Update was successful!");
		} catch (Exception e) {
			System.out.println("Update failed to execute");
		}
	}

	private static void UpdateEmail(String name, String toChange) {
		try {
			ps = conn.prepareStatement(update...);
			ps.setString(1, toChange);
			ps.setString(2, name);
			ps.executeUpdate();
			System.out.println("Update was successful!");
		} catch (Exception e) {
			System.out.println("Update failed to execute");
		}
	}

	private static void Delete(String name) {
		try {
			ps = conn.prepareStatement(delete);
			ps.setString(1, name);
			ps.execute();
			System.out.printf("%s successful %n", name);
		} catch (Exception e) {
			System.out.println("Name was not found");
		}
	}

	private static void Insert(String[] info) {
		try {
			ps = conn.prepareStatement(insertPerson);
			ps.setString(1, info[0]);
			ps.setString(2, info[1]);
			ps.setString(3, info[2]);
			ps.execute();
			ps = conn.prepareStatement(insert...);
			ps.setString(1, info[3]);
			ps.setString(2, String.format("%s %s %s", info[0], info[1], info[2]));
			ps.execute();
			System.out.printf("%s was successfully added%n", String.format("%s %s %s", info[0], info[1], info[2]));
		} catch (Exception e) {
			System.out.println("Insert failed to execute");
		}
	}

	private static void Search(String nameOrEmail){
		try{
			ps = conn.prepareStatement(search);
			ps.setString(1,String.format("%%%s%%", nameOrEmail));
			ps.setString(2,String.format("%%%s%%", nameOrEmail));

			rs=ps.executeQuery();

			while (rs.next()){
				String date = dateFormat.format(rs.getDate("create_at"));
				System.out.printf("Name: %s %nEmail: %s%n Date: %s%n", rs.getString("name"),rs.getString("email"),date);
			}
		}catch (Exception e){
			System.out.println("Search failed to execute");
		}
	}

	public static void main(String[] args) {

		System.out.println("Enter password: ");
		int password;
		do {
			password = Integer.parseInt(scanner.nextLine());
			switch (password) {
				case Admin_Password -> AdminInf();
				case Reader_Password -> ReaderInf();
				default -> System.out.println(" Your password is incorrect");
			}

		}while (password!=Admin_Password && password!=Reader_Password);
		System.out.println("Auf Wiedersehen! ");
	}
}

      
