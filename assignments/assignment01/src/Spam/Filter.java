package Spam;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Filter
 *
 * Designed to do the training and filtering for our spam detector
 *
 * Word counting code provided by Randy Fourtier
 */
public class Filter {
    private WordBag spamBag;
    private WordBag hamBag;
    private double hamCount, spamCount;

    public Filter(){
        spamBag = new WordBag();
        hamBag = new WordBag();
    }

    private void _train(File file, WordBag bag, double fileCount) throws IOException{
        if (file.isDirectory()){
            // process every file in directory
            File[] filesInDir = file.listFiles();
            for (int i = 0; i < filesInDir.length; i++){
                _train(filesInDir[i], bag, fileCount);
            }
        } else if (file.exists()) {
            // load all of the data, and process it into words
            fileCount++;
            Scanner scan = new Scanner(file);
            while (scan.hasNext()){
                String word = scan.next();
                if (isWord(word)) {
                    bag.incrementWord(word);
                }
            }
        }
    }

    public void trainSpam(File file){
        try {
            _train(file, spamBag, spamCount);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void trainHam(File file){
        try {
            _train(file, hamBag, hamCount);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean isWord(String str){
        // the contents of matches parameter is a regex
        // the regex says to consider beginning of a line a-z cap and lower
        // for zero or more times and the end of a line
        if (str.matches("^[a-zA-Z]*$")) return true;
        return false;
    }

}
