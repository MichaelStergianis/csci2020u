package Client;

import java.io.*;
import java.net.Socket;

/**
 * Created by michael on 25/03/16.
 */
public class ConnectionHandler {
    private Socket serverSocket;

    public ConnectionHandler(Socket serverSocket){
        this.serverSocket = serverSocket;
    }

    public String[] receiveDirs(){
        // This function will connect to our server
        // when the directories start coming in
        // they will come in as a comma separated list
        // print(fileName + ",");
        // so string.split(","); will be able to be called
        try {
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream());
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(serverSocket.getInputStream()
            ));
            out.println("DIR");
            out.flush();
            String line = in.readLine();
            System.out.println(line);
            String[] dirs = line.split(",");
            return dirs;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
