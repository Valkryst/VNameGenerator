package com.valkryst.generator;

import com.valkryst.builder.ConsonantVowelBuilder;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class ConsonantVowelNameGenerator implements NameGenerator {
    /** The consonants. */
    private final List<String> consonants;
    /** The vowels. */
    private final List<String> vowels;

    /**
     * Constructs a ConsonantVowelNameGenerator.
     *
     * @param builder
     *         The builder.
     */
    public ConsonantVowelNameGenerator(final ConsonantVowelBuilder builder) {
        consonants = builder.getConsonants();
        vowels = builder.getVowels();
    }

    @Override
    public String generateName(int length) {
        if (length == 0) {
            length = 2;
        }

        final StringBuilder sb = new StringBuilder();

        while (sb.length() < length) {
            if (length % 2 == 0) {
                sb.append(vowels.get(ThreadLocalRandom.current().nextInt(vowels.size())));
            } else {
                sb.append(vowels.get(ThreadLocalRandom.current().nextInt(consonants.size())));
            }
        }

        return sb.substring(0, length);
    }
}
