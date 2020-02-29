package com.valkryst.VNameGenerator.NameGeneratorTest;

import com.valkryst.VNameGenerator.generator.NameGenerator;
import org.junit.Test;

import java.io.IOException;

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
