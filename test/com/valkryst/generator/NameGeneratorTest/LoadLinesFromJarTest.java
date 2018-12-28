package com.valkryst.generator.NameGeneratorTest;

import com.valkryst.generator.NameGenerator;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class LoadLinesFromJarTest {
    @Test (expected = NullPointerException.class)
    public void testLoadLinesFromJar_withNullFilePath() throws IOException {
        NameGenerator.loadLinesFromJar(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testLoadLinesFromJar_withEmptyFilePath() throws IOException {
        NameGenerator.loadLinesFromJar("");
    }
}
