package com.valkryst.generator;

import com.valkryst.NameGenerator;
import com.valkryst.builder.ConsonantVowelBuilder;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntUnaryOperator;

public final class ConsonantVowelNameGenerator implements NameGenerator {
    /** The array containing all loaded consonants. */
    private final List<String> consonants;
    /** The array containing all loaded vowels. */
    private final List<String> vowels;

    /**
     * Constructs a new consonant-vowel name generator from the specified builder.
     *
     * @param builder
     *         The builder to retrieve the consonants and vowels from.
     */
    public ConsonantVowelNameGenerator(final ConsonantVowelBuilder builder) {
        consonants = builder.getConsonants();
        vowels = builder.getVowels();
    }

    @Override
    public String generateName(final int length) {
        if (length == 0) {
            return "LENGTH_WAS_ZERO";
        }

        List<String> data = vowels;
        final StringBuilder sb = new StringBuilder();

        while (sb.length() < length) {
            final int randomIndex = ThreadLocalRandom.current().nextInt(data.size());
            sb.append(data.get(randomIndex));

            if (length % 2 == 0) {
                data = vowels;
            } else {
                data = consonants;
            }
        }

        return sb.substring(0, length);
    }
}
