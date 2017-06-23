package com.valkryst.builder;

import com.valkryst.generator.CombinatorialNameGenerator;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class CombinatorialBuilder extends NameGeneratorBuilder {
    /** The List containing all loaded name-beginnings. */
    @Getter private List<String> beginnings;
    /** The List containing all loaded name-middles. */
    @Getter private List<String> middles;
    /** The List containing all loaded name-endings. */
    @Getter private List<String> endings;

    /**
     * Uses the builder to construct a new CombinatorialNameGenerator.
     *
     * @return
     *         A new CombinatorialNameGenerator.
     *
     * @throws IllegalStateException
     *          If something is wrong with the builder's state.
     */
    public CombinatorialNameGenerator build() throws IllegalStateException {
        return new CombinatorialNameGenerator(this);
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
        if (beginnings == null) {
            throw new IllegalStateException("The list of beginnings is null.");
        }

        if (middles == null) {
            middles = new ArrayList<>(0);
        }

        if (endings == null) {
            throw new IllegalStateException("The list of endings is null.");
        }

        // Ensure lists aren't empty:
        if (beginnings.size() == 0) {
            throw new IllegalStateException("The list of beginnings is empty.");
        }

        if (endings.size() == 0) {
            throw new IllegalStateException("The list of endings is empty.");
        }
    }

    /**
     * Reads each line of the specified file into the list of name-beginnings.
     *
     * @param path
     *         The path to the file of name-beginnings.
     *
     * @throws IOException
     *          If the specified file doesn't exist.
     *          If an I/O error occurs.
     */
    public void setBeginnings(final Path path) throws IOException {
        beginnings = readLines(path);
    }

    /**
     * Reads each line of the specified file into the list of name-middles.
     *
     * @param path
     *         The path to the file of name-middles.
     *
     * @throws IOException
     *          If the specified file doesn't exist.
     *          If an I/O error occurs.
     */
    public void setMiddles(final Path path) throws IOException {
        middles = readLines(path);
    }

    /**
     * Reads each line of the specified file into the list of name-endings.
     *
     * @param path
     *         The path to the file of name-endings.
     *
     * @throws IOException
     *          If the specified file doesn't exist.
     *          If an I/O error occurs.
     */
    public void setEndings(final Path path) throws IOException {
        endings = readLines(path);
    }
}
