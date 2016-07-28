package com.valkryst.generator;

import com.valkryst.builder.ConsonantVowelNameGeneratorBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntUnaryOperator;

public class ConsonantVowelNameGeneratorTest {
    private static final List<String> CONSONANTS = new ArrayList<>();

    private static final List<String> VOWELS = new ArrayList<>();

    static {
        CONSONANTS.add("al");
        CONSONANTS.add("an");
        CONSONANTS.add("ar");
        CONSONANTS.add("as");
        CONSONANTS.add("at");
        CONSONANTS.add("ea");
        CONSONANTS.add("ed");
        CONSONANTS.add("en");
        CONSONANTS.add("er");
        CONSONANTS.add("es");
        CONSONANTS.add("ha");
        CONSONANTS.add("he");
        CONSONANTS.add("hi");
        CONSONANTS.add("in");
        CONSONANTS.add("is");
        CONSONANTS.add("it");
        CONSONANTS.add("le");
        CONSONANTS.add("me");
        CONSONANTS.add("nd");
        CONSONANTS.add("ne");
        CONSONANTS.add("ng");
        CONSONANTS.add("nt");
        CONSONANTS.add("on");
        CONSONANTS.add("or");
        CONSONANTS.add("ou");
        CONSONANTS.add("re");
        CONSONANTS.add("se");
        CONSONANTS.add("st");
        CONSONANTS.add("te");
        CONSONANTS.add("th");
        CONSONANTS.add("ti");
        CONSONANTS.add("to");
        CONSONANTS.add("ve");
        CONSONANTS.add("wa");
        CONSONANTS.add("it");

        VOWELS.add("a");
        VOWELS.add("e");
        VOWELS.add("i");
        VOWELS.add("o");
        VOWELS.add("u");
        VOWELS.add("y");
    }

    @Test
    public void generateNameUsingRandom() {
        final Random random = new Random(System.currentTimeMillis());

        // Setup the Builder:
        final ConsonantVowelNameGeneratorBuilder builder = new ConsonantVowelNameGeneratorBuilder();
        builder.setConsonants(CONSONANTS);
        builder.setVowels(VOWELS);

        // Setup & Test the Generator:
        final ConsonantVowelNameGenerator generator = builder.build();

        for(int i = 0 ; i < 100 ; i++) {
            generator.generateName(random, i % 20);
        }
    }

    @Test
    public void generateNameUsingIntUnaryOperator() {
        final IntUnaryOperator randomInRange = ThreadLocalRandom.current()::nextInt;

        // Setup the Builder:
        final ConsonantVowelNameGeneratorBuilder builder = new ConsonantVowelNameGeneratorBuilder();
        builder.setConsonants(CONSONANTS);
        builder.setVowels(VOWELS);

        // Setup & Test the Generator:
        final ConsonantVowelNameGenerator generator = builder.build();

        for(int i = 0 ; i < 100 ; i++) {
            generator.generateName(randomInRange, i % 20);
        }
    }

    @Test
    public void longGeneration() {
        final IntUnaryOperator randomInRange = ThreadLocalRandom.current()::nextInt;

        // Setup the Builder:
        final ConsonantVowelNameGeneratorBuilder builder = new ConsonantVowelNameGeneratorBuilder();
        builder.setConsonants(CONSONANTS);
        builder.setVowels(VOWELS);

        // Setup & Test the Generator:
        final ConsonantVowelNameGenerator generator = builder.build();

        for (int i = 0 ; i < 100 ; i++) {
            System.out.println(generator.generateName(randomInRange, i));
        }

        for (int i = 0 ; i < 1_000_000 ; i++) {
            generator.generateName(randomInRange, i % 20);
        }
    }
}
