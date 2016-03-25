package Server;

import java.io.*;
import java.net.Socket;

/**
 * Created by michael on 21/03/16.
 */
public class ClientConnectionHandler implements Runnable{
    private Socket clientSocket;
    private File sharedFile;

    public ClientConnectionHandler(Socket clientSocket, File sharedFile){
        this.clientSocket = clientSocket;
        this.sharedFile = sharedFile;
    }
    public void run(){
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );
            String line = in.readLine();
            System.out.println(line);
            String[] request = line.split(" ");
            if (request[0].equals("DIR")) {
                sendDir();
            } else if (request[0].equals("UPLOAD")) {
                recieve(request[1], in);
            } else if (request[0].equals("DOWNLOAD")) {
                send(request[1]);
            } else {
                returnError();
            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendDir(){
        String[] dirs = sharedFile.list();
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
            // The server will send a list of file names separated by commas
            for (String file: dirs) {
                out.print(file + ",");
            }
            out.flush();
            out.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    private void recieve(String fileName, BufferedReader in){
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(new File(sharedFile, fileName)));
            String line;
            while ( (line = in.readLine()) != null){
               out.print(line);
            }
            out.flush();
            out.close();
            in.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    private void send(String fileName){

    }
    private void returnError(){

    }
}
