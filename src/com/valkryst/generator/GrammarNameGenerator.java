package com.valkryst.generator;

import com.valkryst.VParser_CFG.ContextFreeGrammar;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class GrammarNameGenerator implements NameGenerator{
    private final ContextFreeGrammar contextFreeGrammar;

    /**
     * Constructs a GrammarNameGenerator.
     *
     * @param rules
     *         The name generation rules.
     *
     * @throws IllegalArgumentException
     *         If there is a semantic error in one of the rules.
     */
    public GrammarNameGenerator(final List<String> rules) {
        contextFreeGrammar = new ContextFreeGrammar(rules);
    }


    @Override
    public String generateName(final int length) {
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

        return longestResult;
    }
}
