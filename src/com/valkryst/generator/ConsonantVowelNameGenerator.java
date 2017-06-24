package com.valkryst.generator;

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
     */
    public ConsonantVowelNameGenerator(final List<String> consonants, final List<String> vowels) {
        // Ensure lists aren't null:
        if (consonants == null) {
            throw new IllegalArgumentException("The list of consonants is null.");
        }

        if (vowels == null) {
            throw new IllegalArgumentException("The list of vowels is null.");
        }

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

        while (sb.length() < length) {
            if (length % 2 == 0) {
                sb.append(vowels[ThreadLocalRandom.current().nextInt(vowels.length)]);
            } else {
                sb.append(consonants[ThreadLocalRandom.current().nextInt(consonants.length)]);
            }
        }

        return sb.substring(0, length);
    }
}
