import java.io.*;
import java.util.*;

public class FindAverage {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage:  java FindAverage <inputFile> <columnName>");
            System.exit(0);
        }

        File inFile = new File(args[0]);
        String desiredColumnName = args[1].trim();

        try {
            BufferedReader in = new BufferedReader(new FileReader(inFile));
            String line = in.readLine();
            String[] columnNames = line.split(",");
            
            // find the column to be used for the average
            int desiredColumnIndex = -1;
            for (int i = 0; i < columnNames.length; i++) {
                if (columnNames[i].toLowerCase().equals(desiredColumnName.toLowerCase())) {
                    desiredColumnIndex = i;
                    break;
                }
            }
            
            if (desiredColumnIndex >= 0) {
                // calculate the average
                float total = 0f;
                int count = 0;
                while ((line = in.readLine()) != null) {
                    String[] data = line.split(",");
                    float nextMark = Float.parseFloat(data[desiredColumnIndex]);
                    total += nextMark;
                    count++;
                }

                System.out.printf("The average for %s is $%.2f.\n",desiredColumnName, (total/count));
            } else {
                System.err.println("Could not find column '" + desiredColumnName + "'.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
