package assignment06;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

//Class is used to generate a list of random 32 character strings for testing with large amounts of strings
public class StringList {
    public static ArrayList<String> buildStringList(int size) {
        ArrayList<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            String word = generateString();
            list.add(word);
        }
        return list;
    }

    private static String generateString() {
        int leftLimit = 97;
        int rightLimit = 122;
        Random random = new Random();
        int randomStringLength = ThreadLocalRandom.current().nextInt(1, 50);

        return random.ints(leftLimit, rightLimit + 1)
                .limit(randomStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}

