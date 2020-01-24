package com.ninebudget.util;

import org.apache.commons.text.RandomStringGenerator;

public class RandomUtil {
    private RandomUtil() {
    }

    public static String generatePassword(){
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder()
                .withinRange(33, 45)
                .build();
        return pwdGenerator.generate(20);
    }

    public static String generateResetKey(){
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder()
                .withinRange(33, 45)
                .build();
        return pwdGenerator.generate(20);
    }

    public static String generateActivationKey(){
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder()
                .withinRange(33, 45)
                .build();
        return pwdGenerator.generate(20);
    }
}
