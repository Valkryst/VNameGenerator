package com.valkryst;

import java.util.Random;
import java.util.function.IntUnaryOperator;

public interface NameGenerator {

    /**
     * Randomly generates a name using the provided random function, of at least the given length
     *
     * @param randomInRange
     *         A function that returns an arbitrary
     *         number in the range of [0, param)
     *
     * @param length
     *         The length of the name to generateName.
     *
     * @return
     */
    String generateName(final IntUnaryOperator randomInRange, final int length);

    default String generateName(final Random random, final int length) {
        return generateName(random::nextInt, length);
    }
}
