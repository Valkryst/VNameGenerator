package com.valkryst.VNameGenerator;

import com.valkryst.VNameGenerator.generator.ConsonantVowelGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

public class ConsonantVowelGeneratorTest {
	private final ConsonantVowelGenerator generator = new ConsonantVowelGenerator();

	@Test
	public void canGenerateName() {
		final var result = generator.generate(10).length();
		Assertions.assertTrue(result <= 10);
		Assertions.assertTrue(result > 0);
	}

	@Test
	public void canGenerateNameWithArbitraryLength() {
		final var threadLocalRandom = ThreadLocalRandom.current();

		for (int i = 0 ; i < 4 ; i++) {
			final int maxLength = threadLocalRandom.nextInt(1, 75);
			final var result = generator.generate(maxLength).length();
			Assertions.assertTrue(result <= maxLength);
			Assertions.assertTrue(result > 0);
		}
	}

    @Test
    public void cannotGenerateNameWithZeroAsMaxLength() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			generator.generate(0);
		});
    }

	@Test
	public void canSetConsonants() throws NoSuchFieldException, IllegalAccessException {
		final String[] consonants = new String[] { "z" };
		generator.setConsonants(consonants);

		final var field = generator.getClass().getDeclaredField("consonants");
		field.setAccessible(true);

		Assertions.assertArrayEquals(consonants, (String[]) field.get(generator));
	}

	@Test
	public void canSetVowels() throws NoSuchFieldException, IllegalAccessException {
		final String[] vowels = new String[] { "z" };
		generator.setVowels(vowels);

		final var field = generator.getClass().getDeclaredField("vowels");
		field.setAccessible(true);

		Assertions.assertArrayEquals(vowels, (String[]) field.get(generator));
	}
}
