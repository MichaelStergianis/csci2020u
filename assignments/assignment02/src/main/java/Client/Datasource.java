package Client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Irfaan on 28/03/2016.
 */
public class Datasource {
    static ObservableList<String> clientFiles;
    static ObservableList<String> serverFiles;
    public Datasource(){
        clientFiles = FXCollections.observableArrayList();
        serverFiles = FXCollections.observableArrayList();
    }
    public static ObservableList<String> getClientFiles() {
        return clientFiles;
    }
    public static ObservableList<String> getServerFiles() {
        return serverFiles;
    }

    public static void addClientFile(String file){
        clientFiles.add(file);
    }
    public static void addServerFile(String file){
        serverFiles.add(file);
    }
}
