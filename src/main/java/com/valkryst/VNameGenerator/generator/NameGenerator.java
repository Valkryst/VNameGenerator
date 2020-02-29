package com.valkryst.VNameGenerator.generator;

import lombok.NonNull;

import java.io.*;

public abstract class NameGenerator {
    /**
     * Generates a name.
     *
     * The resulting name is not guaranteed to be of the specified length.
     *
     * @param length
     *        The desired name length.
     *
     * @return
     *        The name.
     */
    abstract String generateName(final int length);

    /**
     * Capitalizes the first character of a string.
     *
     * @param string
     *          The string.
     *
     * @return
     *          The string with the first letter capitalized.
     */
    public static String capitalizeFirstCharacter(final String string) {
        if (string == null) {
            return "";
        }

        if (string.isEmpty()) {
            return string;
        }

        final char tmp[] = string.toCharArray();
        tmp[0] = Character.toUpperCase(tmp[0]);
        return new String(tmp);
    }

    /**
     * Loads the lines of a file from within the Jar.
     *
     * @param filePath
     *          The path to the file.
     *
     * @return
     *          The lines.
     *
     * @throws NullPointerException
     *          If the file path is null.
     *
     * @throws IllegalArgumentException
     *          If the file is empty.
     *
     * @throws IOException
     *          If an I/O error occurs while counting the lines.
     */
    public static String[] loadLinesFromJar(final @NonNull String filePath) throws IOException {
        if (filePath.isEmpty()) {
            throw new IllegalArgumentException("The file path cannot be empty.");
        }

        final ClassLoader classLoader = NameGenerator.class.getClassLoader();
        return loadLines(classLoader.getResourceAsStream(filePath), countFileLines(classLoader.getResourceAsStream(filePath)));
    }

    /**
     * Loads the lines of a file from the file system.
     *
     * @param filePath
     *          The path to the file.
     *
     * @return
     *          The lines.
     *
     * @throws NullPointerException
     *          If the file path is null.
     *
     * @throws IllegalArgumentException
     *          If the file is empty.
     *
     * @throws IOException
     *          If an I/O error occurs while counting the lines.
     */
    public static String[] loadLines(final @NonNull String filePath) throws IOException {
        if (filePath.isEmpty()) {
            throw new IllegalArgumentException("The file path cannot be empty.");
        }

        return loadLines(new FileInputStream(filePath), countFileLines(new FileInputStream(filePath)));
    }

    /**
     * Loads the lines of a file, then closes the given input stream when the operation is complete.
     *
     * Blank lines are ignored.
     *
     * @param fileInputStream
     *          The input stream of the file.
     *
     * @param fileLines
     *          The total number of lines in the file.
     *
     * @return
     *          The lines.
     *
     * @throws IOException
     *          If an I/O error occurs while loading the lines.
     */
    private static String[] loadLines(final InputStream fileInputStream, final int fileLines) throws IOException {
        if (fileInputStream == null) {
            return new String[0];
        }

        final InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        final String[] lines = new String[fileLines];
        String currentLine;
        int lineCounter = 0;

        while ((currentLine = bufferedReader.readLine()) != null && lineCounter < fileLines) {
            if (currentLine.isEmpty() == false) {
                lines[lineCounter] = currentLine;
                lineCounter++;
            }
        }

        bufferedReader.close();
        inputStreamReader.close();

        return lines;
    }

    /**
     * Counts the number of lines in a file, then closes the given input stream when the operation is complete.
     *
     * Blank lines are ignored.
     *
     * @param fileInputStream
     *          The input stream of the file.
     *
     * @return
     *          The number of lines.
     *
     * @throws IOException
     *          If an I/O error occurs while counting the lines.
     */
    private static int countFileLines(final InputStream fileInputStream) throws IOException {
        if (fileInputStream == null) {
            return 0;
        }

        final InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String currentLine;
        int fileLines = 0;

        while ((currentLine = bufferedReader.readLine()) != null) {
            fileLines += (currentLine.isEmpty() ? 0 : 1);
        }

        bufferedReader.close();
        inputStreamReader.close();

        return fileLines;
    }
}
