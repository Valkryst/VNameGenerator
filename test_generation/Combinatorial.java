import com.valkryst.VNameGenerator.generator.CombinatorialGenerator;
import com.valkryst.VNameGenerator.generator.NameGenerator;

import java.io.IOException;

public class Combinatorial {
    public static void main(String[] args) throws IOException {
        final String[] beginnings = NameGenerator.loadLinesFromJar("Titles/Dune/Titles.txt");
        final String[] middles = NameGenerator.loadLinesFromJar("Dwarven/Fantasy/Khordaldrum_Last_A.txt");
        final String[] endings = NameGenerator.loadLinesFromJar("Dwarven/Fantasy/Khordaldrum_Last_B.txt");

        final CombinatorialGenerator generator = new CombinatorialGenerator(beginnings, middles, endings);

        for (int i = 0 ; i < 20 ; i++) {
            System.out.println(generator.generateName(10));
        }
    }
}
