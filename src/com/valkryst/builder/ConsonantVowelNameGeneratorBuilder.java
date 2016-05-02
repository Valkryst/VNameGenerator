package com.valkryst.builder;

import com.valkryst.generator.ConsonantVowelNameGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ConsonantVowelNameGeneratorBuilder {
    /** The array containing all loaded consonants. */
    private List<String> consonants = new ArrayList<>();
    /** The array containing all loaded vowels. */
    private List<String> vowels = new ArrayList<>();

    /**
     * Uses the builder to construct a new ConsonantVowelNameGenerator.
     *
     * @return
     *         A new ConsonantVowelNameGenerator.
     *
     * @throws IllegalStateException
     *          If something is wrong with the builder's
     *          state.
     */
    public ConsonantVowelNameGenerator build() throws IllegalStateException {
        checkState();
        return new ConsonantVowelNameGenerator(this);
    }

    /**
     * Checks the current state of the builder for any
     * issue with it's state.
     *
     * If no issue is found, then no exception is thrown.
     *
     * @throws IllegalStateException
     *          If something is wrong with the builder's
     *          state.
     */
    private void checkState() throws IllegalStateException {
        // Ensure lists aren't null:
        if(consonants == null) {
            throw new IllegalStateException("The list of consonants is null.");
        }

        if(vowels == null) {
            throw new IllegalStateException("The list of vowels is null.");
        }

        // Ensure lists aren't empty:
        if(consonants.size() == 0) {
            throw new IllegalStateException("The list of consonants is empty.");
        }

        if(vowels.size() == 0) {
            throw new IllegalStateException("The list of vowels is empty.");
        }
    }

    /** @return The List containing all loaded consonants. */
    public List<String> getConsonants() {
        return consonants;
    }

    /** @return The List containing all loaded vowels. */
    public List<String> getVowels() {
        return vowels;
    }

    /**
     * Reads each line of the specified file into the
     * list of consonants.
     *
     * @param path
     *         The path to the file of consonants.
     *
     * @throws IOException
     *          If the specified file doesn't exist.
     *          If an I/O error occurs.
     */
    public void setConsonants(final Path path) throws IOException {
        consonants = readLines(path);
    }

    /**
     * Sets the list of consonants to be the specified
     * list.
     *
     * @param consonants
     *         The new list of consonants.
     */
    public void setConsonants(final List<String> consonants) {
        this.consonants = consonants;
    }

    /**
     * Reads each line of the specified file into the
     * list of vowels.
     *
     * @param path
     *         The path to the file of vowels.
     *
     * @throws IOException
     *          If the specified file doesn't exist.
     *          If an I/O error occurs.
     */
    public void setVowels(final Path path) throws IOException {
        vowels = readLines(path);
    }

    /**
     * Sets the list of vowels to be the specified list.
     *
     * @param vowels
     *         The new list of vowels.
     */
    public void setVowels(final List<String> vowels) {
        this.vowels = vowels;
    }

    /**
     * Reads each line of the specified file and returns them
     * as a list. All empty lines are skipped.
     *
     * @param path
     *         The file to read from.
     *
     * @return
     *         The lines that have been read.
     *
     * @throws IOException
     *          If the specified file doesn't exist.
     *          If an I/O error occurs.
     */
    private List<String> readLines(final Path path) throws IOException {
        if(! Files.exists(path)) {
            throw new IOException("The file at \"" + path.toString() +"\" does not exist.");
        }

        // Attempt to read the lines using NIO.
        // If this fails, then fallback to
        // the BufferedReader method.
        try {
            return Files.readAllLines(path);
        } catch(final IOException e) {
            // Open Resources:
            final FileReader fileReader = new FileReader(path.toFile());
            final BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read Lines From File:
            List<String> readLines = new ArrayList<>();
            String currentLine;

            while((currentLine = bufferedReader.readLine()) != null) {
                // Don't read empty lines:
                if(! currentLine.isEmpty()) {
                    readLines.add(currentLine);
                }
            }

            // Close Resources:
            bufferedReader.close();
            fileReader.close();

            // Return Read Lines:
            return readLines;
        }
    }
}
