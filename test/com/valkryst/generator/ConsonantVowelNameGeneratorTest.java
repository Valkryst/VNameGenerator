package com.valkryst.generator;

import com.valkryst.builder.ConsonantVowelNameGeneratorBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsonantVowelNameGeneratorTest {
    @Test
    public void generate() {
        final Random random = new Random(System.currentTimeMillis());

        final List<String> consonants = new ArrayList<>();
        consonants.add("al");
        consonants.add("an");
        consonants.add("ar");
        consonants.add("as");
        consonants.add("at");
        consonants.add("ea");
        consonants.add("ed");
        consonants.add("en");
        consonants.add("er");
        consonants.add("es");
        consonants.add("ha");
        consonants.add("he");
        consonants.add("hi");
        consonants.add("in");
        consonants.add("is");
        consonants.add("it");
        consonants.add("le");
        consonants.add("me");
        consonants.add("nd");
        consonants.add("ne");
        consonants.add("ng");
        consonants.add("nt");
        consonants.add("on");
        consonants.add("or");
        consonants.add("ou");
        consonants.add("re");
        consonants.add("se");
        consonants.add("st");
        consonants.add("te");
        consonants.add("th");
        consonants.add("ti");
        consonants.add("to");
        consonants.add("ve");
        consonants.add("wa");
        consonants.add("it");

        final List<String> vowels = new ArrayList<>();
        vowels.add("a");
        vowels.add("e");
        vowels.add("i");
        vowels.add("o");
        vowels.add("u");
        vowels.add("y");

        final ConsonantVowelNameGeneratorBuilder builder = new ConsonantVowelNameGeneratorBuilder();
        builder.setConsonants(consonants);
        builder.setVowels(vowels);

        final ConsonantVowelNameGenerator generator = builder.build();

        for(int i = 0 ; i < 10 ; i++) {
            System.out.println(generator.generateName(random, 6));
        }
    }
}
