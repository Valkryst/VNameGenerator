package com.valkryst.markov;

import lombok.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class MarkovChain {
    private final Map<String, Entry> entries = new HashMap<>();

    /**
     * Trains the Markov Chain using a set of strings.
     *
     * @param strings
     *          The training strings.
     */
    public void train(final @NonNull List<String> strings) {
        final Map<String, Map<Character, Integer>> builders = new HashMap<>();

        for (final String string : strings) {
            if (string.length() < 2) {
                return;
            }

            for (int i = 2 ; i < string.length() ; i++) {
                final String charGroup = string.substring(i - 2, i);
                final char subsequentChar = string.charAt(i);

                builders.putIfAbsent(charGroup, new HashMap<>());

                builders.get(charGroup).putIfAbsent(subsequentChar, 0);
                builders.get(charGroup).put(string.charAt(i), builders.get(charGroup).get(subsequentChar) + 1);
            }
        }

        for (final Map.Entry<String, Map<Character, Integer>> entry : builders.entrySet()) {
            entries.put(entry.getKey(), new Entry(entry.getValue()));
        }
    }

    /**
     * Chooses a random character to follow a string.
     *
     * @param string
     *          The string.
     *
     * @return
     *          The character.
     */
    public Optional<Character> chooseRandomCharacter(final String string) {
        if (string == null || string.isEmpty()) {
            return Optional.empty();
        }

        if (entries.containsKey(string)) {
            return entries.get(string).chooseCharacter();
        } else {
            return Optional.empty();
        }
    }

    /**
     * Chooses a random string from the set of valid strings.
     *
     * @return
     *          The string.
     */
    public String chooseRandomString() {
        final int index = ThreadLocalRandom.current().nextInt(entries.size());
        return (String) entries.keySet().toArray()[index];
    }
}
