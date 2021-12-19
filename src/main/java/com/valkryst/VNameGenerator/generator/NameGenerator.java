package com.valkryst.VNameGenerator.generator;

import lombok.NonNull;

import java.util.Locale;

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

	/**
	 * Capitalizes the string held by a {@link StringBuilder}.
	 *
	 * @param sb A {@link StringBuilder}.
	 * @return The capitalized string.
	 */
	protected String capitalize(final StringBuilder sb) {
		return sb.substring(0, 1).toUpperCase(Locale.ROOT) + sb.substring(1);
	}

	/**
	 * Cleans the string held by a {@link StringBuilder} by performing the
	 * following operations:
	 *
	 * <ul>
	 *     <li>
	 *         Removes non-alphabetic characters from the start of the string.
	 *     </li>
	 *     <li>
	 *         Removes non-alphabetic characters from the end of the string.
	 *     </li>
	 * </ul>
	 *
	 * @param sb A {@link StringBuilder}.
	 * @return The cleaned {@link StringBuilder}.
	 */
	protected StringBuilder clean(final StringBuilder sb) {
		int codePoint = sb.codePointAt(0);
		if (!Character.isAlphabetic(codePoint)) {
			sb.deleteCharAt(0);
		}

		codePoint = sb.codePointAt(sb.length() - 1);
		if (!Character.isAlphabetic(codePoint)) {
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb;
	}
}
