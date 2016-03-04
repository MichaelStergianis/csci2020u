package Spam;

import java.util.Map;
import java.util.TreeMap;


/**
 * Created by michael on 03/03/16.
 */
public class WordBag {
    private Map<String, Integer> map;

    WordBag(){
        map = new TreeMap<>();
    }

    public void incrementWord(String word){
        if (!map.containsKey(word)){
            map.put(word, 1);
        } else {
            map.put(word, (map.get(word) + 1) );
        }
    }
    public Integer getWord(String word){
        return map.get(word);
    }

}
