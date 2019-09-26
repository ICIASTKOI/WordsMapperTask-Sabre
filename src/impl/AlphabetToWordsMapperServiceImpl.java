package impl;

import service.AlphabetToWordsMapperService;


import java.util.*;


public class AlphabetToWordsMapperServiceImpl implements AlphabetToWordsMapperService{


    @Override
    public Map<Character, TreeSet<String>> getMapOfIndexedInput(Collection<String> input)
    {
        Map<Character, TreeSet<String>> map = new TreeMap<>();
        input.forEach(word ->{
        for(char  c : word.toCharArray()){
            handleMapping(map, word, c);
        }});
        return  map;
    }

    private void handleMapping(Map<Character, TreeSet<String>> map, String word, char c) {
        if(map.get(c) == null && Character.isLetter(c)){
            Set<String> set = getStrings(word);
            map.put(c, (TreeSet<String>) set);
        }else if(Character.isLetter(c)){
            map.get(c).add(word);
        }
    }

    private Set<String> getStrings(String word) {
        Set<String> set = new TreeSet<>();
        set.add(word);
        return set;
    }
}
