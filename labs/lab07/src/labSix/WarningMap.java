package labSix;

import org.omg.CORBA.INTERNAL;

import java.io.*;
import java.util.TreeMap;

/**
 * Created by michael on 07/03/16.
 */
public class WarningMap {
    private TreeMap<String, Integer> warnMap;

    public WarningMap(){
        this.warnMap = new TreeMap<String, Integer>();
    }

    public void readWarn(){
        File file = new File("/home/michael/Documents/school/csci2020u/labs/lab07/out/production/lab07/labSix/weatherwarnings-2015.csv");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null){
                // Store the 6th column in a string and use to access map
                String warn = line.split(",")[5];
                if (warnMap.containsKey(warn)){
                    warnMap.put(warn, (warnMap.get(warn) + 1));
                } else {
                    warnMap.put(warn, 1);
                }

            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    public Integer[] getWarn(){
        Integer[] ret = new Integer[4];
        ret[0] = warnMap.get("FLASH FLOOD");
        ret[1] = warnMap.get("SEVERE THUNDERSTORM");
        ret[2] = warnMap.get("SPECIAL MARINE");
        ret[3] = warnMap.get("TORNADO");
        return ret;
    }
    public Integer[] getWarnNo(){
        // creates a collection and casts to Integer array to return
        return ( (Integer[]) warnMap.values().toArray());
    }

}
