package com.valkryst.generator;

public interface NameGenerator {
    /**
     * Generates a name with a length in the range of [2, length].
     *
     * @param length
     *        The desired name length.
     *
     * @return
     *        The name.
     */
    String generateName(final int length);
}
