package com.valkryst.generator;

import com.valkryst.builder.CombinatorialBuilder;

import java.util.concurrent.ThreadLocalRandom;

public final class CombinatorialNameGenerator implements NameGenerator {
    /** The name-beginnings. */
    private final String[] beginnings;
    /** The name-middles. */
    private final String[] middles;
    /** The name-endings. */
    private final String[] endings;

    /**
     * Constructs a CombinatorialNameGenerator.
     *
     * @param builder
     *         The builder.
     */
    public CombinatorialNameGenerator(final CombinatorialBuilder builder) {
        final int totalBeginnings = builder.getBeginnings().size();
        final int totalMiddles = builder.getMiddles().size();
        final int totalEndings = builder.getEndings().size();

        beginnings = builder.getBeginnings().toArray(new String[totalBeginnings]);
        middles = builder.getMiddles().toArray(new String[totalMiddles]);
        endings = builder.getEndings().toArray(new String[totalEndings]);
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

    private String chooseRandomElementFrom(final String[] arr) {
        final int index = ThreadLocalRandom.current().nextInt(arr.length);
        return arr[index];
    }
}
