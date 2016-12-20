package com.valkryst.generator;

import com.valkryst.NameGenerator;
import com.valkryst.builder.MarkovBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.IntUnaryOperator;

public final class MarkovNameGenerator implements NameGenerator {
    /** The List containing all two-character combinations found in the training names. */
    private final List<String> sequences = new ArrayList<>();

    // todo JavaDoc
    private final MarkovChain<String, String> markovChain = new MarkovChain<>();

    /**
     * Constructs a new MarkovNameGenerator with the specified builder.
     *
     * @param builder
     *         The builder to retrieve the training names from.
     */
    public MarkovNameGenerator(final MarkovBuilder builder) {
        builder.getTrainingNames()
               .forEach(this::acquireProbabilities);
    }

    /**
     * Generates a name through the use of a trained Markov Chain.
     *
     * @param randomInRange
     *         A function that returns an arbitrary number in the range of [0, param)
     *
     * @param length
     *         The length of the name to generateName.
     *
     *         If the value is less than or equal to zero, then this parameter is ignored.
     *
     *         No guarantee is made that the name will be exactly this length.
     *
     * @return
     *         The generated name.
     */
    @Override
    public String generateName(final IntUnaryOperator randomInRange, final int length) {
        if (length == 0) {
            return "LENGTH_WAS_ZERO";
        }

        final StringBuilder sb = new StringBuilder();

        // Choose a random preSequence to begin with:
        String preSequence = sequences.get(randomInRange.applyAsInt(sequences.size()));
        sb.append(preSequence);

        String previous = preSequence.substring(0, 1);
        String current = preSequence.substring(1, 2);

        for (int i = 2; i < length; ++i) {
            preSequence  = previous + "" + current;

            try {
                final String next = chooseNextCharacter(randomInRange, preSequence);
                sb.append(next);
                previous = current;
                current = next;
            } catch (final NoSuchElementException e) {
                break;
            }

        }

        return formatName(sb.toString());
    }

    /**
     * Parses the specified String to determine the probability of a character appearing after the previous two
     * characters beginning with the third character in the String and ending with the last.
     *
     * @param trainingString
     *         The string to parse.
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
     * Determines the character to follow the specified sequence using the precomputed probabilities of which characters
     * most often appear after the specified sequence.
     *
     * @param randomInRange
     *         A function that returns an arbitrary number in the range of [0, param)
     *
     * @param preSequence
     *         The sequence to find the next character for.
     *
     * @return
     *         The next character of the sequence.
     *
     * @throws NoSuchElementException
     *          If the specified sequence has no corresponding probabilities to determine the next character from.
     */
    private String chooseNextCharacter(final IntUnaryOperator randomInRange, final String preSequence) throws NoSuchElementException {
        final ArrayList<String> sequences = markovChain.getAllSequences(preSequence);

        if (sequences.size() == 0) {
            throw new NoSuchElementException("There are no computed probabilities for the specified key.");
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

        final int randomIndex = randomInRange.applyAsInt(highestStrings.size());
        return highestStrings.get(randomIndex);
    }

    /**
     * Sets the first character of the specified name to be upper case.
     *
     * @param name
     *         The name to format.
     *
     * @return
     *         The formatted name.
     */
    private String formatName(final String name) {
        final char firstCharacter = Character.toUpperCase(name.charAt(0));
        final String remainingCharacters = name.substring(1);
        return firstCharacter + remainingCharacters;
    }
}
