package com.valkryst.generator;

import com.valkryst.TestHelper;
import com.valkryst.builder.CombinatorialNameGeneratorBuilder;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class CombinatorialNameGeneratorTest {
    @Test
    public void generateNameFantasyDwarven() {
        try {
            final Random random = new Random(System.currentTimeMillis());

            final CombinatorialNameGeneratorBuilder builder = new CombinatorialNameGeneratorBuilder();

            builder.setBeginnings(TestHelper.resource("Dwarven/Fantasy/Dwarven_Khordaldrum_Fantasy_Last_A.txt"));
            builder.setEndings(TestHelper.resource("Dwarven/Fantasy/Dwarven_Khordaldrum_Fantasy_Last_B.txt"));

            final CombinatorialNameGenerator nameGenerator = builder.build(false);

            for(int i = 0 ; i < 50 ; i++) {
                System.out.println(nameGenerator.generateName(random,  i, 0));
            }

            for(int i = 0 ; i < 10000 ; i++) {
                nameGenerator.generateName(random, i, 0);
            }
        } catch(final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void generateNameRealisticDwarven() {
        try {
            final Random random = new Random(System.currentTimeMillis());

            final CombinatorialNameGeneratorBuilder builderFirst = new CombinatorialNameGeneratorBuilder();
            builderFirst.setBeginnings(TestHelper.resource("Dwarven/Realistic/Dwarven_Khordaldrum_Realistic_Male_A.txt"));
            builderFirst.setEndings(TestHelper.resource("Dwarven/Realistic/Dwarven_Khordaldrum_Realistic_Male_B.txt"));

            final CombinatorialNameGenerator firstNameGenerator = builderFirst.build(false);


            final CombinatorialNameGeneratorBuilder builderLast = new CombinatorialNameGeneratorBuilder();
            builderLast.setBeginnings(TestHelper.resource("Dwarven/Realistic/Dwarven_Khordaldrum_Realistic_Last_A.txt"));
            builderLast.setEndings(TestHelper.resource("Dwarven/Realistic/Dwarven_Khordaldrum_Realistic_Last_B.txt"));

            final CombinatorialNameGenerator lastNameGenerator = builderLast.build(false);

            for(int i = 0 ; i < 50 ; i++) {
                System.out.println(firstNameGenerator.generateName(random, i, 0) + " " + lastNameGenerator.generateName(random, i, 0));
            }

            for(int i = 0 ; i < 10000 ; i++) {
                firstNameGenerator.generateName(random, i, 0);
                lastNameGenerator.generateName(random, i, 0);
            }
        } catch(final IOException e) {
            e.printStackTrace();
        }
    }
}
