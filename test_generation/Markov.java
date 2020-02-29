import com.valkryst.VNameGenerator.generator.MarkovGenerator;
import com.valkryst.VNameGenerator.generator.NameGenerator;

import java.io.IOException;

public class Markov {
    public static void main(String[] args) throws IOException {
        final String[] trainingNames = NameGenerator.loadLinesFromJar("Human/Viking/Male.txt");

        final MarkovGenerator generator = new MarkovGenerator(trainingNames);

        for (int i = 0 ; i < 20 ; i++) {
            System.out.println(generator.generateName(5));
        }
    }
}
