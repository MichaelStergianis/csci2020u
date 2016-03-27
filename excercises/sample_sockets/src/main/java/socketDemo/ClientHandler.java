import java.io.*;
import java.net.*;


public class ClientHandler implements Runnable{
	private Socket clientSocket;

	public ClientHandler(Socket clientSocket){
		this.clientSocket = clientSocket;
	}

	public void run(){
		try {
			// create our reader
			BufferedReader in = new BufferedReader(
			    new InputStreamReader(
			        clientSocket.getInputStream()
			));
			// create our writer
      OutputStream os = clientSocket.getOutputStream();
			PrintWriter out = new PrintWriter(os);
			
			// handle the request data
			String requestLine = in.readLine();
			String[] httpCommand = requestLine.split(" ");
			if (!httpCommand[0].equalsIgnoreCase("get")){
				out.print("HTTP/1.0 405 Method ont supported\r\n");
			} else {
				// return the response data
				out.print("HTTP/1.0 200 OK\r\n");
				while ( (requestLine = in.readLine()) != null){
					System.out.println(requestLine);
				}
				// load and send the file data
				String fn = httpCommand[1];
				BufferedReader fin = new BufferedReader(
				    new InputStreamReader(
							new FileInputStream(fn)
				));
				String fileLine = null;
				while ( (fileLine = fin.readLine()) != null){
					out.println(fileLine);
				}
			}
			// close our files
			out.flush();
			clientSocket.close();
//      String line = null;
//      String filename = null;
//      while ((line = in.readLine()) != null) {
//          String[] httpParts = line.split(" ");
//          if (httpParts.equals("/")) {
//              filename = "www/index.html";
//          } else {
//              filename = "www" + httpParts[1];
//          }
//          System.out.println("Filename: " + filename);
//          break;
//          //System.out.println(">>" + line);
//      }
//
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
