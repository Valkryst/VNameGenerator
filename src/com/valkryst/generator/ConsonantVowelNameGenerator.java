package com.valkryst.generator;

import com.valkryst.NameGenerator;
import com.valkryst.builder.ConsonantVowelNameGeneratorBuilder;

import java.util.function.IntUnaryOperator;

public final class ConsonantVowelNameGenerator implements NameGenerator {
    /** The array containing all loaded consonants. */
    private final String[] consonants;
    /** The array containing all loaded vowels. */
    private final String[] vowels;

    /**
     * Constructs a new consonant-vowel name
     * generator from the specified builder.
     *
     * @param builder
     *         The builder to retrieve the
     *         consonants and vowels from.
     */
    public ConsonantVowelNameGenerator(final ConsonantVowelNameGeneratorBuilder builder) {
        final int totalConsonants = builder.getConsonants().size();
        final int totalVowels = builder.getVowels().size();

        consonants = builder.getConsonants().toArray(new String[totalConsonants]);
        vowels = builder.getVowels().toArray(new String[totalVowels]);
    }

    /**
     * Generates a name of at-least the specified length
     * as a string of randomized consonants and vowels.
     *
     * The string begins with a consonant, then a vowel,
     * then a consonant, then a vowel, etc... until the
     * specified length is reached or exceeded.
     *
     * @param randomInRange
     *         A function that returns an arbitrary
     *         number in the range [0, param)
     *
     * @param length
     *         The length of the name to generateName.
     *
     * @return
     *         The generated name.
     */
    @Override
    public String generateName(final IntUnaryOperator randomInRange, final int length) {
        if (length == 0) {
            return "LENGTH_WAS_ZERO";
        }

        boolean addConsonant = false;
        final StringBuilder sb = new StringBuilder();

        while (sb.length() < length) {
            if (addConsonant) {
                final int randomIndex = randomInRange.applyAsInt(consonants.length);
                sb.append(consonants[randomIndex]);
            } else {
                final int randomIndex = randomInRange.applyAsInt(vowels.length);
                sb.append(vowels[randomIndex]);
            }

            addConsonant = !addConsonant;
        }

        return sb.substring(0, length);
    }
}
