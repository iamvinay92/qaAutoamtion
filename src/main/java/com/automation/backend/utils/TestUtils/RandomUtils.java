package com.automation.backend.utils.TestUtils;

import java.util.Random;

public class RandomUtils {

    static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("res 1" + generateRandomAlphaNumericString(12));
        System.out.println("res 2" + generateRandomNumber(6));
        System.out.println("res 2" + generateRandomString(7));
    }

    public static String generateRandomAlphaNumericString(Integer length) {
        String text = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < length; i++) {
            sb.append(text.charAt(random.nextInt(text.length())));
        }
        return sb.toString().toLowerCase();
    }

    public static Integer generateRandomNumber(Integer length) {
        String text = "1234567890";
        StringBuffer sb = new StringBuffer();

        for (int i = 1; i < length; i++) {
            sb.append(text.charAt(random.nextInt(text.length())));
        }
        return Integer.valueOf(String.valueOf(sb));
    }

    public static String generateRandomString(Integer length) {
        String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer sb = new StringBuffer();

        for (int i = 1; i < length; i++) {
            sb.append(text.charAt(random.nextInt(text.length())));
        }
        return sb.toString().toLowerCase();
    }
}
