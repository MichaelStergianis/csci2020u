package Client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Irfaan on 28/03/2016.
 */
public class Datasource {
    static ObservableList<FileListing> clientFiles;
    static ObservableList<FileListing> serverFiles;
    public Datasource(){
        clientFiles = FXCollections.observableArrayList();
        serverFiles = FXCollections.observableArrayList();
    }
    public static ObservableList<FileListing> getClientFiles() {
        return clientFiles;
    }
    public static ObservableList<FileListing> getServerFiles() {
        return serverFiles;
    }

    public static void updateClientFiles(String[] files){
        ArrayList<FileListing> ar = new ArrayList<>();
        for (String file: files){
            ar.add(new FileListing(file));
        }
        clientFiles.setAll(ar);
    }
    public static void updateServerFiles(String[] files){
        ArrayList<FileListing> ar = new ArrayList<>();
        for (String file: files){
            ar.add(new FileListing(file));
        }
        serverFiles.setAll(ar);
    }
}
