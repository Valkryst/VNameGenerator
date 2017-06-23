package com.valkryst.markov;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class Entry {
    private char[] characters;
    private int[] threshold;

    public Entry(final EntryBuilder builder) {
        final Map<Character, Integer> occurrences = builder.getOccurrences();

        characters = new char[occurrences.size()];
        int[] frequency = new int[occurrences.size()];
        threshold = new int[occurrences.size()];

        int counter = 0;
        for (final Map.Entry<Character, Integer> entry : occurrences.entrySet()) {
            characters[counter] = entry.getKey();
            frequency[counter] = entry.getValue();

            if (counter > 0) {
                threshold[counter] = frequency[counter] + threshold[counter - 1];
            } else {
                threshold[counter] = frequency[counter];
            }

            counter++;
        }
    }

    public Optional<Character> chooseCharacter() {
        final int max = threshold[threshold.length - 1];
        final int rand = ThreadLocalRandom.current().nextInt(max + 1);

        for (int i = 0 ; i < threshold.length ; i++) {
            if (threshold[i] >= rand) {
                return Optional.of(characters[i]);
            }
        }

        return Optional.empty();
    }
}
