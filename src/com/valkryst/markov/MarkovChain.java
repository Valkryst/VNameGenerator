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
     *        The training strings.
     */
    public void train(final @NonNull List<String> strings) {
        final Map<String, EntryBuilder> builders = new HashMap<>();

        for (final String string : strings) {
            train(builders, string);
        }

        for (final Map.Entry<String, EntryBuilder> entry : builders.entrySet()) {
            entries.put(entry.getKey(), entry.getValue().build());
        }
    }

    /**
     * Trains the Markov Chain with a string.
     *
     * For each group of two characters, an occurrence of the following third
     * character is recorded.
     *
     * Strings with a length less than two are ignored.
     *
     * @param builders
     *        todo JavaDoc
     *
     * @param string
     *        The string.
     */
    private void train(final @NonNull Map<String, EntryBuilder> builders, final @NonNull String string) {
        if (string.length() < 2) {
            return;
        }

        for (int i = 2 ; i < string.length() ; i++) {
            final String charGroup = string.substring(i - 2, i);
            final Character thirdChar = string.charAt(i);

            builders.putIfAbsent(charGroup, new EntryBuilder());
            builders.get(charGroup).addOccurrence(thirdChar);
        }
    }

    /**
     * Chooses a random character to follow a string.
     *
     * @param string
     *        The string.
     *
     * @return
     *        The character.
     */
    public Optional<Character> chooseRandomCharacter(final @NonNull String string) {
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
     *        The string.
     */
    public String chooseRandomString() {
        final int index = ThreadLocalRandom.current().nextInt(entries.size());
        return (String) entries.keySet().toArray()[index];
    }
}
