package com.valkryst.generator;

import com.valkryst.builder.MarkovNameGeneratorBuilder;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

public class MarkovNameGeneratorTest {
    @Test
    public void generateName() {
        try {
            final Random random = new Random(System.currentTimeMillis());

            final MarkovNameGeneratorBuilder builder = new MarkovNameGeneratorBuilder();
            builder.setTrainingNames(Paths.get("C:\\Users\\Valkryst\\Google Drive\\Development\\Programming\\Java\\VNameGenerator\\res\\Scottish_Gaelic\\Human_Scottish_Gaelic_Female.txt"));

            final MarkovNameGenerator nameGenerator = builder.build();

            for(int i = 0 ; i < 50 ; i++) {
                System.out.println(nameGenerator.generateName(random, 7));
            }

            for(int i = 0 ; i < 10000 ; i++) {
                nameGenerator.generateName(random, i % 50);
            }
        } catch(final IOException e) {
            e.printStackTrace();
        }
    }
}
