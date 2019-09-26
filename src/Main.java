import impl.AlphabetToWordsMapperServiceImpl;
import impl.InputServiceImpl;
import impl.WordMapperServiceImpl;
import service.WordMapperService;

import java.util.logging.Logger;


public class Main {


    public static void main(String... args)
    {
        String[] array = new String[]{" ala ma kota, kot koduje w Javie kota ", null};
        WordMapperService wordMapperService = new WordMapperServiceImpl(new InputServiceImpl(),new AlphabetToWordsMapperServiceImpl());
        wordMapperService.mapWordsToLetters(array).forEach((x,y) -> System.out.println(x + " || " + y));

    }
}
