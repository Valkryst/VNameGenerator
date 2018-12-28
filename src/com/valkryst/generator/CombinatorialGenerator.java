package com.valkryst.generator;

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
    public CombinatorialGenerator(final String[] beginnings, final String[] endings) {
        this(beginnings, new String[0], endings);
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
     *         If the lists of beginnings or endings are null or empty.
     */
    public CombinatorialGenerator(final String[] beginnings, String[] middles, final String[] endings) {
        if (middles == null) {
            middles = new String[0];
        }

        // Ensure lists aren't empty:
        if (beginnings == null || beginnings.length == 0) {
            throw new IllegalArgumentException("The list of beginnings is empty or null.");
        }

        if (endings == null || endings.length == 0) {
            throw new IllegalArgumentException("The list of endings is empty or null.");
        }

        this.beginnings = beginnings;
        this.middles = middles;
        this.endings = endings;
    }

    @Override
    public String generateName(int length) {
        if (length == 0) {
            length = 2;
        }

        final StringBuilder sb = new StringBuilder();

        // Construct Name:
        sb.append(beginnings[ThreadLocalRandom.current().nextInt(beginnings.length)]);

        if (middles.length > 0) {
            while (sb.length() < length) {
                sb.append(middles[ThreadLocalRandom.current().nextInt(middles.length)]);
            }
        }

        sb.append(endings[ThreadLocalRandom.current().nextInt(endings.length)]);

        return super.capitalizeFirstCharacter(sb.toString());
    }
}
