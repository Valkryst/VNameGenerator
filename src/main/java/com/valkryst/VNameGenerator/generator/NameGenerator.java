package com.valkryst.VNameGenerator.generator;

import lombok.NonNull;

public abstract class NameGenerator {
    /**
     * Generates a name of at most {@code maxLength} characters.
     *
     * @param maxLength Maximum length of the generated name.
     * @return The name.
     */
    abstract String generate(final int maxLength);

	/**
	 * Determines the validity of a maximum length
	 *
	 * @param maxLength A maximum length.
	 * @throws IllegalArgumentException If {@code maxLength} <= 0.
	 */
	protected final void validateMaxLengthValid(final int maxLength) throws IllegalArgumentException {
    	if (maxLength <= 0) {
    		throw new IllegalArgumentException("The maximum length, which is currently " + maxLength + " must be at least 1.");
		}
	}

	protected final void lowercaseAllElements(final @NonNull String[] array) {
		for (int i = 0 ; i < array.length ; i++) {
			array[i] = array[i].toLowerCase();
		}
	}
}
