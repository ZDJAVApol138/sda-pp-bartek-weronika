package pl.sda.bankapp.utils;

import java.util.Random;

public class AccountNumberGenerator {

    public static final String SPACE = " ";

    public static String generate() {
        final Random random = new Random();

        final StringBuilder sb = new StringBuilder();


        for (int i = 0; i < 26; i++) {

            int number = random.nextInt(0, 10);
            sb.append(number);
        }
        sb.insert(2, SPACE);
        sb.insert(7, SPACE);
        sb.insert(12, SPACE);
        sb.insert(17, SPACE);
        sb.insert(22, SPACE);
        sb.insert(27, SPACE);


        return sb.toString();
    }
}