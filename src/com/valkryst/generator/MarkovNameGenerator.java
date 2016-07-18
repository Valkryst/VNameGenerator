package com.valkryst.generator;

import com.valkryst.builder.MarkovNameGeneratorBuilder;

import java.util.*;

public class MarkovNameGenerator {
    /**
     * The List containing all two-character combinations found
     * in the training names.
     */
    private List<String> sequences = new ArrayList<>();
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
    private HashMap<String, HashMap<String, Integer>> sequenceProbabilities = new HashMap<>();

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
     * @param random
     *         The instance of Random to use when
     *         necessary.
     *
     * @param length
     *         The length of the name to generate.
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
    public String generateName(final Random random, final int length) {
        if(length == 0) {
            return "LENGTH_WAS_ZERO";
        }

        final StringBuilder sb = new StringBuilder();

        // Initalize the first two characters of the name:
        int initialSequenceIndex = random.nextInt(sequences.size());
        sb.append(sequences.get(initialSequenceIndex));

        int counter = 1;

        char previousChar;
        char currentChar;
        String key;

        // Ensure that the name, if no error occurs, will be at-least
        // as long as the preferred length.
        while(sb.length() < length) {
                // Get the current and previous character, then combine
                // them to create a key.
                previousChar = sb.charAt(counter - 1);
                currentChar = sb.charAt(counter);

                key = String.valueOf(previousChar) + String.valueOf(currentChar);

                //
                try {
                    sb.append(chooseNextCharacter(random, key));
                } catch(final NoSuchElementException e) {
                    break;
                }

                counter++;
        }

        // Return Name:
        if(length > 0 && length <= sb.length()) {
            return formatName(sb.substring(0, length));
        } else {
            return formatName(sb.toString());
        }
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
        if(string.length() >= 2) {
            char previousChar;
            char currentchar;
            char nextChar;
            String key;
            String value;

            for(int i = 1 ; i < string.length() - 1 ; i++) {
                previousChar = string.charAt(i - 1);
                currentchar = string.charAt(i);
                nextChar = string.charAt(i + 1);

                key = String.valueOf(previousChar) + String.valueOf(currentchar);
                value = String.valueOf(nextChar);

                if(! sequences.contains(key)) {
                    sequences.add(key);
                }

                if(! sequenceProbabilities.containsKey(key)) {
                    sequenceProbabilities.put(key, new HashMap<>());
                }

                if(! sequenceProbabilities.get(key).containsKey(value)) {
                    sequenceProbabilities.get(key).put(value, 1);
                } else {
                    Integer probability = sequenceProbabilities.get(key).get(value);
                    probability++;
                    sequenceProbabilities.get(key).put(value, probability);
                }
            }
        }
    }

    /**
     * Determines the character to follow the specified
     * sequence using the precomputed probabilities of
     * which characters most often appear after the
     * specified sequence.
     *
     * @param random
     *         The instance of Random to use when
     *         necessary.
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
    private String chooseNextCharacter(final Random random, final String sequence) throws NoSuchElementException {
        if(! sequenceProbabilities.containsKey(sequence)) {
            throw new NoSuchElementException("There are no computed probabilities for the specified key.");
        }

        Integer highestProbability = null;
        final List<String> highestStrings = new ArrayList<>();

        for(final Map.Entry<String, Integer> entry : sequenceProbabilities.get(sequence).entrySet()) {
            // If the initial data has not yet been set,
            // then set it.
            if(highestProbability == null) {
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

        final int randomIndex = random.nextInt(highestStrings.size());

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
    private String formatName(String name) {
        Character firstCharacter = name.charAt(0);
        firstCharacter = Character.toUpperCase(firstCharacter);

        final String remainingCharacters = name.substring(1);

        return String.valueOf(firstCharacter) + remainingCharacters;
    }
}
