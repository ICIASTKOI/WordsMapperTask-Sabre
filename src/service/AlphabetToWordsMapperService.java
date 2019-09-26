package service;

import java.util.*;


public interface AlphabetToWordsMapperService{
    Map<Character, TreeSet<String>> getMapOfIndexedInput(Collection<String> input);
}

