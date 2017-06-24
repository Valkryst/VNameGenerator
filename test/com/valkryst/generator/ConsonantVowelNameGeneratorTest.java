package com.valkryst.generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConsonantVowelNameGeneratorTest {
    private final List<String> consonants = new ArrayList<>();
    private final List<String> vowels = new ArrayList<>();

    private ConsonantVowelNameGenerator nameGenerator;

    @Before
    public void initalizeVariables() {
        consonants.clear();
        vowels.clear();

        consonants.add("al");
        consonants.add("an");
        consonants.add("ar");
        consonants.add("as");
        consonants.add("at");
        consonants.add("ea");
        consonants.add("ed");
        consonants.add("en");
        consonants.add("er");
        consonants.add("es");
        consonants.add("ha");
        consonants.add("he");
        consonants.add("hi");
        consonants.add("in");
        consonants.add("is");
        consonants.add("it");
        consonants.add("le");
        consonants.add("me");
        consonants.add("nd");
        consonants.add("ne");
        consonants.add("ng");
        consonants.add("nt");
        consonants.add("on");
        consonants.add("or");
        consonants.add("ou");
        consonants.add("re");
        consonants.add("se");
        consonants.add("st");
        consonants.add("te");
        consonants.add("th");
        consonants.add("ti");
        consonants.add("to");
        consonants.add("ve");
        consonants.add("wa");
        consonants.add("it");

        vowels.add("a");
        vowels.add("e");
        vowels.add("i");
        vowels.add("o");
        vowels.add("u");
        vowels.add("y");

        nameGenerator = new ConsonantVowelNameGenerator(consonants, vowels);
    }

    @Test
    public void testConstructor_withValidConsonantsAndVowels() {
        nameGenerator = new ConsonantVowelNameGenerator(consonants, vowels);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withNullConsonants() {
        nameGenerator = new ConsonantVowelNameGenerator(null, vowels);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withNullVowels() {
        nameGenerator = new ConsonantVowelNameGenerator(consonants, null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withEmptyConsonants() {
        nameGenerator = new ConsonantVowelNameGenerator(new ArrayList<>(0), vowels);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withEmptyVowels() {
        nameGenerator = new ConsonantVowelNameGenerator(consonants, new ArrayList<>(0));
    }

    @Test
    public void testGenerateName_withZeroLength() {
        final String result = nameGenerator.generateName(0);
        Assert.assertEquals(2, result.length());
    }

    @Test
    public void  testGenerateName_withOneLength() {
        final String result = nameGenerator.generateName(1);
        Assert.assertEquals(2, result.length());
    }

    @Test
    public void  testGenerateName_withTwoLength() {
        final String result = nameGenerator.generateName(2);
        Assert.assertEquals(2, result.length());
    }

    @Test
    public void  testGenerateName_withThreeToTwentyLength() {
        for (int i = 3 ; i < 20 ; i++) {
            final String result = nameGenerator.generateName(i);
            Assert.assertEquals(i, result.length());
        }
    }
}
