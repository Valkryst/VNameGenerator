package com.valkryst;

import java.util.Random;

public interface NameGenerator {
    /**
     * Randomly generates a name of at-least the desired length.
     *
     * @param random
     *         The instance of Random to use when necessary.
     *
     * @param length
     *         The desired name length.
     *
     * @return
     */
    String generateName(final Random random, final int length);
}
