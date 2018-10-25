package com.valkryst.generator;

public abstract class NameGenerator {
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
    abstract String generateName(final int length);

    /**
     * Capitalizes the first character of a string.
     *
     * @param string
     *          The string.
     *
     * @return
     *          The string with the first letter capitalized.
     */
    String capitalizeFirstCharacter(final String string) {
        if (string == null) {
            return "";
        }

        if (string.isEmpty()) {
            return string;
        }

        final char tmp[] = string.toCharArray();
        tmp[0] = Character.toUpperCase(tmp[0]);
        return new String(tmp);
    }
}
