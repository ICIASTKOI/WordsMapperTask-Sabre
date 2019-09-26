package impl;

import service.AlphabetToWordsMapperService;
import service.InputService;
import service.WordMapperService;

import java.util.*;

public class WordMapperServiceImpl implements WordMapperService {

    private InputService inputService;
    private AlphabetToWordsMapperService alphabetToWordsMapperService;

    public WordMapperServiceImpl(InputService inputService, AlphabetToWordsMapperService alphabetToWordsMapperService){
        this.inputService = inputService;
        this.alphabetToWordsMapperService = alphabetToWordsMapperService;
    }

    @Override
    public TreeMap<Character, TreeSet<String>> mapWordsToLetters(String... string) {
        try {
            Set<String> validInput = (Set<String>) inputService.getOnlyValidInput(string);
            return (TreeMap<Character, TreeSet<String>>) alphabetToWordsMapperService.getMapOfIndexedInput(validInput);
        }catch(NullPointerException e) {
            e.printStackTrace();
        }
        return new TreeMap<>();
    }

}
