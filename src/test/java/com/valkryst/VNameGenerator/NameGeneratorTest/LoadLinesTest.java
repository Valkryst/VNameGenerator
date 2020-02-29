package com.valkryst.VNameGenerator.NameGeneratorTest;

import com.valkryst.VNameGenerator.generator.NameGenerator;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoadLinesTest {
    @BeforeClass
    public static void createTestFiles() throws IOException {
        final File emptyFile = new File("EmptyFile.txt");
        final File nonEmptyFile = new File("NonEmptyFile.txt");
        final File nonEmptyFileWithBlankLines = new File("NonEmptyFileWithBlankLines.txt");

        if (emptyFile.createNewFile() == false) {
            Assert.fail("EmptyFile.txt not created before running tests.");
        }

        if (nonEmptyFile.createNewFile() == false) {
            Assert.fail("NonEmptyFile.txt not created before running tests.");
        }

        if (nonEmptyFileWithBlankLines.createNewFile() == false) {
            Assert.fail("NonEmptyFileWithBlankLines.txt not created before running tests.");
        }

        FileWriter fileWriter = new FileWriter(nonEmptyFile);
        fileWriter.write("NameA\n");
        fileWriter.write("NameB\n");
        fileWriter.write("NameC");
        fileWriter.flush();
        fileWriter.close();

        fileWriter = new FileWriter(nonEmptyFileWithBlankLines);
        fileWriter.write("NameA\n");
        fileWriter.write("NameB\n");
        fileWriter.write("\n");
        fileWriter.write("NameC");
        fileWriter.flush();
        fileWriter.close();
    }

    @AfterClass
    public static void deleteTestFiles() {
        final File emptyFile = new File("EmptyFile.txt");
        final File nonEmptyFile = new File("NonEmptyFile.txt");
        final File nonEmptyFileWithBlankLines = new File("NonEmptyFileWithBlankLines.txt");

        if (emptyFile.exists()) {
            if (emptyFile.delete() == false) {
                Assert.fail("EmptyFile.txt not deleted after running tests.");
            }
        }

        if (nonEmptyFile.exists()) {
            if (nonEmptyFile.delete() == false) {
                Assert.fail("NonEmptyFile.txt not deleted after running tests.");
            }
        }

        if (nonEmptyFileWithBlankLines.exists()) {
            if (nonEmptyFileWithBlankLines.delete() == false) {
                Assert.fail("NonEmptyFileWithBlankLines.txt not deleted after running tests.");
            }
        }
    }

    @Test (expected = NullPointerException.class)
    public void testLoadLines_withNullFilePath() throws IOException {
        NameGenerator.loadLines(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testLoadLines_withEmptyFilePath() throws IOException {
        NameGenerator.loadLines("");
    }

    @Test
    public void testLoadLines_withEmptyFile() throws IOException {
        final String[] lines = NameGenerator.loadLines("EmptyFile.txt");
        Assert.assertEquals(0, lines.length);
    }

    @Test
    public void testLoadLines_withNonEmptyFile() throws IOException {
        final String[] lines = NameGenerator.loadLines("NonEmptyFile.txt");
        Assert.assertEquals(3, lines.length);
        Assert.assertEquals("NameA", lines[0]);
        Assert.assertEquals("NameB", lines[1]);
        Assert.assertEquals("NameC", lines[2]);
    }

    @Test
    public void testLoadLines_withNonEmptyFileWithBlankLines() throws IOException {
        final String[] lines = NameGenerator.loadLines("NonEmptyFileWithBlankLines.txt");
        Assert.assertEquals(3, lines.length);
        Assert.assertEquals("NameA", lines[0]);
        Assert.assertEquals("NameB", lines[1]);
        Assert.assertEquals("NameC", lines[2]);
    }
}
