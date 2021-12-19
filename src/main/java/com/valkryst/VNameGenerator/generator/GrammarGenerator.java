package com.valkryst.VNameGenerator.generator;

import com.valkryst.VParser_CFG.ContextFreeGrammar;
import lombok.NonNull;

import java.util.List;

public final class GrammarGenerator extends NameGenerator{
    private ContextFreeGrammar contextFreeGrammar;

    /**
     * Constructs a GrammarGenerator.
     *
     * @param rules A set of Context Free Grammar rules.
     */
    public GrammarGenerator(final @NonNull List<String> rules) {
        setRules(rules);
    }

    @Override
    public String generate(final int maxLength) {
    	super.validateMaxLengthValid(maxLength);

		final var sb = new StringBuilder();
		sb.append(contextFreeGrammar.run());
		sb.setLength(Math.min(sb.length(), maxLength));
		return super.capitalize(sb);
    }

	/**
	 * Set a new set of rules.
	 *
	 * @param rules A set of Context Free Grammar rules.
	 */
	public void setRules(final @NonNull List<String> rules) {
		if (rules.size() == 0) {
			throw new IllegalArgumentException("The list of rules must have at least one rule. It is currently empty.");
		}

		contextFreeGrammar = new ContextFreeGrammar(rules);
	}
}
