package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by michael on 21/03/16.
 */
public class ClientConnectionHandler implements Runnable{
    private Socket clientSocket;

    public ClientConnectionHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
    public void run(){
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );
            String line = null;
            while ((line = in.readLine()) != null) {
                String[] request = line.split(" ");
                if (request[0].equals("DIR")) {
                    sendDir();
                } else if (request[0].equals("UPLOAD")) {
                    recieve(request[1]);
                } else if (request[0].equals("DOWNLOAD")) {

                } else {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendDir(){

    }
    private void recieve(String fileName){

    }
    private void send(String fileName){

    }
}
