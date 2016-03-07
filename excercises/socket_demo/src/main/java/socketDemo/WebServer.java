package socketDemo;

/*
 * GET  -- lets you get files from a server
 * POST -- 
 * PUT  -- 
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public final class WebServer {
	private ServerSocket serverSocket = null;
	private Socket clientSocket;

	public WebServer(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void handleRequests() {
		System.out.println("WebServer 1.0 Listening");
		while (true) {
			try {
				clientSocket = serverSocket.accept();
				BufferedReader in = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream())
				);
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

				String line = null;
				while ((line = in.readLine()) != null) {
					String[] httpParts = line.split(" ");
					System.out.println("Filename: " + httpParts[1]);
					break;
					// System.out.println(">> " + line);
				}

				in.close();
				out.close();
				clientSocket.close();
			} catch(IOException e){
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args){
		WebServer webServer = new WebServer(80);
		webServer.handleRequests();
	}
}
