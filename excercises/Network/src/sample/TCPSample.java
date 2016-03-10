package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by michael on 03/03/16.
 */
public class TCPSample {
    public void main(String[] args){
        InputStreamReader
    }


}

class TCP implements Runnable{
    private ServerSocket serverSocket;
    public void TCP(){
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void run() {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
