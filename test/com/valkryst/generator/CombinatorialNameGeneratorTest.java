package com.valkryst.generator;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CombinatorialNameGeneratorTest {
    private final List<String> beginnings;
    private final List<String> middles;
    private final List<String> endings;

    public CombinatorialNameGeneratorTest() throws URISyntaxException, IOException {
        final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL resourceUrl = classloader.getResource("Titles/Dune/Titles.txt");
        beginnings = Files.readAllLines(Paths.get(resourceUrl.toURI()));

        resourceUrl = classloader.getResource("Dwarven/Fantasy/Khordaldrum_Last_A.txt");
        middles = Files.readAllLines(Paths.get(resourceUrl.toURI()));

        resourceUrl = classloader.getResource("Dwarven/Fantasy/Khordaldrum_Last_B.txt");
        endings = Files.readAllLines(Paths.get(resourceUrl.toURI()));
    }

    @Test
    public void testConstructor_twoParams_withValidInput() {
        new CombinatorialNameGenerator(beginnings, endings);
    }

    @Test
    public void testConstructor_threeParams_withValidInput() {
        new CombinatorialNameGenerator(beginnings, middles, endings);
    }

    @Test(expected=NullPointerException.class)
    public void testConstructor_threeParams_withNullBeginnings() {
        new CombinatorialNameGenerator(null, middles, endings);
    }

    @Test
    public void testConstructor_threeParams_withNullMiddles() {
        new CombinatorialNameGenerator(beginnings, null, endings);
    }

    @Test(expected=NullPointerException.class)
    public void testConstructor_threeParams_withNullEndings() {
        new CombinatorialNameGenerator(beginnings, middles, null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_threeParams_withEmptyBeginnings() {
        new CombinatorialNameGenerator(new ArrayList<>(0), middles, endings);
    }

    @Test
    public void testConstructor_threeParams_withEmptyMiddles() {
        new CombinatorialNameGenerator(beginnings, new ArrayList<>(0), endings);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_threeParams_withEmptyEndings() {
        new CombinatorialNameGenerator(beginnings, middles, new ArrayList<>(0));
    }

    @Test
    public void testGenerateName_withZeroLength() {
        final CombinatorialNameGenerator nameGenerator = new CombinatorialNameGenerator(beginnings, middles, endings);
        final String result = nameGenerator.generateName(0);
        Assert.assertTrue(result.length() >= 2);
    }

    @Test
    public void  testGenerateName_withOneLength() {
        final CombinatorialNameGenerator nameGenerator = new CombinatorialNameGenerator(beginnings, middles, endings);
        final String result = nameGenerator.generateName(1);
        Assert.assertTrue(result.length() >= 2);
    }

    @Test
    public void  testGenerateName_withTwoLength() {
        final CombinatorialNameGenerator nameGenerator = new CombinatorialNameGenerator(beginnings, middles, endings);
        final String result = nameGenerator.generateName(2);
        Assert.assertTrue(result.length() >= 2);
    }

    @Test
    public void  testGenerateName_withThreeToTwentyLength() {
        final CombinatorialNameGenerator nameGenerator = new CombinatorialNameGenerator(beginnings, middles, endings);
        for (int i = 3 ; i < 20 ; i++) {
            final String result = nameGenerator.generateName(i);
            Assert.assertTrue(result.length() >= i);
        }
    }
}
