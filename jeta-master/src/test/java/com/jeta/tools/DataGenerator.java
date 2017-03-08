package com.jeta.tools;

import java.util.Random;

/**
 * Created by yksenofontov on 08.03.2017.
 */
public class DataGenerator {
    private final static String alpha_num_source = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String generateString(int length) {
        char[] text = new char[length];
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            text[i] = alpha_num_source.charAt(rand.nextInt(alpha_num_source.length()));
        }
        return new String(text);
    }
}
