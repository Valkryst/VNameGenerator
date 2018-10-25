package com.valkryst.generator;

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
        this(beginnings, Collections.emptyList(), endings);
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
    public CombinatorialGenerator(final List<String> beginnings, List<String> middles, final List<String> endings) {
        if (middles == null) {
            middles = Collections.emptyList();
        }

        // Ensure lists aren't empty:
        if (beginnings == null || beginnings.size() == 0) {
            throw new IllegalArgumentException("The list of beginnings is empty or null.");
        }

        if (endings == null || endings.size() == 0) {
            throw new IllegalArgumentException("The list of endings is empty or null.");
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
