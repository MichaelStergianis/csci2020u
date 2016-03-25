package socketDemo;

import java.io.*;
import java.net.*;

public final class WebServer {
    private ServerSocket serverSocket = null;
    
    public WebServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void handleRequests() {
        System.out.println("WebServer v1.0 Listening");
        while (true) {
            try {
								// blocking function
                Socket clientSocket = serverSocket.accept();
                
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                        clientSocket.getInputStream()
                                         )
                );
                
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                
                
                String line = null;
								line = in.readLine();
                String[] httpParts = line.split(" ");
                System.out.println("Filename: " + httpParts[1]);
                while ((line = in.readLine()) != null) {
                    System.out.println(">>" + line);
                }
								File file = new File("www"+httpParts[1]);
								if (file.exists()){
									out.print("HTTP/1.0 200 Ok\r\n");
									// copy all of the bytes from the file to the socket
									FileInputStream fileIn = new FileInputStream(file);
									OutputStream socketOut = clientSocket.getOutputStream();
									byte[] buffer = new byte[512];
									int numBytes;
									while ( (numBytes = fileIn.read(buffer)) > 0 ){
										socketOut.write(buffer, 0, numBytes);
									}
									fileIn.close();
								} else {
									out.print("HTTP/1.0 404 Not Found\r\n");
								}
                
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        WebServer webServer = new WebServer(8080);
        webServer.handleRequests();
    }
}
