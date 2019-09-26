import impl.AlphabetToWordsMapperServiceImpl;
import impl.InputServiceImpl;
import impl.WordMapperServiceImpl;
import org.junit.Before;
import org.junit.Test;
import service.WordMapperService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import static org.junit.Assert.assertTrue;

public class GeneralTest {

    WordMapperService wordMapperService;

    @Before
    public void setUpTest(){
        wordMapperService = new WordMapperServiceImpl(new InputServiceImpl(),new AlphabetToWordsMapperServiceImpl());
    }

    @Test
    public void shouldReturnEmptyMap(){
        String[] array = new String[]{null};
        assertTrue(wordMapperService.mapWordsToLetters().isEmpty());
        assertTrue(wordMapperService.mapWordsToLetters(array).isEmpty());
        assertTrue(wordMapperService.mapWordsToLetters(null).isEmpty());
    }

    @Test
    public void shouldIgnoreEmptyStrings(){
        String[] array = new String[]{"a", "", "e"};
       wordMapperService.mapWordsToLetters(array).forEach((x,y)->{
           y.forEach( word -> assertTrue(!word.isEmpty()));
       });

    }

    @Test
    public void shouldReturnMapKeysAlphabetical(){
        String[] array = new String[]{"z","a","h"};
        List<Character> before = new ArrayList<>();
        List<Character> after = new ArrayList<>();
        after.add('a');
        after.add('h');
        after.add('z');
        wordMapperService.mapWordsToLetters(array).forEach((x,y) -> {
           before.add(x);
        });
        assertTrue(before.get(0).equals(after.get(0)));
        assertTrue(before.get(1).equals(after.get(1)));
        assertTrue(before.get(2).equals(after.get(2)));
    }

    @Test
    public void shouldIgnoreSpecialsInsideWordAndSplitThisWordOnTwoParts(){
        String[] array = new String[]{"kot!ka"};
        wordMapperService.mapWordsToLetters(array).forEach((x,y)->{
            y.forEach( word -> assertTrue(word.contains("kot") || word.contains("ka") ));
        });
    }
    @Test
    public void shouldIgnoreSpecialsAfterWord(){
        String[] array = new String[]{"niechce! lubi, masło"};
        wordMapperService.mapWordsToLetters(array).forEach((x,y)->{
            y.forEach( word -> assertTrue(word.contains("niechce") || word.contains("lubi") || word.contains("masło") ));
        });
    }

    @Test
    public void shouldIgnoreDuplicates(){
        String[] array = new String[]{"kotka kotka, kotka", "kotka, kotka", "kotka"};
        Map<Character, TreeSet<String>> map = wordMapperService.mapWordsToLetters(array);
        assertTrue(map.size() == 4 && map.get('k').size() == 1);
    }

    @Test
    public void shouldReturnMapWithOneKey(){
        String[] array = new String[]{"aaaa a, aa!", "a    ,", "a"};
        Map<Character, TreeSet<String>> map = wordMapperService.mapWordsToLetters(array);
        assertTrue(map.size() == 1);
    }

    @Test
    public void shouldReturnMapValuesHashSetInAlphabeticOrder(){
        String[] array = new String[]{"cba","abc","cab"};
        List<String> before = new ArrayList<>();
        List<String> after = new ArrayList<>();
        Map<Character, TreeSet<String>> map = wordMapperService.mapWordsToLetters(array);
        assertTrue( map.get('a').pollFirst().equals("abc"));
        assertTrue(map.get('a').pollFirst().equals("cab"));
        assertTrue(map.get('a').pollFirst().equals("cba"));
    }

    @Test
    public void shouldIgnoreOnlyNumbersString(){
        String[] array = new String[]{"111", "", "e"};
        wordMapperService.mapWordsToLetters(array).forEach((x,y)->{
            y.forEach( word -> assertTrue(!word.contains("1")));
        });
    }

    @Test
    public void shouldIgnoreSpecials(){
        String[] array = new String[]{"111", "", "e!","####", "asdf", "asdf***"};
        wordMapperService.mapWordsToLetters(array).forEach((x,y)->{
            y.forEach( word -> assertTrue(!word.contains("*") &&!word.contains("!") && !word.contains("#") ));
        });
    }

}
