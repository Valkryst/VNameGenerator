package com.valkryst.VNameGenerator.generator;

import com.valkryst.VNameGenerator.markov.MarkovChain;

import java.util.Optional;

public final class MarkovGenerator extends NameGenerator {
    /** The chain to use when generating names. */
    private final MarkovChain markovChain = new MarkovChain();

    /**
     * Constructs a MarkovGenerator.
     *
     * @param trainingNames
     *          The names to train the Markov Chain with.
     *
     * @throws NullPointerException
     *          If the list of training names is null.
     *
     * @throws IllegalArgumentException
     *          If the list of training names is empty.
     */
    public MarkovGenerator(final String[] trainingNames) {
        if (trainingNames == null) {
            throw new NullPointerException("The array of training names is null.");
        }

        if (trainingNames.length == 0) {
            throw new IllegalArgumentException("The array of training names is empty.");
        }

        markovChain.train(trainingNames);
    }

    @Override
    public String generateName(int length) {
        if (length < 2) {
            length = 2;
        }

        final StringBuilder sb = new StringBuilder();
        sb.append(markovChain.chooseRandomString()); // Begin the name with a random pair of two characters.

        for (int i = 2; i < length; ++i) {
            final Optional<Character> next = markovChain.chooseRandomCharacter(sb.substring(i - 2, i));

            if (next.isPresent()) {
                sb.append(next.get());
            } else {
                if (sb.length() != length) {
                    return generateName(length);
                } else {
                    break;
                }
            }
        }

        if (sb.length() > length) {
            sb.deleteCharAt(sb.length() - (sb.length() - length));
        }

        return capitalizeFirstCharacter(sb.toString());
    }
}
