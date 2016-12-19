package com.valkryst.builder;

import com.valkryst.NameGeneratorBuilder;
import com.valkryst.generator.ConsonantVowelNameGenerator;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class ConsonantVowelNameGeneratorBuilder extends NameGeneratorBuilder {
    /** The array containing all loaded consonants. */
    @Getter private List<String> consonants = new ArrayList<>();
    /** The array containing all loaded vowels. */
    @Getter private List<String> vowels = new ArrayList<>();

    /**
     * Uses the builder to construct a new ConsonantVowelNameGenerator.
     *
     * @return
     *         A new ConsonantVowelNameGenerator.
     *
     * @throws IllegalStateException
     *          If something is wrong with the builder's state.
     */
    public ConsonantVowelNameGenerator build() throws IllegalStateException {
        checkState();
        return new ConsonantVowelNameGenerator(this);
    }

    /**
     * Checks the current state of the builder for any issue with it's state.
     *
     * If no issue is found, then no exception is thrown.
     *
     * @throws IllegalStateException
     *          If something is wrong with the builder's state.
     */
    private void checkState() throws IllegalStateException {
        // Ensure lists aren't null:
        if (consonants == null) {
            throw new IllegalStateException("The list of consonants is null.");
        }

        if (vowels == null) {
            throw new IllegalStateException("The list of vowels is null.");
        }

        // Ensure lists aren't empty:
        if (consonants.size() == 0) {
            throw new IllegalStateException("The list of consonants is empty.");
        }

        if (vowels.size() == 0) {
            throw new IllegalStateException("The list of vowels is empty.");
        }
    }

    /**
     * Reads each line of the specified file into the list of consonants.
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
     * Reads each line of the specified file into the list of vowels.
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
}
