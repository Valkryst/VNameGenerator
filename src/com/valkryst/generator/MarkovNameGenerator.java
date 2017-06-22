package com.valkryst.generator;

import com.valkryst.NameGenerator;
import com.valkryst.builder.MarkovBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntUnaryOperator;

public final class MarkovNameGenerator implements NameGenerator {
    /** All two-character combinations found in the training names. */
    private final List<String> sequences = new ArrayList<>();

    // todo JavaDoc
    private final MarkovChain<String, String> markovChain = new MarkovChain<>();

    /**
     * Constructs a MarkovNameGenerator.
     *
     * @param builder
     *         Builder containing the training names.
     */
    public MarkovNameGenerator(final MarkovBuilder builder) {
        builder.getTrainingNames().forEach(this::acquireProbabilities);
    }

    @Override
    public String generateName(final int length) {
        if (length == 0) {
            return "LENGTH_WAS_ZERO";
        }

        final StringBuilder sb = new StringBuilder();

        // Choose a random preSequence to begin with:
        String preSequence = sequences.get(ThreadLocalRandom.current().nextInt(sequences.size()));
        sb.append(preSequence);

        String previous = preSequence.substring(0, 1);
        String current = preSequence.substring(1, 2);

        for (int i = 2; i < length; ++i) {
            preSequence  = previous + "" + current;

            try {
                final String next = chooseNextCharacter(preSequence);
                sb.append(next);
                previous = current;
                current = next;
            } catch (final NoSuchElementException e) {
                break;
            }

        }

        // Capitalize the first letter of the name:
        return sb.toString().substring(0, 1).toUpperCase() + sb.toString().substring(1);
    }

    /**
     * Parses the specified string to determine the probability of a character appearing after the previous two
     * characters beginning with the third character in the string and ending with the last.
     *
     * @param trainingString
     *         A string to parse.
     */
    public void acquireProbabilities(final String trainingString) {
        if (trainingString.length() < 2) {
            return;
        }

        for (int i = 2 ; i < trainingString.length(); ++i) {
            final String preSequence = trainingString.substring(i - 2, i);
            final String sequence = trainingString.substring(i, i + 1);

            if (sequences.contains(preSequence) == false) {
                 sequences.add(preSequence);
            }

            markovChain.incrementOccurrences(preSequence, sequence);
        }
    }

    /**
     * Determines the sequence to follow the specified pre-sequence using the precomputed probabilities of which
     * sequences most often appear after the specified pre-sequence.
     *
     * @param preSequence
     *         The pre-sequence to find the next character for.
     *
     * @return
     *         The next character of the sequence.
     *
     * @throws NoSuchElementException
     *          If the specified pre-sequence has no corresponding probabilities to determine the next sequence from.
     */
    private String chooseNextCharacter(final String preSequence) throws NoSuchElementException {
        final ArrayList<String> sequences = markovChain.getAllSequences(preSequence);

        if (sequences.size() == 0) {
            throw new NoSuchElementException("There are no computed probabilities for the specified pre-sequence.");
        }

        Float highestProbability = null;
        final List<String> highestStrings = new ArrayList<>();

        for(final String sequence : sequences) {
            final Float currentProbability = markovChain.getProbability(preSequence, sequence);

            // If the initial data has not yet been set,
            // then set it.
            if (highestProbability == null) {
                highestStrings.add(sequence);
                highestProbability = currentProbability;
            } else {
                if(currentProbability > highestProbability) {
                    highestStrings.clear();
                    highestStrings.add(sequence);
                    highestProbability = currentProbability;
                } else {
                    highestStrings.add(sequence);
                }
            }
        }

        final int randomIndex = ThreadLocalRandom.current().nextInt(highestStrings.size());
        return highestStrings.get(randomIndex);
    }
}
