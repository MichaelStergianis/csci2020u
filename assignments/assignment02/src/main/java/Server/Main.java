package Server;

import java.io.File;

/**
 * Created by michael on 21/03/16.
 */
public class Main {
    public static void main (String[] args){
        FileServer fs = new FileServer(8080, new File("."));
        fs.handleRequests();
    }
}
