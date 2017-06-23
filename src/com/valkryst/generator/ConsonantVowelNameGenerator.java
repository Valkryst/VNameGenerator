package com.valkryst.generator;

import com.valkryst.builder.ConsonantVowelBuilder;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class ConsonantVowelNameGenerator implements NameGenerator {
    /** The consonants. */
    private final String[] consonants;
    /** The vowels. */
    private final String[] vowels;

    /**
     * Constructs a ConsonantVowelNameGenerator.
     *
     * @param builder
     *         The builder.
     */
    public ConsonantVowelNameGenerator(final ConsonantVowelBuilder builder) {
        final int totalConsonants = builder.getConsonants().size();
        final int totalVowels = builder.getConsonants().size();

        consonants = builder.getConsonants().toArray(new String[totalConsonants]);
        vowels = builder.getConsonants().toArray(new String[totalVowels]);
    }

    @Override
    public String generateName(int length) {
        if (length == 0) {
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
