package com.valkryst.generator;

public interface NameGenerator {
    /**
     * Generates a name.
     *
     * The resulting name is not guaranteed to be of the specified length.
     *
     * @param length
     *        The desired name length.
     *
     * @return
     *        The name.
     */
    String generateName(final int length);
}
