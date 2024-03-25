![Java CI with Maven](https://github.com/Valkryst/VNameGenerator/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master)

As this project's inception, the included algorithms are the only ones my research has turned up. If you know of
any other _unique_ algorithms for name generation, then please let me know. I would love to implement them.

## Table of Contents

* [Installation](https://github.com/Valkryst/VNameGenerator#installation)
	* [Gradle](https://github.com/Valkryst/VNameGenerator#-gradle)
	* [Maven](https://github.com/Valkryst/VNameGenerator#-maven)
	* [sbt](https://github.com/Valkryst/VNameGenerator#-scala-sbt)
* [Algorithms](https://github.com/Valkryst/VNameGenerator#algorithms)
    * [Combinatorial](https://github.com/Valkryst/VNameGenerator#combinatorial)
	* [Consonant Vowel](https://github.com/Valkryst/VNameGenerator#consonant-vowel)
	* [Context Free Grammar](https://github.com/Valkryst/VNameGenerator#context-free-grammar)
	* [Markov Chain](https://github.com/Valkryst/VNameGenerator#markov-chain)

## Installation

VNameGenerator is hosted on the [JitPack package repository](https://jitpack.io/#Valkryst/VNameGenerator)
which supports Gradle, Maven, and sbt.

### ![Gradle](https://i.imgur.com/qtc6bXq.png?1) Gradle

Add JitPack to your `build.gradle` at the end of repositories.

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Add VNameGenerator as a dependency.

```
dependencies {
	implementation 'com.github.Valkryst:VNameGenerator:2024.03.25'
}
```

### ![Maven](https://i.imgur.com/2TZzobp.png?1) Maven

Add JitPack as a repository.

``` xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
Add VNameGenerator as a dependency.

```xml
<dependency>
    <groupId>com.github.Valkryst</groupId>
    <artifactId>VNameGenerator</artifactId>
    <version>2024.03.25</version>
</dependency>
```

### ![Scala SBT](https://i.imgur.com/Nqv3mVd.png?1) Scala SBT

Add JitPack as a resolver.

```
resolvers += "jitpack" at "https://jitpack.io"
```

Add VNameGenerator as a dependency.

```
libraryDependencies += "com.github.Valkryst" % "VNameGenerator" % "2024.03.25"
```

## Algorithms

The _Combinatorial_, _Consonant Vowel_, and _Markov Chain_ algorithms will
generate names with a length of `(maxLength * 0.5)` to `maxLength`. This was
done to improve the quality of the generated names.

Names are guaranteed to begin with a capital character.

### Combinatorial
1. A beginning is chosen and added to the name.
2. While the name is less than the maximum length, middles are chosen and added
   to the name.
3. An ending is chosen and added to the end of the name, overwriting existing
   characters in order to fit within the maximum length.

```java
public class Example {
    public static void main(final String[] args) {
        final var beginnings = new String[] { "th", "bo", "ja", "fu" };
        final var middles = new String[] { "la", "su", "dhu", "li", "da", "zk", "fr"};
        final var endings = new String[] { "r", "t", "gh", "or", "al", "ar", "is" };

        final var generator = new CombinatorialGenerator(beginnings, middles, endings);

        for (int i = 0 ; i < 20 ; i++) {
            System.out.println(generator.generate(10));
        }
    }
}
```
```
Thlifrdhgh
Bolalar
Fusuladagh
Thlis
Thlagh
Thlilifis
Jadasugh
Fuzklr
Jadhular
Jadaal
Bosulaligh
Jafrgh
Jadar
Bodhugh
Bolazkr
Thlidadis
Fudhr
Thzklaar
Jazklidgh
Bozkr
```

### Consonant Vowel
1. A consonant is chosen and added to the name.
2. A vowel is chosen and added to the name.
3. Repeat the previous steps until the name is equal to the maximum length.

```java
public class Example {
    public static void main(final String[] args) {
        final var generator = new ConsonantVowelGenerator();

        for (int i = 0 ; i < 20 ; i++) {
            System.out.println(generator.generate(10));
        }
    }
}
```
```
Itleanas
Netemete
Auieie
Stvetoerit
Ataseander
Ouitha
Auyuyieyoe
Arhaea
Aauuiaui
Thatatea
Seenesor
Itstisme
Titire
Eouuyuoo
Leteisha
Ayueea
Waatanto
Eoyouo
Eeoyieuuui
Haontiseal
```

### Context Free Grammar
Click [here](http://www.tutorialspoint.com/automata_theory/context_free_grammar_introduction.htm)
to learn more about CFGs and how they work.

I do not recommend using this method as it is difficult to create a set of rules
that results in good quality names, and a large variety of names.

```java
public class Example {
    public static void main(final String[] args) {
    	/*
    	 * This set of rules was created using the following set of names.
    	 *
    	 * Balin, Bifur, Bofur, Bombur, Borin, Dain, Dis, Dori, Dwalin, Farin,
    	 * Fili,  Floi, Frar, Frerin, Fror, Fundin, Gaiml, Gimli, Gloin, Groin,
    	 * Gror, Ibun, Khim, Kili, Loni, Mim, Nain, Nali, Nar, Narvi, Nori, Oin,
    	 * Ori, Telchar, Thorin, Thrain, Thror
    	 */
        final List<String> rules = new ArrayList<>();
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

        final var generator = new GrammarGenerator(rules);

        final int maxLength = 10;
        String temp;
        for (int i = 0 ; i < 20 ; i++) {
			do {
				temp = generator.generate(10);
			} while (temp.length() < (maxLength / 2));
			
			System.out.println(temp);
        }
    }
}
```
```
Ororv
Dwarv
Naloro
Ororo
Grori
Daloi
Narori
Nalorv
Noroi
Terororoi
Dunai
Flori
Glori
Thalo
Funal
Baloroi
Khari
Ororv
Thalor
Dwari
```

### Markov Chain
Click [here](https://en.wikipedia.org/wiki/Markov_chain) to learn more about
Markov Chains and how they work.

I recommend using this method with a large set of training names. Smaller sets
will result in the generation of many similar names, whereas larger sets will
result in more unique and varied names.

```java
public class Example {
	public static void main(final String[] args) {
		final String[] trainingNames = new String[] {
			"ailios", "ailisl", "aimil", "aingealag", "anabla", "anna",
			"aoife", "barabal", "baraball", "barabla", "bearnas", "beasag",
			"beathag", "beileag", "beitidh", "beitiris", "beitris",
			"bhioctoria", "brighde", "brìde", "cairistiòna", "cairistìne",
			"cairistìona", "caitir", "caitlin", "caitrìona", "calaminag",
			"catrìona", "ceana", "ceit", "ceiteag", "ceitidh", "ciorsdan",
			"ciorstag", "ciorstaidh", "ciorstan", "cotrìona", "criosaidh",
			"curstag", "curstaidh", "deirdre", "deòiridh", "deònaidh",
			"dior-bhorgàil", "diorbhail", "doileag", "doilidh", "doirin",
			"dolag", "ealasaid", "eamhair", "eilidh", "eimhir", "eiric",
			"eithrig", "eubh", "eubha", "èibhlin", "fionnaghal", "fionnuala",
			"floireans", "flòraidh", "frangag", "giorsail", "giorsal",
			"gormall", "gormlaith", "isbeil", "iseabail", "iseabal",
			"leagsaidh", "leitis", "lili", "liùsaidh", "lucrais", "lìosa",
			"magaidh", "maighread", "mairead", "mairearad", "malamhìn",
			"malmhìn", "marsail", "marsaili", "marta", "milread", "moibeal",
			"moire", "moireach", "muire", "muireall", "màili", "màiri",
			"mòr", "mòrag", "nansaidh", "oighrig", "olibhia", "peanaidh",
			"peigi", "raghnaid", "raodhailt", "raonaid", "raonaild", "rut",
			"seasaìdh", "seonag", "seònaid", "simeag", "siubhan", "siùsaidh",
			"siùsan", "sorcha", "stineag", "sìle", "sìleas", "sìlis", "sìne",
			"sìneag", "sìonag", "teasag", "teàrlag", "ùna", "una"
		};

		final MarkovGenerator generator = new MarkovGenerator(trainingNames);

		for (int i = 0 ; i < 20 ; i++) {
			System.out.println(generator.generate(10));
		}
	}
}
```
```
Sorsag
Iria
Unabarst
Nualasana
Tirdreal
Craoilisl
Nearaidha
Lrealairea
Nuala
Almhalamh
Reabarnaig
Ireag
Geabl
Abara
Unaba
Ighang
Beitrìd
Ciorcha
Caimeabal
Mhailil
```
