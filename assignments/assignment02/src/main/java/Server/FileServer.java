package Server;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by michael on 21/03/16.
 */
public class FileServer {
    private ServerSocket serverSocket;
    private File sharedFile;

    public FileServer(){
        try {
            serverSocket = new ServerSocket(8020);
            this.sharedFile = new File(".");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public FileServer(int port, File sharedFile){
        try {
            serverSocket = new ServerSocket(port);
            this.sharedFile = sharedFile;
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public FileServer(int port){
        try {
            serverSocket = new ServerSocket(port);
            sharedFile = new File(".");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void handleRequests(){
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread clientThread = new Thread(
                        new ClientConnectionHandler(clientSocket, sharedFile
                        ));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}