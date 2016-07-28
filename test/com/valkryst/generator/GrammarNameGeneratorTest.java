package com.valkryst.generator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntUnaryOperator;

public class GrammarNameGeneratorTest {
    private static final List<String> RULES = new ArrayList<>();

    static {
        // Balin, Bifur, Bofur, Bombur, Borin
        // Dain, Dis, Dori, Dwalin, Farin, Fili
        // Floi, Frar, Frerin, Fror, Fundin, Gaiml
        // Gimli, Gloin, Groin, Gror, Ibun, Khim
        // Kili, Loni, Mim, Nain, Nali, Nar, Narvi
        // Nori, Oin, Ori, Telchar, Thorin, Thrain
        // Thror,

        RULES.add("S B D F G I K L M N O T");
        RULES.add("A a aL aI aR");
        RULES.add("B b bA bI bO");
        RULES.add("C c");
        RULES.add("D d dA dI dO dW dU");
        RULES.add("E e eR eL");
        RULES.add("F f fA fI fL fR fU fO");
        RULES.add("G g gA gI gL gR");
        RULES.add("H h hI hA");
        RULES.add("I i");
        RULES.add("K k kH kI");
        RULES.add("L l lO");
        RULES.add("M m mI");
        RULES.add("N n nA nO");
        RULES.add("O o oI oR");
        RULES.add("P p");
        RULES.add("Q q");
        RULES.add("R r rI rO rV");
        RULES.add("S s");
        RULES.add("T t tE tH");
        RULES.add("U u uR uN");
        RULES.add("V v");
        RULES.add("W w wA");
        RULES.add("X x");
        RULES.add("Y y");
        RULES.add("Z z");
    }

    @Test
    public void generateNameUsingRandom() {
        final Random random = new Random(System.currentTimeMillis());

        // Setup & Test the Generator:
        final GrammarNameGenerator grammarNameGenerator = new GrammarNameGenerator(RULES);

        for(int i = 0 ; i < 100 ; i++) {
            grammarNameGenerator.generateName(random, i % 6, 'S');
        }
    }

    @Test
    public void generateNameUsingIntUnaryOperator() {
        final IntUnaryOperator randomInRange = ThreadLocalRandom.current()::nextInt;

        // Setup & Test the Generator:
        final GrammarNameGenerator grammarNameGenerator = new GrammarNameGenerator(RULES);

        for(int i = 0 ; i < 100 ; i++) {
            grammarNameGenerator.generateName(randomInRange, i % 6, 'S');
        }
    }

    @Test
    public void longGeneration() {
        final IntUnaryOperator randomInRange = ThreadLocalRandom.current()::nextInt;

        // Setup & Test the Generator:
        final GrammarNameGenerator grammarNameGenerator = new GrammarNameGenerator(RULES);

        for (int i = 0 ; i < 100 ; i++) {
            System.out.println(grammarNameGenerator.generateName(randomInRange, i % 6, 'S'));
        }

        for (int i = 0 ; i < 1_000_000 ; i++) {
            grammarNameGenerator.generateName(randomInRange, i % 6, 'S');
        }
    }
}
