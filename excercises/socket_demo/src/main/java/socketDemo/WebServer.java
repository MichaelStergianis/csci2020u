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
				Socket clientSocket = serverSocket.accept();
				BufferedReader in = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream())
				);
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

				String line = null;
				while ((line = in.readLine()) != null) {
					System.out.println(">> " + line);
				}

				in.close();
				out.close();
				clientSocket.close();
			} catch(IOException e){
				e.printStackTrace();
			}

		}
	}
}
