package com.valkryst.generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MarkovGeneratorTest {
    private MarkovGenerator nameGenerator;
    private final List<String> trainingNames;

    public MarkovGeneratorTest() throws URISyntaxException, IOException {
        final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        final URL resourceUrl = classloader.getResource("Human/Viking/Male.txt");
        trainingNames = Files.readAllLines(Paths.get(resourceUrl.toURI()));
    }

    @Before
    public void initializeVariables() {
        nameGenerator = new MarkovGenerator(trainingNames);
    }

    @Test
    public void testConstructor_withValidTrainingNames() {
        nameGenerator = new MarkovGenerator(trainingNames);
    }

    @Test(expected=NullPointerException.class)
    public void testConstructor_withNullTrainingNames() {
        nameGenerator = new MarkovGenerator(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withEmptyTrainingNames() {
        nameGenerator = new MarkovGenerator(new ArrayList<>(0));
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
