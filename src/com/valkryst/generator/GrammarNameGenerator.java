package com.valkryst.generator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class GrammarNameGenerator implements NameGenerator{
    /** The name generation rules. */
    private String[] rules;

    /**
     * Constructs a new GrammarNameGenerator with the specified grammar rules.
     *
     * @param rules
     *         The rules of the grammar to use when generating a name.
     */
    public GrammarNameGenerator(final List<String> rules) {
        this.rules = rules.toArray(new String[rules.size()]);
    }


    @Override
    public String generateName(final int length) {
        final char startingSymbol = (char) ThreadLocalRandom.current().nextInt(Character.MAX_VALUE);
        return generateName(length, startingSymbol);
    }

    /**
     * Attempts to generate a name of at-least the specified length.
     *
     * @param length
     *         The length of the name to generateName.
     *
     *         If the value is less than or equal to zero, then this parameter is ignored.
     *
     *         No guarantee is made that the name will be exactly this length.
     *
     * @param startingSymbol
     *         The symbol to begin generating the name from.
     *
     * @return
     *         The generated name.
     */
    public String generateName(final int length, final char startingSymbol) {
        if (length == 0) {
            return "LENGTH_WAS_ZERO";
        }

        final StringBuilder sb = new StringBuilder();
        sb.append(startingSymbol);

        for (int i = 0; i < sb.length(); i++) {
            final char currentChar = sb.charAt(i);
            final boolean isCurrentCharUpperCase = Character.isUpperCase(currentChar);

            if (isCurrentCharUpperCase) {
                final String substitution = chooseRandomRuleOption(currentChar);

                if (substitution != null) {
                    String temp = sb.toString();
                    temp = temp.replace(String.valueOf(currentChar), substitution);

                    sb.setLength(0);
                    sb.append(temp);

                    i--;
                }
            }
        }

        // Return Name:
        return sb.toString();
    }

    /**
     * Searches the rules of the grammar for the first rule whose first character matches that of the specified
     * character.
     *
     * Ex:
     *      character = 'S'
     *
     *      rule = "T tt Tt"
     *      rule = "S ss Ss"
     *      rule = "S tt St"
     *
     *      The first rule is ignored and the second rule is used.
     *
     * @param character
     *         The identifier of the grammar rule to use.
     *
     * @return
     *         The chosen rule or null if no rule could be chosen.
     */
    private String chooseRandomRuleOption(final char character) {
        for (final String rule : rules) {
            final String[] tokens = rule.split(" ");

            if (tokens[0].equals(String.valueOf(character))) {
                if (tokens.length > 1) {
                    final int randomOptionIndex = ThreadLocalRandom.current().nextInt(tokens.length - 1) + 1;
                    return tokens[randomOptionIndex];
                }
            }
        }

        return null;
    }
}
