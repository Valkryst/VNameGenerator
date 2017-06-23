package com.valkryst.generator;

import com.valkryst.builder.CombinatorialBuilder;

import java.util.concurrent.ThreadLocalRandom;

public final class CombinatorialNameGenerator implements NameGenerator {
    /** The array containing all loaded name-beginnings. */
    private final String[] beginnings;
    /** The array containing all loaded name-middles. */
    private final String[] middles;
    /** The array containing all loaded name-endings. */
    private final String[] endings;

    /**
     * Constructs a new combinatorial name generator from the specified builder.
     *
     * @param builder
     *         The builder to retrieve the beginnings, middles, and ends from.
     *
     * @param usesMiddles
     *         Whether or not name-middles can be used in name generation.
     */
    public CombinatorialNameGenerator(final CombinatorialBuilder builder, final boolean usesMiddles) {
        final int totalBeginnings = builder.getBeginnings().size();
        final int totalEndings = builder.getEndings().size();

        beginnings = builder.getBeginnings().toArray(new String[totalBeginnings]);
        endings = builder.getEndings().toArray(new String[totalEndings]);

        if(usesMiddles) {
            final int totalMiddles = builder.getMiddles().size();
            middles = builder.getMiddles().toArray(new String[totalMiddles]);
        } else {
            middles = null;
        }
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
