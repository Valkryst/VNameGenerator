package com.valkryst.VNameGenerator;

import com.valkryst.VNameGenerator.generator.ConsonantVowelGenerator;
import org.junit.Assert;
import org.junit.Test;

public class ConsonantVowelGeneratorTest {
    private final String[] consonants = new String[] {
        "al", "an", "ar", "as", "at", "ea", "ed", "en", "er", "es", "ha", "he", "hi", "in", "is", "it",
        "le", "me", "nd", "ne", "ng", "nt", "on", "or", "ou", "re", "se", "st", "te", "th", "ti", "to",
        "ve", "wa", "it"
    };

    private final String[] vowels = new String[] {
            "a", "e", "i", "o", "u", "y"
    };

    @Test
    public void testConstructor_withValidConsonantsAndVowels() {
        new ConsonantVowelGenerator(consonants, vowels);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withNullConsonants() {
        new ConsonantVowelGenerator(null, vowels);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withNullVowels() {
        new ConsonantVowelGenerator(consonants, null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withEmptyConsonants() {
        new ConsonantVowelGenerator(new String[0], vowels);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withEmptyVowels() {
        new ConsonantVowelGenerator(consonants, new String[0]);
    }

    @Test
    public void testGenerateName_withZeroLength() {
        final String result = new ConsonantVowelGenerator(consonants, vowels).generateName(0);
        Assert.assertEquals(2, result.length());
    }

    @Test
    public void  testGenerateName_withOneLength() {
        final String result = new ConsonantVowelGenerator(consonants, vowels).generateName(1);
        Assert.assertEquals(2, result.length());
    }

    @Test
    public void  testGenerateName_withTwoLength() {
        final String result = new ConsonantVowelGenerator(consonants, vowels).generateName(2);
        Assert.assertEquals(2, result.length());
    }

    @Test
    public void  testGenerateName_withThreeToTwentyLength() {
        for (int i = 3 ; i < 20 ; i++) {
            final String result = new ConsonantVowelGenerator(consonants, vowels).generateName(i);
            Assert.assertEquals(i, result.length());
        }
    }
}
