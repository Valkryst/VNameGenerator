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

public class CombinatorialGeneratorTest {
    private final List<String> beginnings;
    private final List<String> middles;
    private final List<String> endings;

    public CombinatorialGeneratorTest() throws URISyntaxException, IOException {
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
        new CombinatorialGenerator(beginnings, endings);
    }

    @Test
    public void testConstructor_threeParams_withValidInput() {
        new CombinatorialGenerator(beginnings, middles, endings);
    }

    @Test(expected=NullPointerException.class)
    public void testConstructor_threeParams_withNullBeginnings() {
        new CombinatorialGenerator(null, middles, endings);
    }

    @Test
    public void testConstructor_threeParams_withNullMiddles() {
        new CombinatorialGenerator(beginnings, null, endings);
    }

    @Test(expected=NullPointerException.class)
    public void testConstructor_threeParams_withNullEndings() {
        new CombinatorialGenerator(beginnings, middles, null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_threeParams_withEmptyBeginnings() {
        new CombinatorialGenerator(new ArrayList<>(0), middles, endings);
    }

    @Test
    public void testConstructor_threeParams_withEmptyMiddles() {
        new CombinatorialGenerator(beginnings, new ArrayList<>(0), endings);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_threeParams_withEmptyEndings() {
        new CombinatorialGenerator(beginnings, middles, new ArrayList<>(0));
    }

    @Test
    public void testGenerateName_withZeroLength() {
        final CombinatorialGenerator nameGenerator = new CombinatorialGenerator(beginnings, middles, endings);
        final String result = nameGenerator.generateName(0);
        Assert.assertTrue(result.length() >= 2);
    }

    @Test
    public void  testGenerateName_withOneLength() {
        final CombinatorialGenerator nameGenerator = new CombinatorialGenerator(beginnings, middles, endings);
        final String result = nameGenerator.generateName(1);
        Assert.assertTrue(result.length() >= 2);
    }

    @Test
    public void  testGenerateName_withTwoLength() {
        final CombinatorialGenerator nameGenerator = new CombinatorialGenerator(beginnings, middles, endings);
        final String result = nameGenerator.generateName(2);
        Assert.assertTrue(result.length() >= 2);
    }

    @Test
    public void  testGenerateName_withThreeToTwentyLength() {
        final CombinatorialGenerator nameGenerator = new CombinatorialGenerator(beginnings, middles, endings);
        for (int i = 3 ; i < 20 ; i++) {
            final String result = nameGenerator.generateName(i);
            Assert.assertTrue(result.length() >= i);
        }
    }
}
