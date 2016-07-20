package com.valkryst.builder;

import com.valkryst.generator.CombinatorialNameGenerator;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class CombinatorialNameGeneratorBuilder {
    /** The List containing all loaded name-beginnings. */
    @Getter private List<String> beginnings;
    /** The List containing all loaded name-middles. */
    @Getter private List<String> middles;
    /** The List containing all loaded name-endings. */
    @Getter private List<String> endings;

    /**
     * Uses the builder to construct a new CombinatorialNameGenerator.
     *
     * @param usesMiddles
     *         Whether or not the builder will construct
     *         a CombinatorialNameGenerator that uses
     *         name-middles or not.
     *
     * @return
     *         A new CombinatorialNameGenerator.
     *
     * @throws IllegalStateException
     *          If something is wrong with the builder's
     *          state.
     */
    public CombinatorialNameGenerator build(final boolean usesMiddles) throws IllegalStateException {
        checkState(usesMiddles);
        return new CombinatorialNameGenerator(this, usesMiddles);
    }

    /**
     * Checks the current state of the builder for any
     * issue with it's state.
     *
     * If no issue is found, then no exception is thrown.
     *
     * @param usesMiddles
     *         Whether or not the builder will construct
     *         a CombinatorialNameGenerator that uses
     *         name-middles or not.
     *
     * @throws IllegalStateException
     *          If something is wrong with the builder's
     *          state.
     */
    private void checkState(final boolean usesMiddles) throws IllegalStateException {
        // Ensure lists aren't null:
        if(beginnings == null) {
            throw new IllegalStateException("The list of beginnings is null.");
        }

        if(usesMiddles && middles == null) {
            throw new IllegalStateException("The list of middles is null.");
        }

        if(endings == null) {
            throw new IllegalStateException("The list of endings is null.");
        }

        // Ensure lists aren't empty:
        if(beginnings.size() == 0) {
            throw new IllegalStateException("The list of beginnings is empty.");
        }

        if(usesMiddles && middles.size() == 0) {
            throw new IllegalStateException("The list of middles is empty.");
        }

        if(endings.size() == 0) {
            throw new IllegalStateException("The list of endings is empty.");
        }
    }

    /**
     * Reads each line of the specified file into the
     * list of name-beginnings.
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
     * Reads each line of the specified file into the
     * list of name-middles.
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
     * Reads each line of the specified file into the
     * list of name-endings.
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
