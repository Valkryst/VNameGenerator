package com.valkryst.generator;

import com.valkryst.VParser_CFG.ContextFreeGrammar;

import java.util.List;
import java.util.Objects;

public final class GrammarNameGenerator implements NameGenerator{
    /** The CFG to generate names with. */
    private final ContextFreeGrammar contextFreeGrammar;

    /**
     * Constructs a GrammarNameGenerator.
     *
     * @param rules
     *         The name generation rules.
     *
     * @throws NullPointerException
     *         If the list of rules is null.
     *
     * @throws IllegalArgumentException
     *         If there is a semantic error in one of the rules.
     */
    public GrammarNameGenerator(final List<String> rules) {
        Objects.requireNonNull(rules);

        contextFreeGrammar = new ContextFreeGrammar(rules);
    }


    @Override
    public String generateName(int length) {
        if (length < 2) {
            length = 2;
        }

        String longestResult = "";

        for (int i = 0 ; i < 100 ; i++) {
            final String tmp = contextFreeGrammar.run();

            if (tmp.length() > longestResult.length()) {
                longestResult = tmp;
            }

            if (tmp.length() > length) {
                longestResult = tmp.substring(0, length);
                break;
            }

            if (tmp.length() == length) {
                longestResult = tmp;
                break;
            }
        }

        // Capitalize the first letter of the name:
        return longestResult.substring(0, 1).toUpperCase() + longestResult.substring(1);
    }
}
