package com.valkryst.VNameGenerator.generator;

import com.valkryst.VParser_CFG.ContextFreeGrammar;

import java.util.List;

public final class GrammarGenerator extends NameGenerator{
    /** The CFG to generate names with. */
    private final ContextFreeGrammar contextFreeGrammar;

    /**
     * Constructs a GrammarGenerator.
     *
     * @param rules
     *          The name generation rules.
     *
     * @throws NullPointerException
     *          If the list of rules is null.
     *
     * @throws IllegalArgumentException
     *          If the list of rules is empty.
     *          If there is a semantic error in one of the rules.
     */
    public GrammarGenerator(final List<String> rules) {
        if (rules == null) {
            throw new NullPointerException("The list of rules is null.");
        }

        if (rules.size() == 0) {
            throw new IllegalArgumentException("The list of rules is empty.");
        }

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

        return capitalizeFirstCharacter(longestResult);
    }
}
