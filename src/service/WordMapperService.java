package service;


import java.util.TreeMap;
import java.util.TreeSet;

public interface WordMapperService {
    TreeMap<Character, TreeSet<String>> mapWordsToLetters(String... string);
}
