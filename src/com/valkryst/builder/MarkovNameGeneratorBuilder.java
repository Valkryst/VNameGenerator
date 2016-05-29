package com.valkryst.builder;

import com.valkryst.generator.MarkovNameGenerator;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MarkovNameGeneratorBuilder {
    /** The list containing all names to train the Markov Chain with. */
    @Getter private List<String> trainingNames;

    /**
     * Uses the builder to construct a new MarkovNameGenerator.
     *
     * @return
     *         A new MarkovNameGenerator.
     *
     * @throws IllegalStateException
     *          If something is wrong with the builder's
     *          state.
     */
    public MarkovNameGenerator build() throws IllegalStateException {
        checkState();
        return new MarkovNameGenerator(this);
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
        // Ensure list isn't null:
        if(trainingNames == null) {
            throw new IllegalStateException("The list of training names is null.");
        }

        // Ensure list isn't empty:
        if(trainingNames.size() == 0) {
            throw new IllegalStateException("The list of training names is empty.");
        }
    }

    /**
     * Reads each line of the specified file into the
     * list of training names.
     *
     * @param path
     *         The path to the file of training names.
     *
     * @throws IOException
     *          If the specified file doesn't exist.
     *          If an I/O error occurs.
     */
    public void setTrainingNames(final Path path) throws IOException {
        trainingNames = readLines(path);
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

