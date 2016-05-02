package com.valkryst.generator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrammarNameGeneratorTest {
    @Test
    public void generateName() {
        final List<String> rules = new ArrayList<>();
        rules.add("1 2 3 4 5 6");
        rules.add("1 2 3 4 5 6");

        // Balin, Bifur, Bofur, Bombur, Borin
        // Dain, Dis, Dori, Dwalin, Farin, Fili
        // Floi, Frar, Frerin, Fror, Fundin, Gaiml
        // Gimli, Gloin, Groin, Gror, Ibun, Khim
        // Kili, Loni, Mim, Nain, Nali, Nar, Narvi
        // Nori, Oin, Ori, Telchar, Thorin, Thrain
        // Thror,

        rules.add("S B D F G I K L M N O T");
        rules.add("A a aL aI aR");
        rules.add("B b bA bI bO");
        rules.add("C c");
        rules.add("D d dA dI dO dW dU");
        rules.add("E e eR eL");
        rules.add("F f fA fI fL fR fU fO");
        rules.add("G g gA gI gL gR");
        rules.add("H h hI hA");
        rules.add("I i");
        rules.add("K k kH kI");
        rules.add("L l lO");
        rules.add("M m mI");
        rules.add("N n nA nO");
        rules.add("O o oI oR");
        rules.add("P p");
        rules.add("Q q");
        rules.add("R r rI rO rV");
        rules.add("S s");
        rules.add("T t tE tH");
        rules.add("U u uR uN");
        rules.add("V v");
        rules.add("W w wA");
        rules.add("X x");
        rules.add("Y y");
        rules.add("Z z");

        final GrammarNameGenerator grammarNameGenerator = new GrammarNameGenerator(rules);
        final Random random = new Random(System.currentTimeMillis());

        for(int i = 0 ; i < 10 ; i++) {
            System.out.println(grammarNameGenerator.generateName(random, 5, 'S'));
        }
    }
}
