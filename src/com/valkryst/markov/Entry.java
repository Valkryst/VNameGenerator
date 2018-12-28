package com.valkryst.markov;

import lombok.NonNull;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

class Entry {
    /** The characters.*/
    private char[] characters;
    /** The threshold value of each character. */
    private int[] threshold;

    public Entry(final @NonNull EntryBuilder builder) {
        final Map<Character, Integer> occurrences = builder.getOccurrences();
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
