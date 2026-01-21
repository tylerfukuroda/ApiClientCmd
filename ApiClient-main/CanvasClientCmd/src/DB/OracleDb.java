package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleDb {
	private static String connectionString = "example.connection.string"; 
	
	public static class OracleDbConnect{
		// Save as environment variables
		private static String username = System.getenv("USERNAME");
		private static String password = System.getenv("PASSWORD");
		public static void connect() {
			try (Connection conn = DriverManager.getConnection(connectionString, username, password)) {
				System.out.println("Connection successful");
				/*
				Add other database logic here as necessary
				*/
			} catch (Exception e) {
				System.err.println(e);
				/*
				add more catch logic if needed
				*/
			}
		}
	}
}
