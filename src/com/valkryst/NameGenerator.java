package com.valkryst;

import java.util.Random;

public interface NameGenerator {
    /**
     * Randomly generates a name of at-least the desired length.
     *
     * @param length
     *         The desired name length.
     *
     * @return
     */
    String generateName(final int length);
}
