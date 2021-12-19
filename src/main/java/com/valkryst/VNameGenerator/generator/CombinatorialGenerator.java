package com.valkryst.VNameGenerator.generator;

import lombok.NonNull;

import java.util.concurrent.ThreadLocalRandom;

public final class CombinatorialGenerator extends NameGenerator {
    /** A set of name beginnings. */
    private String[] beginnings;
    /** A set of name middles. */
    private String[] middles;
    /** A set of name endings. */
    private String[] endings;

    /**
     * Constructs a new CombinatorialGenerator.
     *
     * @param beginnings A set of name beginnings.
     * @param endings A set of name endings.
     */
    public CombinatorialGenerator(final @NonNull String[] beginnings, final @NonNull String[] endings) {
        this(beginnings, null, endings);
    }

    /**
     * Constructs a new CombinatorialGenerator.
     *
     * @param beginnings A set of name beginnings.
     * @param middles A set of name middles.
     * @param endings A set of name endings.
     *
     * @throws IllegalArgumentException
     *         If the lists of beginnings or endings are null or empty.
     */
    public CombinatorialGenerator(final @NonNull String[] beginnings, final String[] middles, final @NonNull String[] endings) {
		if (beginnings.length == 0) {
			throw new IllegalArgumentException("The array of beginnings must have at least one element. It is currently empty.");
		}

		if (endings.length == 0) {
			throw new IllegalArgumentException("The array of endings must have at least one element. It is currently empty.");
		}

		setBeginnings(beginnings);
		setMiddles(middles);
		setEndings(endings);
    }

    @Override
    public String generate(int maxLength) {
		super.validateMaxLengthValid(maxLength);

        final var stringBuilder = new StringBuilder();
        final var threadLocalRandom = ThreadLocalRandom.current();

        maxLength = threadLocalRandom.nextInt((int) (maxLength * 0.5), maxLength + 1);

        final var beginning = beginnings[threadLocalRandom.nextInt(beginnings.length)];
		stringBuilder.append(beginning);

        if (middles.length != 0) {
            while (stringBuilder.length() < maxLength) {
				stringBuilder.append(middles[threadLocalRandom.nextInt(middles.length)]);
            }
        }

        if (maxLength > 1) {
        	final var temp = endings[threadLocalRandom.nextInt(endings.length)];
        	stringBuilder.replace(maxLength - temp.length(), maxLength, temp);
		}

        return super.capitalize(super.clean(stringBuilder));
    }

	/**
	 * Sets a new set of beginnings.
	 *
	 * @param beginnings A set of beginnings.
	 */
    public void setBeginnings(final @NonNull String[] beginnings) {
		if (beginnings.length == 0) {
			throw new IllegalArgumentException("The array of beginnings must have at least one element. It is currently empty.");
		}

		super.lowercaseAllElements(beginnings);

		this.beginnings = beginnings;
	}

	/**
	 * Sets a new set of middles.
	 *
	 * @param middles A set of middles.
	 */
	public void setMiddles(final String[] middles) {
		if (middles == null || middles.length == 0) {
			this.middles = new String[0];
			return;
		}

		super.lowercaseAllElements(middles);

		this.middles = middles;
	}

	/**
	 * Sets a new set of endings.
	 *
	 * @param endings A set of endings.
	 */
	public void setEndings(final @NonNull String[] endings) {
		if (endings.length == 0) {
			throw new IllegalArgumentException("The array of endings must have at least one element. It is currently empty.");
		}

		super.lowercaseAllElements(endings);

		this.endings = endings;
	}
}
