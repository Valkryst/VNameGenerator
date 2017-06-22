package com.valkryst.builder;

import com.valkryst.NameGeneratorBuilder;
import com.valkryst.generator.MarkovNameGenerator;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public final class MarkovBuilder extends NameGeneratorBuilder {
    /** The names to train the Markov Chain with. */
    @Getter private List<String> trainingNames;

    /**
     * Constructs a new MarkovNameGenerator.
     *
     * @return
     *         A new MarkovNameGenerator.
     *
     * @throws IllegalStateException
     *          If something is wrong with the builder's state.
     */
    public MarkovNameGenerator build() throws IllegalStateException {
        checkState();
        return new MarkovNameGenerator(this);
    }

    /**
     * Checks the current state of the builder for issues.
     *
     * @throws IllegalStateException
     *          If something is wrong with the builder's state.
     */
    private void checkState() throws IllegalStateException {
        // Ensure list isn't null:
        if (trainingNames == null) {
            throw new IllegalStateException("The list of training names is null.");
        }

        // Ensure list isn't empty:
        if (trainingNames.size() == 0) {
            throw new IllegalStateException("The list of training names is empty.");
        }
    }

    /**
     * Reads each line of the specified file into the list of training names.
     *
     * @param path
     *         The path to the file of training names.
     *
     * @throws IOException
     *          If the specified file doesn't exist.
     *          If an I/O error occurs.
     */
    public void setTrainingNames(final Path path) throws IOException {
        trainingNames = super.readLines(path);
    }
}

