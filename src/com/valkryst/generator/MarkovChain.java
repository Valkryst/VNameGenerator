package com.valkryst.generator;

import java.util.ArrayList;
import java.util.HashMap;

public class MarkovChain <PreSequence, Sequence> {
    private final HashMap<PreSequence, MarkovEntry<Sequence>> markovEntries = new HashMap<>();

    public Float getProbability(final PreSequence preSequence, final Sequence sequence) {
        return markovEntries.get(preSequence).getProbabilities().get(sequence);
    }

    public ArrayList<Sequence> getAllSequences(final PreSequence preSequence) {
        MarkovEntry<Sequence> entry = markovEntries.get(preSequence);

        if (entry == null) {
            entry = new MarkovEntry<>();
            markovEntries.put(preSequence, entry);
        }

        return entry.getSequences();
    }

    public void incrementOccurrences(final PreSequence preSequence, final Sequence sequence) {
        MarkovEntry<Sequence> entry;

        // Get Entry:
        entry = markovEntries.get(preSequence);

        if (entry == null) {
            entry = new MarkovEntry<>();
            markovEntries.put(preSequence, entry);
        }

        entry.incrementOccurrence(sequence);
    }
}
