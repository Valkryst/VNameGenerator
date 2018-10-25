package com.valkryst.generator;

import lombok.NonNull;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class CombinatorialGenerator extends NameGenerator {
    /** The name-beginnings. */
    private final String[] beginnings;
    /** The name-middles. */
    private final String[] middles;
    /** The name-endings. */
    private final String[] endings;

    /**
     * Constructs a new CombinatorialGenerator.
     *
     * @param beginnings
     *         The name-beginnings.
     *
     * @param endings
     *         The name-endings.
     */
    public CombinatorialGenerator(final List<String> beginnings, final List<String> endings) {
        this(beginnings, null, endings);
    }

    /**
     * Constructs a new CombinatorialGenerator.
     *
     * @param beginnings
     *         The name-beginnings.
     *
     * @param middles
     *         The name-middles.
     *
     * @param endings
     *         The name-endings.
     *
     * @throws IllegalArgumentException
     *         If the lists of beginnings or endings are null.
     */
    public CombinatorialGenerator(List<String> beginnings, List<String> middles, List<String> endings) {
        if (middles == null) {
            middles = Collections.emptyList();
        }

        // Ensure lists aren't empty:
        if (beginnings.size() == 0) {
            throw new IllegalArgumentException("The list of beginnings is empty.");
        }

        if (endings.size() == 0) {
            throw new IllegalArgumentException("The list of endings is empty.");
        }

        this.beginnings = beginnings.toArray(new String[0]);
        this.middles = middles.toArray(new String[0]);
        this.endings = endings.toArray(new String[0]);
    }

    @Override
    public String generateName(int length) {
        if (length == 0) {
            length = 2;
        }

        final StringBuilder sb = new StringBuilder();

        // Construct Name:
        sb.append(chooseRandomElementFrom(beginnings));

        if (middles.length > 0) {
            while (sb.length() < length) {
                sb.append(chooseRandomElementFrom(middles));
            }
        }

        sb.append(chooseRandomElementFrom(endings));

        return super.capitalizeFirstCharacter(sb.toString());
    }

    /**
     * Chooses a random String from an array.
     *
     * @param arr
     *        The array.
     *
     * @return
     *        A random String from the array.
     *
     * @throws NullPointerException
     *        If the array is null.
     */
    private String chooseRandomElementFrom(final @NonNull String[] arr) {
        final int index = ThreadLocalRandom.current().nextInt(arr.length);
        return arr[index];
    }
}
