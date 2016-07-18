package com.valkryst;

import java.util.ArrayList;
import java.util.List;

public class WordFilter {
    /** The list of disallowed strings. */
    private final List<String> disallowedStrings = new ArrayList<>();

    /**
     * Determines if the specified string contains any of the
     * disallowed strings.
     *
     * @param input
     *         The string to be checked.
     *
     * @return
     *         Whether or not the input string contains any of
     *         the disallowed strings.
     */
    public boolean isAllowed(final String input) {
        final String lowerCaseInput = input.toLowerCase();

        return ! disallowedStrings.parallelStream()
                                  .anyMatch(lowerCaseInput::contains);
    }

    /**
     * Adds the specified string to the list of disallowed
     * strings.
     *
     * @param string
     *         The string to add.
     */
    public void addDisallowedString(final String string) {
        disallowedStrings.add(string.toLowerCase());
    }

    /**
     * Removes the specified string from the list of disallowed
     * strings, if it exists within the list.
     *
     * @param string
     *         The string to remove.
     */
    public void removeDisallowedString(final String string) {
        disallowedStrings.remove(string);
    }
}
