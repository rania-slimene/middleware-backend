package com.example.cbsmiddleware.AccountType.Service;


import java.util.Random;

public class ShortNameGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789*/-+_";
    private static final int SHORT_NAME_LENGTH = 4;

    public static String generateShortName() {
        StringBuilder shortName = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < SHORT_NAME_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            shortName.append(randomChar);
        }

        return shortName.toString();
    }
}