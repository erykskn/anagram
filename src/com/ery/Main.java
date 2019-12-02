package com.ery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static String REGEX = "[a-zA-Z]+";

    public static void main(String[] args) throws IOException {
        Map<Character, BigInteger> map = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String name = reader.readLine();
            if (!name.matches(REGEX)) {
                throw new IllegalArgumentException("Not valid word!");
            }

            for (int index = 0; index < name.length(); index++) {
                Character char1 = name.charAt(index);
                BigInteger countOfChar = map.get(char1);
                if (countOfChar == null) {
                    countOfChar = BigInteger.ZERO;
                }
                map.put(char1, countOfChar.add(BigInteger.ONE));
            }

            System.out.println(calculateWordCount(map, BigInteger.valueOf(name.length())));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static BigInteger calculateWordCount(Map<Character, BigInteger> map, BigInteger wordLenght) {

        BigInteger countOfAnagram = BigInteger.ZERO;
        BigInteger wordFac = calcFactorial(wordLenght);
        BigInteger mapFac = BigInteger.ONE;

        for (Map.Entry<Character, BigInteger> chars : map.entrySet()) {
            BigInteger count = chars.getValue();
            if (count.compareTo(BigInteger.ONE) == 1) {
                mapFac = mapFac.multiply(calcFactorial(count));
            }
        }
        countOfAnagram = wordFac.divide(mapFac);
        return countOfAnagram;
    }

    private static BigInteger calcFactorial(BigInteger bigInteger) {

        if (bigInteger.compareTo(BigInteger.ONE) == 0) {
            return BigInteger.ONE;
        }
        BigInteger newInt = bigInteger;
        bigInteger = bigInteger.subtract(BigInteger.ONE);
        return calcFactorial(bigInteger).multiply(newInt);
    }
}
