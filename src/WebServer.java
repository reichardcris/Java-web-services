import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class WebServer {
	
	  public static void main(String[] args) throws IOException {
	      HttpServer server = HttpServer.create(new InetSocketAddress(8600), 0);
	      HttpContext context = server.createContext("/");
	      context.setHandler(WebServer::handleRequest);
	      server.start();
	  }

	  private static void handleRequest(HttpExchange exchange) throws IOException {
	      
		  String response = "";
		  String request_body = convertToString(exchange.getRequestBody(),Charset.forName("UTF-8"));
	      String f_string[] = request_body.split("&");
	      String string_auth_split[] = f_string[1].split("=");
	      String string_auth = string_auth_split[1];
	      System.out.println(exchange.getRequestURI());
	      int code;
	      
	      if(string_auth.equalsIgnoreCase("webservices")) {
	    	  response = "Access granted!";
	    	  code = 200;
	      }else {
	    	  response = "Invalid Credentials!";
	    	  code = 401;
	      }
	      
	      exchange.sendResponseHeaders(code,response.getBytes().length);//response code and length
	      OutputStream os = exchange.getResponseBody();
	      os.write(response.getBytes());
	      os.close();
	  }
	  
	  public static String convertToString(InputStream inputStream, Charset charset) throws IOException {
		  
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			
			try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))) {	
				while ((line = bufferedReader.readLine()) != null) {
					stringBuilder.append(line);
				}
			}
			
			return stringBuilder.toString();
		}
	  
}
