package com.valkryst.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class CombinatorialNameGenerator implements NameGenerator {
    /** The name-beginnings. */
    private final String[] beginnings;
    /** The name-middles. */
    private final String[] middles;
    /** The name-endings. */
    private final String[] endings;

    /**
     * Constructs a new CombinatorialNameGenerator.
     *
     * @param beginnings
     *         The name-beginnings.
     *
     * @param endings
     *         The name-endings.
     */
    public CombinatorialNameGenerator(final List<String> beginnings, final List<String> endings) {
        this(beginnings, null, endings);
    }

    /**
     * Constructs a new CombinatorialNameGenerator.
     *
     * @param beginnings
     *         The name-beginnings.
     *
     * @param middles
     *         The name-middles.
     *
     * @param endings
     *         The name-endings.
     */
    public CombinatorialNameGenerator(final List<String> beginnings, List<String> middles, final List<String> endings) {
        // Ensure lists aren't null:
        if (beginnings == null) {
            throw new IllegalArgumentException("The list of beginnings is null.");
        }

        if (middles == null) {
            middles = new ArrayList<>(0);
        }

        if (endings == null) {
            throw new IllegalArgumentException("The list of endings is null.");
        }

        // Ensure lists aren't empty:
        if (beginnings.size() == 0) {
            throw new IllegalArgumentException("The list of beginnings is empty.");
        }

        if (endings.size() == 0) {
            throw new IllegalArgumentException("The list of endings is empty.");
        }

        this.beginnings = beginnings.toArray(new String[beginnings.size()]);
        this.middles = middles.toArray(new String[middles.size()]);
        this.endings = endings.toArray(new String[endings.size()]);
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

        return sb.toString();
    }

    /**
     * Chooses a random String from an array.
     *
     * @param arr
     *        The array.
     *
     * @return
     *        A random String from the array.
     */
    private String chooseRandomElementFrom(final String[] arr) {
        final int index = ThreadLocalRandom.current().nextInt(arr.length);
        return arr[index];
    }
}
