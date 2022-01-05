package person;
import java.sql.*;
public class Connerctor {

	public static Connection Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection= DriverManager.getConnection("jdbc:mysql://localhost/prsn", "root", "3Bzye017818*");
			System.out.println("Connected to DB");
			return connection;
		}catch(Exception e) {
			System.out.println("Failed connection");
			return null;
		}
		
	}
}
