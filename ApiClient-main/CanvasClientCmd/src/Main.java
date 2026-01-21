import java.util.Scanner;

import Client.ApiClient;
import Client.ApiClient.JsonPrinter;
import DB.OracleDb.OracleDbConnect;

public class Main {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// Intro message in red
		System.out.println("\u001B[31m" + "API Client: Command Line" + "\u001B[0m");
		
		// Program will exit when while loop is exited
		while(true) {
			System.out.println("Base URL: https://www.example.com/api");
			// Store api access token as env variable for security
			String token = System.getenv("TOKEN");
			// Prompt user to enter canvas api endpoint
			System.out.print("\nEnter API Endpoint: ");
			String endPoint = scan.nextLine();
			ApiClient client = new ApiClient(token);
			
			String rawResponse = client.GetResponse(endPoint);
			// Alert user of http error
			if (client.getStatusCode() != 200) {
				System.out.println("\u001B[31m" + "ERROR: Please make sure you are using a valid access token and valid endpoint." + "\u001B[0m");
				// Program will only continue if user hits 'Enter' key
				System.out.println("Press Enter to Continue.");
				scan.nextLine();
			} else {
				while(true) {
					// Menu choice - how user wants to handle data
					System.out.println("[1]: Print JSON Response\n[2]: Save Response to Database\n[3]: New Request\n[4]: Exit");
					int choice = scan.nextInt();
					
						switch (choice) {
						// Pretty print JSON response to console
						case 1:
							JsonPrinter.prettyPrint(rawResponse);
							break;
						// Establish connection to DB and execute DB logic (does not have to be Oracle DB)
						case 2:
							OracleDbConnect.connect();
							break;
						case 3:
							break;
						// Exit program
						case 4:
							System.exit(0);
						default:
							System.out.println("\nInvalid Option\n");
						}
					if(choice == 3) {
						// Reset endpoint and menu choice
						endPoint = "";
						choice = 0;
						break;
					}
						
				}
				
			}
		}
		
	}
}
