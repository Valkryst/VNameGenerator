package com.valkryst.markov;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class EntryBuilder {
    /** The number of occurrences of each character. */
    @Getter private final Map<Character, Integer> occurrences = new HashMap<>();

    /**
     * Constructs a Entry.
     *
     * @return
     *        The Entry.
     */
    public Entry build() {
        return new Entry(this);
    }

    /**
     * Adds an occurrence of a character to the count of occurrences.
     *
     * @param character
     *        The character.
     */
    public void addOccurrence(final char character) {
        occurrences.putIfAbsent(character, 0);
        occurrences.put(character, occurrences.get(character) + 1);
    }
}
