package com.valkryst.VNameGenerator.generator;

import lombok.NonNull;

import java.util.concurrent.ThreadLocalRandom;

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
		super.validateMaxLengthValid(maxLength);

        final var stringBuilder = new StringBuilder();
        final var threadLocalRandom = ThreadLocalRandom.current();

		maxLength = threadLocalRandom.nextInt((int) (maxLength * 0.5), maxLength + 1);

        String temp;
        while (stringBuilder.length() < maxLength) {
            if (maxLength % 2 == 0) {
				temp = vowels[threadLocalRandom.nextInt(vowels.length)];
            } else {
            	temp = consonants[threadLocalRandom.nextInt(consonants.length)];
            }

			stringBuilder.append(temp);
        }

        while (stringBuilder.length() > maxLength) {
        	stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}

        return stringBuilder.substring(0, 1).toUpperCase() + stringBuilder.substring(1);
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
