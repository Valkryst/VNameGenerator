package com.valkryst.generator;

public interface NameGenerator {
    /**
     * Randomly generates a name with a length of at-least two characters.
     *
     * @param length
     *        The desired name length.
     *
     * @return
     *        The name.
     */
    String generateName(final int length);
}
