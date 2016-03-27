import java.io.*;
import java.net.*;

public final class WebServer {
	private ServerSocket serverSocket;

	public WebServer(int port){
		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void handleRequests(){
		try {
			Socket clientSocket = serverSocket.accept();
			ClientHandler clientHandler = new ClientHandler(clientSocket);
			Thread clientThread = new Thread(clientHandler);
			clientThread.start();

		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		WebServer webServer = new WebServer(8080);
		webServer.handleRequests();
	}
}
