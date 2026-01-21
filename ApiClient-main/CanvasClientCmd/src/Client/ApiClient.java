package Client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiClient {
	private HttpClient client;
	private String token;
	private String url = "https://www.example.com/api";
	private int statusCode;

	// Constructor 
	public ApiClient(String token) {
		// Create new HTTP client for API client object to use
		this.client = HttpClient.newHttpClient();
		this.token = token;
		this.statusCode = 0;
	}
	
	public String GetResponse(String endpoint) {
		
		try {
			// Concatenate base url and endpoint
			url += endpoint;
			// Build GET request using  user-provided endpoint
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(url))
					.header("Authorization", "Bearer " + token)
					.header("Accept", "application/json")
					.GET()
					.build();
			// HTTP GET request sent
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			statusCode = response.statusCode();
			// Print status code for user
			System.out.println("Status Code: " + statusCode);
			// Returns raw JSON response as a string
			return response.body();
		} catch (Exception e) {
			return " ";
		}

	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	
	public static class JsonPrinter {
		static ObjectMapper mapper = new ObjectMapper();
		
		public static void prettyPrint(String jsonTxt) {
			try {
				JsonNode node = mapper.readTree(jsonTxt);
				String pretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
				System.out.println(pretty);
				
			} catch (Exception e) {
				System.out.println("JSON Formatting/Mapping Error.");
			}
		}
		
	}
}
