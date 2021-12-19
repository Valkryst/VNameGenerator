package com.valkryst.VNameGenerator.generator;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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

	private final class Entry {
		/** The characters.*/
		private char[] characters;
		/** The threshold value of each character. */
		private int[] threshold;

		public Entry(final @NonNull Map<Character, Integer> occurrences) {
			characters = new char[occurrences.size()];
			threshold = new int[occurrences.size()];

			int counter = 0;
			for (final Map.Entry<Character, Integer> entry : occurrences.entrySet()) {
				characters[counter] = entry.getKey();
				threshold[counter] = entry.getValue() + (counter > 0 ? threshold[counter - 1] : 0);

				counter++;
			}
		}

		public Optional<Character> chooseCharacter() {
			final int rand = ThreadLocalRandom.current().nextInt(threshold[threshold.length - 1] + 1);

			for (int i = 0 ; i < threshold.length ; i++) {
				if (threshold[i] >= rand) {
					return Optional.of(characters[i]);
				}
			}

			return Optional.empty();
		}
	}

	private final class MarkovChain {
		private final Map<String, Entry> entries = new HashMap<>();

		/**
		 * Trains the Markov Chain using a set of strings.
		 *
		 * @param strings
		 *          The training strings.
		 */
		public void train(final String[] strings) {
			final Map<String, Map<Character, Integer>> builders = new HashMap<>();

			for (final String string : strings) {
				if (string.length() < 2) {
					return;
				}

				for (int i = 2 ; i < string.length() ; i++) {
					final String charGroup = string.substring(i - 2, i);
					final char subsequentChar = string.charAt(i);

					builders.putIfAbsent(charGroup, new HashMap<>());

					builders.get(charGroup).putIfAbsent(subsequentChar, 0);
					builders.get(charGroup).put(string.charAt(i), builders.get(charGroup).get(subsequentChar) + 1);
				}
			}

			for (final Map.Entry<String, Map<Character, Integer>> entry : builders.entrySet()) {
				entries.put(entry.getKey(), new Entry(entry.getValue()));
			}
		}

		/**
		 * Chooses a random character to follow a string.
		 *
		 * @param string
		 *          The string.
		 *
		 * @return
		 *          The character.
		 */
		public Optional<Character> chooseRandomCharacter(final String string) {
			if (string == null || string.isEmpty()) {
				return Optional.empty();
			}

			if (entries.containsKey(string)) {
				return entries.get(string).chooseCharacter();
			} else {
				return Optional.empty();
			}
		}

		/**
		 * Chooses a random string from the set of valid strings.
		 *
		 * @return
		 *          The string.
		 */
		public String chooseRandomString() {
			final int index = ThreadLocalRandom.current().nextInt(entries.size());
			return (String) entries.keySet().toArray()[index];
		}
	}
}
