package Client;

import java.io.*;
import java.net.Socket;

/**
 * Created by michael on 25/03/16.
 */
public class ConnectionHandler {
    private Socket serverSocket;
    private String iNet;
    private int port;
    private File sharedFolder;

    public ConnectionHandler(String iNet, int port, File sharedFolder){
        try {
            this.serverSocket = new Socket(iNet, port);
            this.sharedFolder = sharedFolder;
            this.iNet = iNet;
            this.port = port;
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String[] receiveDirs(){
        // This function will connect to our server
        // when the directories start coming in
        // they will come in as a comma separated list
        // print(fileName + ",");
        // so string.split(","); will be able to be called
        try {
            if (serverSocket.isClosed()){
                serverSocket = new Socket(iNet, port);
            }
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream());
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(serverSocket.getInputStream()
            ));
            out.println("DIR");
            out.flush();
            String line = in.readLine();
            String[] dirs = line.split(",");
            out.close();
            in.close();
            serverSocket.close();
            return dirs;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public void receive(String fileName){
        try {
            if (serverSocket.isClosed()){
                serverSocket = new Socket(iNet, port);
            }
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream());
            InputStream inStream = serverSocket.getInputStream();
            // function fileCollision prevents file.exists() so we just create
            File receiveFile = new File(sharedFolder, fileCollision(fileName, 0));
            receiveFile.createNewFile();
            FileOutputStream fout = new FileOutputStream(receiveFile);
            out.println("DOWNLOAD " + fileName);
            out.flush();
            int inByte = inStream.read();
            while ( inByte != -1){
                fout.write(inByte);
                inByte = inStream.read();
            }
            fout.flush();
            fout.close();
            out.close();
            inStream.close();
            fout.close();
            serverSocket.close();
        } catch (IOException e ){
            e.printStackTrace();
        }
    }
    public void send(File parent, String fileName){
        try {
            if (serverSocket.isClosed()){
                serverSocket = new Socket(iNet, port);
            }
            File file = new File(parent, fileName);
            if (file.isDirectory()){
                UI.showError("Cannot Send Directories", "",
                        "You must choose a regular file, not a directory");
                return;
            }
            FileInputStream fin = new FileInputStream(file);
            OutputStream outStream = serverSocket.getOutputStream();
            PrintWriter out = new PrintWriter( outStream );
            out.println("UPLOAD " + fileName);
            out.flush();
            int inByte = fin.read();
            while (inByte != -1){
                outStream.write(inByte);
                inByte = fin.read();
            }
            outStream.flush();
            out.close();
            outStream.close();
            fin.close();
            serverSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public String fileCollision(String fileName, int i){
        File file;
        if (i > 0){
            file = new File(sharedFolder, fileName + " (" + i + ")");
        } else {
            file = new File(sharedFolder, fileName);
        }
        if (!file.exists()){
            return file.getName();
        } else {
            return fileCollision(fileName, ++i);
        }
    }
}
