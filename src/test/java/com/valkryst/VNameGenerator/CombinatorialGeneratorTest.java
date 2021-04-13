package com.valkryst.VNameGenerator;

import com.valkryst.VNameGenerator.generator.CombinatorialGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

public class CombinatorialGeneratorTest {
    private final static String[] BEGINNINGS = new String[] { "a", "b", "c" };
    private final static String[] MIDDLES = new String[] { "d", "e", "f" };
    private final static String[] ENDINGS = new String[] { "g", "h", "i" };

    private final CombinatorialGenerator generator = new CombinatorialGenerator(BEGINNINGS, MIDDLES, ENDINGS);

    @Test
	public void canConstructWithBeginningsAndEndings() throws NoSuchFieldException, IllegalAccessException {
    	final var generator = new CombinatorialGenerator(BEGINNINGS, ENDINGS);

    	var field = generator.getClass().getDeclaredField("beginnings");
		field.setAccessible(true);
		Assertions.assertArrayEquals(BEGINNINGS, (String[]) field.get(generator));

		field = generator.getClass().getDeclaredField("endings");
		field.setAccessible(true);
		Assertions.assertArrayEquals(ENDINGS, (String[]) field.get(generator));
	}

	@Test
	public void canConstructWithBeginningsMiddlesAndEndings() throws NoSuchFieldException, IllegalAccessException {
		final var generator = new CombinatorialGenerator(BEGINNINGS, MIDDLES, ENDINGS);

		var field = generator.getClass().getDeclaredField("beginnings");
		field.setAccessible(true);
		Assertions.assertArrayEquals(BEGINNINGS, (String[]) field.get(generator));

		field = generator.getClass().getDeclaredField("middles");
		field.setAccessible(true);
		Assertions.assertArrayEquals(MIDDLES, (String[]) field.get(generator));

		field = generator.getClass().getDeclaredField("endings");
		field.setAccessible(true);
		Assertions.assertArrayEquals(ENDINGS, (String[]) field.get(generator));
	}

	@Test
	public void canConstructWithNullMiddles() throws NoSuchFieldException, IllegalAccessException {
		final var generator = new CombinatorialGenerator(BEGINNINGS, null, ENDINGS);

		var field = generator.getClass().getDeclaredField("beginnings");
		field.setAccessible(true);
		Assertions.assertArrayEquals(BEGINNINGS, (String[]) field.get(generator));

		field = generator.getClass().getDeclaredField("middles");
		field.setAccessible(true);
		Assertions.assertEquals(0, ((String[]) field.get(generator)).length);

		field = generator.getClass().getDeclaredField("endings");
		field.setAccessible(true);
		Assertions.assertArrayEquals(ENDINGS, (String[]) field.get(generator));
	}

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
	public void canSetBeginnings() throws NoSuchFieldException, IllegalAccessException {
		final String[] beginnings = new String[] { "z" };
		generator.setBeginnings(beginnings);

		final var field = generator.getClass().getDeclaredField("beginnings");
		field.setAccessible(true);
		Assertions.assertArrayEquals(beginnings, (String[]) field.get(generator));
	}

	@Test
	public void canSetMiddles() throws NoSuchFieldException, IllegalAccessException {
		final String[] middles = new String[] { "z" };
		generator.setMiddles(middles);

		final var field = generator.getClass().getDeclaredField("middles");
		field.setAccessible(true);
		Assertions.assertArrayEquals(middles, (String[]) field.get(generator));
	}

	@Test
	public void canSetMiddlesWithNull() throws NoSuchFieldException, IllegalAccessException {
		final var field = generator.getClass().getDeclaredField("middles");
		field.setAccessible(true);
		Assertions.assertNotEquals(0, ((String[]) field.get(generator)).length);

		generator.setMiddles(null);
		Assertions.assertEquals(0, ((String[]) field.get(generator)).length);
	}

	@Test
	public void canSetEndings() throws NoSuchFieldException, IllegalAccessException {
		final String[] endings = new String[] { "z" };
		generator.setEndings(endings);

		final var field = generator.getClass().getDeclaredField("endings");
		field.setAccessible(true);
		Assertions.assertArrayEquals(endings, (String[]) field.get(generator));
	}
}
