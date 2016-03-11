package main.java.SpamFilter;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
    private TreeMap<String, Double> probMap;
    private boolean probChange;

    public Filter(){
        spamBag = new WordBag();
        hamBag = new WordBag();
        probMap = new TreeMap<>();
        probChange = true;
    }

    private void _train(File file, WordBag bag) throws IOException{
        if (file.isDirectory()){
            // process every file in directory
            File[] filesInDir = file.listFiles();
            for (int i = 0; i < filesInDir.length; i++){
                _train(filesInDir[i], bag);
            }
        } else if (file.exists()) {
            // load all of the data, and process it into words
            // use another tree map to count if the word alread exists
            // this will prevent multiple counts of a word from the same file
            TreeMap<String, Integer> fileMap = new TreeMap<>();
            bag.incrementFiles();
            Scanner scan = new Scanner(file);
            while (scan.hasNext()){
                String word = scan.next().toLowerCase();
                if (isWord(word) && !fileMap.containsKey(word)) {
                    fileMap.put(word, 1);
                    bag.incrementWord(word);
                }
            }
            scan.close();
        }
    }

    public void trainSpam(File file){
        probChange = true;
        try {
            _train(file, spamBag);
            System.gc();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void trainHam(File file){
        probChange = true;
        try {
            // Training will create a lot of objects, if the garbage
            // collector does not automatically run, we want to run it
            _train(file, hamBag);
            System.gc();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public double spamProb(String word){
        return (spamBag.getWord(word) / spamBag.getNumFiles());
    }

    public double hamProb(String word){
        return (hamBag.getWord(word) / hamBag.getNumFiles());
    }

    private void genSpamProb(){
        if (!probChange) return;
        String curr;
        double spPr;
        // only iterate over spam space
        // if there is a word only in ham, it's probability will default
        // to zero regardless of whether it is in our bag
        Iterator<String> iter = spamBag.getBagIter();
        while (iter.hasNext()){
            curr = iter.next();
            spPr = spamProb(curr);
            probMap.put(curr,
                    (spPr / (spPr + hamProb(curr)))
            );
        }
        probChange = false;
    }

    public double probSpam(String word){
        // if it doesn't need to be done, genSpamProb will return immediately
        genSpamProb();
        Double p = probMap.get(word);
        if (p != null) return (p);
        else return 0.0;
    }


    public boolean isWord(String str){
        // the contents of matches parameter is a regex
        // the regex says to consider beginning of a line a-z caps and lowercase
        // for zero or more times and the end of a line
        return (str.matches("^[a-zA-Z]*$"));
    }

    /*
     * eehta
     *
     * computes the eehta value for a single word
     */
    private double eehta(String word){
        // use an identity to simplify the computation of eehta
        // log(x) - log(y) = log(x / y)
        // normalize the values 1 and 0 to something that is not undefined
        // when log is taken
        double spPr = probSpam(word);
        Double result;
        if (spPr >= 1){
            spPr = 0.99999999999999999;
        } else if (spPr == 0){
            spPr = 0.00000000000000001;
        }
        result = Math.log( (1 - spPr) / spPr );
        return result;
    }

    public double test(File file, List<TestFile> fileList) {
        try {
            if (file.isDirectory()) {
                // process every file in directory
                File[] filesInDir = file.listFiles();
                for (int i = 0; i < filesInDir.length; i++) {
                    test(filesInDir[i], fileList);
                }
            } else if (file.exists()) {
                // load all of the data, and process it into words
                Scanner scan = new Scanner(file);
                double eehta = 0;
                while (scan.hasNext()) {
                    String word = scan.next().toLowerCase();
                    if (isWord(word)) {
//                        System.out.println("----------------------");
//                        System.out.println(eehta);
                        eehta += eehta(word);
//                        System.out.println(eehta);
//                        System.out.println("----------------------");
                    }
                }
                // calculate probability that file is spam
                double prSF = (1 / (1 + Math.pow(Math.E, eehta)));
                String parentName;
                if (file.getParentFile().getName().equalsIgnoreCase("ham")){
                    parentName = "Ham";
                } else if (file.getParentFile().getName().equalsIgnoreCase("spam")){
                    parentName = "Spam";
                } else {
                    parentName = "void";
                    System.err.println("Error occured, directory improperly set");
                }
                fileList.add(new TestFile(file.getName(), prSF, parentName));
                scan.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return 0.0;
    }
}
