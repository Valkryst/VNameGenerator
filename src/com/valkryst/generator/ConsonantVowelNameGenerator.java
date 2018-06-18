package com.valkryst.generator;

import lombok.NonNull;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class ConsonantVowelNameGenerator implements NameGenerator {
    /** The consonants. */
    private final String[] consonants;
    /** The vowels. */
    private final String[] vowels;

    /**
     * Constructs a new ConsonantVowelNameGenerator.
     *
     * @param consonants
     *         The consonants.
     *
     * @param vowels
     *         The vowels.
     *
     * @throws NullPointerException
     *        If the lists of consonants or vowels are null.
     *
     * @throws IllegalArgumentException
     *        If the lists of consonants or vowels are empty.
     */
    public ConsonantVowelNameGenerator(final @NonNull List<String> consonants, final @NonNull List<String> vowels) {
        // Ensure lists aren't empty:
        if (consonants.size() == 0) {
            throw new IllegalArgumentException("The list of consonants is empty.");
        }

        if (vowels.size() == 0) {
            throw new IllegalArgumentException("The list of vowels is empty.");
        }

        this.consonants = consonants.toArray(new String[0]);
        this.vowels = vowels.toArray(new String[0]);
    }

    @Override
    public String generateName(int length) {
        if (length < 2) {
            length = 2;
        }

        final StringBuilder sb = new StringBuilder();
        final ThreadLocalRandom rand = ThreadLocalRandom.current();

        while (sb.length() < length) {
            if (length % 2 == 0) {
                sb.append(vowels[rand.nextInt(vowels.length)]);
            } else {
                sb.append(consonants[rand.nextInt(consonants.length)]);
            }
        }

        final String name = sb.substring(0, length);
        // Capitalize the first letter of the name:
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
