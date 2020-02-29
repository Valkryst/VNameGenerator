import com.valkryst.VNameGenerator.generator.ConsonantVowelGenerator;

public class ConsonantVowel {
    public static void main(String[] args) {
        final String[] consonants = new String[] {
                "al", "an", "ar", "as", "at", "ea", "ed", "en", "er", "es", "ha", "he", "hi", "in", "is", "it",
                "le", "me", "nd", "ne", "ng", "nt", "on", "or", "ou", "re", "se", "st", "te", "th", "ti", "to",
                "ve", "wa", "it"
        };

        final String[] vowels = new String[] {
                "a", "e", "i", "o", "u", "y"
        };

        final ConsonantVowelGenerator generator = new ConsonantVowelGenerator(consonants, vowels);

        for (int i = 0 ; i < 20 ; i++) {
            System.out.println(generator.generateName(5));
        }
    }
}
