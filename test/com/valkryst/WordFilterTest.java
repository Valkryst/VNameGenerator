package com.valkryst;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

public class WordFilterTest {
    @Test
    public void isAllowed() {
        // Generate 100,000 random strings:
        final Random random = new Random(System.currentTimeMillis());
        final String[] testData = new String[100000];

        for (int i = 0 ; i < testData.length ; i++) {
            testData[i] = new BigInteger(130, random).toString(32);
        }

        // Set a number of the strings to contain filtered words:
        testData[50000] = "Dog";
        testData[64742] = "Cat";
        testData[23123] = "ayhynhj Cat";
        testData[12253] = "dgrtgh Dog syhfgyi";

        // Create and set-up the filter:
        final WordFilter wordFilter = new WordFilter();
        wordFilter.addDisallowedString("Dog");
        wordFilter.addDisallowedString("Cat");

        // Run all strings through the filter:
        for (int i = 0 ; i < testData.length ; i++) {
            if (! wordFilter.isAllowed(testData[i])) {
                System.out.println("The String \"" + testData[i] + "\" contains a disallowed word. The index of the " +
                                   "testData array is " + i + ".");
            }
        }
    }
}
