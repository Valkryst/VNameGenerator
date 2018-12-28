import com.valkryst.generator.MarkovGenerator;
import com.valkryst.generator.NameGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Markov {
    public static void main(String[] args) throws IOException {
        final String[] trainingNames = NameGenerator.loadLinesFromJar("Human/Viking/Male.txt");

        final MarkovGenerator generator = new MarkovGenerator(trainingNames);

        for (int i = 0 ; i < 20 ; i++) {
            System.out.println(generator.generateName(5));
        }
    }
}
