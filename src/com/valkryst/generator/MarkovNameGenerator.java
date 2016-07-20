package com.valkryst.generator;

import com.valkryst.NameGenerator;
import com.valkryst.builder.MarkovNameGeneratorBuilder;

import java.util.*;
import java.util.function.IntUnaryOperator;

public final class MarkovNameGenerator implements NameGenerator {
    /**
     * The List containing all two-character combinations found
     * in the training names.
     */
    private final List<String> sequences = new ArrayList<>();
    /**
     * The HashMap containing the probability of any character
     * to follow any two previous characters.
     *
     * Ex:
     *      aa, <a, 1>
     *      aa, <b, 2>
     *      aa, <c, 3>
     *
     *      With the three entries above, we can see that the
     *      character 'c' is most-likely to follow the characters
     *      "aa" with 'b' being the second most-likely, and 'a'
     *      being the least likely.
     */
    private final HashMap<String, HashMap<Character, Integer>> sequenceProbabilities = new HashMap<>();

    /**
     * Constructs a new MarkovNameGenerator with the
     * specified builder.
     *
     * @param builder
     *         The builder to retrieve the training names from.
     */
    public MarkovNameGenerator(final MarkovNameGeneratorBuilder builder) {
        builder.getTrainingNames()
               .forEach(this::acquireProbabilities);
    }

    /**
     * Generates a name through the use of a trained
     * Markov Chain.
     *
     * @param randomInRange
     *         A function that returns an arbitrary
     *         number in the range of [0, param)
     *
     * @param length
     *         The length of the name to generateName.
     *
     *         If the value is less than or equal to
     *         zero, then this parameter is ignored.
     *
     *         No guarantee is made that the name
     *         will be exactly this length.
     *
     * @return
     *         The generated name.
     */
    @Override
    public String generateName(final IntUnaryOperator randomInRange, final int length) {
        if (length == 0) {
            return "LENGTH_WAS_ZERO";
        }

        final StringBuilder sb = new StringBuilder();

        // Initalize the first two characters of the name:
        final int initialSequenceIndex = randomInRange.applyAsInt(sequences.size());
        final String initialSequence = sequences.get(initialSequenceIndex);
        sb.append(initialSequence);

        char previous = initialSequence.charAt(0);
        char current = initialSequence.charAt(1);

        for (int i = 2; i < length; ++i) {
            final String key  = previous + "" + current;

            try {
                final char next = chooseNextCharacter(randomInRange, key);
                sb.append(next);
                previous = current;
                current = next;
            } catch (final NoSuchElementException e) {
                break;
            }

        }

        /*
         * We guarantee "at-least-as-long-as". Lengths of 0 or 1 will produce a result of
         * size 2, all other lengths will result in a string of the specified length.
         */
        return formatName(sb.toString());
    }

    /**
     * Parses the specified String to determine the
     * probability of a character appearing after
     * the previous two characters beginning with
     * the third character in the String and ending
     * with the last.
     *
     * @param string
     *         The string to parse.
     */
    public void acquireProbabilities(final String string) {
        if (string.length() < 2) {
            return;
        }

        for (int i = 2 ; i < string.length(); ++i) {
            final char previousChar = string.charAt(i - 2);
            final char currentChar = string.charAt(i - 1);
            final char nextChar = string.charAt(i);

            final String key = previousChar + "" + currentChar;

            if (!sequences.contains(key)) {
                sequences.add(key);
            }

            HashMap<Character, Integer> probabilities = sequenceProbabilities.get(key);
            if (probabilities == null) {
                probabilities = new HashMap<>();
                sequenceProbabilities.put(key, probabilities);
            }

            int existingValue = probabilities.getOrDefault(nextChar, 0);
            ++existingValue;
            probabilities.put(nextChar, existingValue);
        }
    }

    /**
     * Determines the character to follow the specified
     * sequence using the precomputed probabilities of
     * which characters most often appear after the
     * specified sequence.
     *
     * @param randomInRange
     *         A function that returns an arbitrary
     *         number in the range of [0, param)
     *
     * @param sequence
     *         The sequence to find the next character
     *         for.
     *
     * @return
     *         The next character of the sequence.
     *
     * @throws NoSuchElementException
     *          If the specified sequence has no
     *          corresponding probabilities to
     *          determine the next character from.
     */
    private char chooseNextCharacter(final IntUnaryOperator randomInRange, final String sequence) throws NoSuchElementException {
        final Map<Character, Integer> probabilities = sequenceProbabilities.get(sequence);

        if (probabilities == null) {
            throw new NoSuchElementException("There are no computed probabilities for the specified key.");
        }

        Integer highestProbability = null;
        final List<Character> highestStrings = new ArrayList<>();

        for(final Map.Entry<Character, Integer> entry : probabilities.entrySet()) {
            // If the initial data has not yet been set,
            // then set it.
            if (highestProbability == null) {
                highestStrings.add(entry.getKey());
                highestProbability = entry.getValue();
            } else {
                if(entry.getValue() > highestProbability) {
                    highestStrings.clear();
                    highestStrings.add(entry.getKey());
                    highestProbability = entry.getValue();
                } else {
                    highestStrings.add(entry.getKey());
                }
            }
        }

        final int randomIndex = randomInRange.applyAsInt(highestStrings.size());

        return highestStrings.get(randomIndex);
    }

    /**
     * Sets the first character of the specified name
     * to be upper case.
     *
     * @param name
     *         The name to format.
     *
     * @return
     *         The formatted name.
     */
    private String formatName(final String name) {
        final char firstCharacter = Character.toUpperCase(name.charAt(0));
        final String remainingCharacters = name.substring(1);
        return firstCharacter + remainingCharacters;
    }
}
