package sample;

import java.util.*;

public class RandomCategoryGenerator {
    private final static String songs[] = {"pop", "rock", "disco-polo", "metal", "reggae"};
    private final static List<String> availableCategories = new ArrayList<>(Arrays.asList(songs));

    public static String generateRandomCategory(){
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(songs.length);
        return availableCategories.get(randomNumber);
    }
}
