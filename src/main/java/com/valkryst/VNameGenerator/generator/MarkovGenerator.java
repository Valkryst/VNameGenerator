package com.valkryst.VNameGenerator.generator;

import com.valkryst.VNameGenerator.markov.MarkovChain;
import lombok.NonNull;

import java.util.concurrent.ThreadLocalRandom;

public final class MarkovGenerator extends NameGenerator {
    private MarkovChain markovChain = new MarkovChain();

    /**
     * Constructs a MarkovGenerator and trains it with a set of names.
     *
     * @param names A set of names.
     */
    public MarkovGenerator(final @NonNull String[] names) {
    	setTrainingNames(names);
    }

    @Override
    public String generate(int maxLength) {
    	super.validateMaxLengthValid(maxLength);
		maxLength = ThreadLocalRandom.current().nextInt((int) (maxLength * 0.5), maxLength + 1);

        final var stringBuilder = new StringBuilder();

		// Begin the name with a random pair of two characters.
        stringBuilder.append(markovChain.chooseRandomString());

        for (int i = 2 ; i < maxLength ; i++) {
            final var next = markovChain.chooseRandomCharacter(stringBuilder.substring(i - 2, i));

            if (next.isPresent()) {
                stringBuilder.append(next.get());
            } else {
            	break;
			}
        }

		while (stringBuilder.length() > maxLength) {
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}

        return stringBuilder.substring(0, 1).toUpperCase() + stringBuilder.substring(1);
    }

	/**
	 * Erase the existing Markov Chain and retrain it with a new set of names.
	 *
	 * @param names A set of names.
	 */
	public void setTrainingNames(final @NonNull String[] names) {
    	if (names.length == 0) {
    		throw new IllegalArgumentException("The array of training names must have at least one element. It is currently empty.");
		}

		super.lowercaseAllElements(names);

    	markovChain = new MarkovChain();
    	markovChain.train(names);
	}
}
