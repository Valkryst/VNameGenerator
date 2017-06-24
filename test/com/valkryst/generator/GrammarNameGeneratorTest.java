package com.valkryst.generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GrammarNameGeneratorTest {
    private final List<String> rules = new ArrayList<>();
    private GrammarNameGenerator grammarNameGenerator;


    @Before
    public void initalizerules() {
        // Balin, Bifur, Bofur, Bombur, Borin
        // Dain, Dis, Dori, Dwalin, Farin, Fili
        // Floi, Frar, Frerin, Fror, Fundin, Gaiml
        // Gimli, Gloin, Groin, Gror, Ibun, Khim
        // Kili, Loni, Mim, Nain, Nali, Nar, Narvi
        // Nori, Oin, Ori, Telchar, Thorin, Thrain
        // Thror,
        rules.clear();
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

        grammarNameGenerator = new GrammarNameGenerator(rules);
    }

    @Test
    public void testConstructor_withValidRules() {
        grammarNameGenerator = new GrammarNameGenerator(rules);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withNullRules() {
        grammarNameGenerator = new GrammarNameGenerator(null);
    }

    @Test
    public void testGenerateName_withZeroLength() {
        final String result = grammarNameGenerator.generateName(0);
        Assert.assertEquals(2, result.length());
    }

    @Test
    public void  testGenerateName_withOneLength() {
        final String result = grammarNameGenerator.generateName(1);
        Assert.assertEquals(2, result.length());
    }

    @Test
    public void  testGenerateName_withTwoLength() {
        final String result = grammarNameGenerator.generateName(2);
        Assert.assertEquals(2, result.length());
    }

    @Test
    public void  testGenerateName_withThreeToTwentyLength() {
        for (int i = 3 ; i < 20 ; i++) {
            final String result = grammarNameGenerator.generateName(i);
            Assert.assertTrue(result.length() <= i);
            Assert.assertTrue(result.length() > 0);
        }
    }
}
