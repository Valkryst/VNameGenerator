package com.valkryst.generator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

public class MarkovEntry <Sequence> {
    @Getter private final ArrayList<Sequence> sequences = new ArrayList<>();
    private float totalOccurrences = 0;
    @Getter private final HashMap<Sequence, Integer> occurrences = new HashMap<>();
    @Getter private final HashMap<Sequence, Float> probabilities = new HashMap<>();

    public void addNewSequence(final Sequence sequence) {
        if (sequences.contains(sequence) == false) {
            sequences.add(sequence);
            occurrences.put(sequence, 0);
            probabilities.put(sequence, 0f);
        }
    }

    public void incrementOccurrence(final Sequence sequence) {
        addNewSequence(sequence);
        totalOccurrences++;
        occurrences.put(sequence, occurrences.get(sequence) + 1);
        computeProbabilities();
    }

    private void computeProbabilities() {
        sequences.forEach(sequence -> {
            Integer occurrence = occurrences.get(sequence);
            Float probability = occurrence / totalOccurrences;
            probabilities.put(sequence, probability);
        });
    }
}
