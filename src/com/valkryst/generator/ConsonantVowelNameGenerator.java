package com.valkryst.generator;

import com.valkryst.builder.ConsonantVowelNameGeneratorBuilder;

import java.util.Random;

public class ConsonantVowelNameGenerator {
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
     * The string begins with a consonants, then a vowel,
     * then a consonant, then a vowel, etc... until the
     * specified length is reached or exceeded.
     *
     * @param random
     *         The instance of Random to use when
     *         necessary.
     *
     * @param length
     *         The length of the name to generate.
     *
     * @return
     *         The generated name.
     */
    public String generateName(final Random random, final int length) {
        if(length == 0) {
            return "LENGTH_WAS_ZERO";
        }

        boolean addConsonant = false;
        final StringBuilder sb = new StringBuilder();

        while(sb.length() < length) {
            if(addConsonant) {
                int randomIndex = random.nextInt(consonants.length);
                sb.append(consonants[randomIndex]);
            } else {
                int randomIndex = random.nextInt(vowels.length);
                sb.append(vowels[randomIndex]);
            }

            addConsonant = !addConsonant;
        }

        return sb.substring(0, length);
    }
}
