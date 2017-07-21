package com.valkryst.generator;

import java.util.List;
import java.util.Objects;
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
    public ConsonantVowelNameGenerator(final List<String> consonants, final List<String> vowels) {
        Objects.requireNonNull(consonants);
        Objects.requireNonNull(vowels);

        // Ensure lists aren't empty:
        if (consonants.size() == 0) {
            throw new IllegalArgumentException("The list of consonants is empty.");
        }

        if (vowels.size() == 0) {
            throw new IllegalArgumentException("The list of vowels is empty.");
        }

        this.consonants = consonants.toArray(new String[consonants.size()]);
        this.vowels = vowels.toArray(new String[vowels.size()]);
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

        return sb.substring(0, length);
    }
}
