package com.valkryst.generator;

import lombok.NonNull;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class ConsonantVowelGenerator extends NameGenerator {
    /** The consonants. */
    private final String[] consonants;
    /** The vowels. */
    private final String[] vowels;

    /**
     * Constructs a new ConsonantVowelGenerator.
     *
     * @param consonants
     *         The consonants.
     *
     * @param vowels
     *         The vowels.
     * @throws IllegalArgumentException
     *        If the lists of consonants or vowels are empty.
     */
    public ConsonantVowelGenerator(final List<String> consonants, final List<String> vowels) {
        // Ensure lists aren't empty:
        if (consonants.size() == 0) {
            throw new IllegalArgumentException("The list of consonants is empty or null.");
        }

        if (vowels.size() == 0) {
            throw new IllegalArgumentException("The list of vowels is empty or null.");
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

        if (sb.length() > length) {
            sb.deleteCharAt(sb.length() - (sb.length() - length));
        }

        return super.capitalizeFirstCharacter(sb.toString());
    }
}
