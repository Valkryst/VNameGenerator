package com.valkryst.VNameGenerator.generator;

import lombok.NonNull;

public final class ConsonantVowelGenerator extends NameGenerator {
    /** A set of consonants. */
    private String[] consonants;
    /** A set of vowels. */
    private String[] vowels;

	/** Constructs a new ConsonantVowelGenerator. */
	public ConsonantVowelGenerator() {
		setConsonants(new String[] {
			"al", "an", "ar", "as", "at", "ea", "ed", "en", "er", "es", "ha", "he", "hi", "in", "is", "it",
			"le", "me", "nd", "ne", "ng", "nt", "on", "or", "ou", "re", "se", "st", "te", "th", "ti", "to",
			"ve", "wa", "it"
		});

		setVowels(new String[] { "a", "e", "i", "o", "u", "y" });
	}

    @Override
    public String generate(int maxLength) {
		super.validateMaxLength(maxLength);
		maxLength = super.randomizeMaxLength(maxLength);

        final var stringBuilder = new StringBuilder();

        String temp;
        while (stringBuilder.length() < maxLength) {
            if (maxLength % 2 == 0) {
				temp = super.randomArrayElement(vowels);
            } else {
            	temp = super.randomArrayElement(consonants);
            }

			stringBuilder.append(temp);
        }

        while (stringBuilder.length() > maxLength) {
        	stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}

        return super.capitalize(super.clean(stringBuilder));
    }

	/**
	 * Sets a new set of consonants.
	 *
	 * @param consonants A set of consonants.
	 */
	public void setConsonants(final @NonNull String[] consonants) {
    	if (consonants.length == 0) {
    		throw new IllegalArgumentException("The array of consonants must have at least one element. It is currently empty.");
		}

		super.lowercaseAllElements(consonants);

    	this.consonants = consonants;
	}

	/**
	 * Sets a new set of vowels.
	 *
	 * @param vowels A set of vowels.
	 */
    public void setVowels(final @NonNull String[] vowels) {
		if (consonants.length == 0) {
			throw new IllegalArgumentException("The array of vowels must have at least one element. It is currently empty.");
		}

		super.lowercaseAllElements(vowels);

    	this.vowels = vowels;
	}
}
