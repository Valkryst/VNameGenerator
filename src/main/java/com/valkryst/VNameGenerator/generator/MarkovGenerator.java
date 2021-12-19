package com.valkryst.VNameGenerator.generator;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public final class MarkovGenerator extends NameGenerator {
	private final Map<String, Entry> entries = new HashMap<>();

    /**
     * Constructs a MarkovGenerator and trains it with a set of names.
     *
     * @param names A set of names.
     */
    public MarkovGenerator(final @NonNull String[] names) {
		if (names.length == 0) {
			throw new IllegalArgumentException("The array of training names must have at least one element. It is currently empty.");
		}

		final Map<String, Map<Character, Integer>> builders = new HashMap<>();

		for (String string : names) {
			if (string.length() < 2) {
				return;
			}

			string = string.toLowerCase(Locale.ROOT);

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

    @Override
    public String generate(int maxLength) {
    	super.validateMaxLengthValid(maxLength);

		/*
		 * Even a small Markov Chain is capable of generating long names, but
		 * the names can be of a subjectively poor quality due to repetition
		 * and length.
		 *
		 * e.g. Poor Quality Names
		 *
		 * 		Rlasaìdhagaidhairidh
		 * 		Itirigiosaidhagsaìde
		 * 		Nsaireabalaileall
		 * 		Nanagaililiormailili
		 * 		Unagaghreagagaghnait
		 *
		 * To generate names that are of a subjectively higher quality, we
		 * randomize maxLength. This partially solves the issues of repetition
		 * and length.
		 *
		 * We could allow maxLength randomization by users, but we want this
		 * function to produce subjectively good results without needing to
		 * know much about its internals.
		 */
		final var random = ThreadLocalRandom.current();
		maxLength = random.nextInt((int) (maxLength * 0.5), maxLength + 1);

		final var sb = new StringBuilder();
		sb.append((String) entries.keySet().toArray()[random.nextInt(entries.size())]);

		for (int i = 2 ; i < maxLength ; i++) {
            final var substring = sb.substring(i - 2, i);

			final var entry = entries.get(substring);
			if (entry == null) {
				break;
			}

			sb.appendCodePoint(entry.randomCodePoint());
        }

		// Prevent names from begin/ending with non-alphabetic characters.
		int codePoint = sb.codePointAt(0);
		if (!Character.isAlphabetic(codePoint)) {
			sb.deleteCharAt(0);
		}

		codePoint = sb.codePointAt(sb.length() - 1);
		if (!Character.isAlphabetic(codePoint)) {
			sb.deleteCharAt(sb.length() - 1);
		}

        return super.capitalize(sb);
    }

	private static final class Entry {
		private final int[] codePoints;
		private final float[] sums;

		public Entry(final @NonNull Map<Character, Integer> occurrences) {
			codePoints = new int[occurrences.size()];
			sums = new float[occurrences.size()];

			int index = 0;
			float cumulativeSum = 0;

			for (final var entry : occurrences.entrySet()) {
				codePoints[index] = entry.getKey();

				cumulativeSum += entry.getValue();
				sums[index] = cumulativeSum;

				index++;
			}
		}

		public int randomCodePoint() {
			/*
			 * This algorithm was inspired by the following StackExchange
			 * answer:
			 *
			 * https://softwareengineering.stackexchange.com/a/150618
			 */
			final var randomSum = ThreadLocalRandom.current().nextFloat(sums[sums.length - 1] + 0.01f);

			for (int i = 0 ; i < sums.length ; i++) {
				if (randomSum <= sums[i]) {
					return codePoints[i];
				}
			}

			return 0;
		}
	}
}
