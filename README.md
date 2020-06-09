[![CircleCI](https://circleci.com/gh/Valkryst/VNameGenerator.svg?style=svg)](https://circleci.com/gh/Valkryst/VNameGenerator)
[![Release](https://jitpack.io/v/Valkryst/VNameGenerator.svg)](https://jitpack.io/#Valkryst/VNameGenerator)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/Valkryst/VNameGenerator.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/Valkryst/VNameGenerator/alerts/)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/Valkryst/VNameGenerator.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/Valkryst/VNameGenerator/context:java)

# Jar Files & Maven

The Maven dependency is hosted off of JitPack, so you will need to add JitPack as a repository before you add VNameGenerator as a dependency.

### Maven

JitPack ([Example](https://github.com/Valkryst/VNameGenerator/blob/master/pom.xml)):

    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>

Dependency:

    <dependency>
        <groupId>com.github.Valkryst</groupId>
        <artifactId>VNameGenerator</artifactId>
        <version>2.0.0</version>
    </dependency>

### Jar

Jar files can be found on the [releases](https://github.com/Valkryst/VNameGenerator/releases) page.

## How to Use:

Examples of each algorithm in-use can be found within the *test_generation* folder. These tests aren't
documented, but should be simple to understand after reading the JavaDoc documentation within the generator classes
located in the *test_generation* folder.

Lists of names to use with the generators can be found within the *src/main/resources* folder.

## Included Algorithms:

### Combinatorial:
Names are generated using a set of beginnings, optional set of middles, and a set of endings.

### Consonant-Vowel:
Names are generated using a set of consonants and vowels. A name begins with a vowel which is followed by a consonant
which is followed by a vowel... and so on, until the name is generated.

### Context Free Grammar:
Names are generated using a context free grammar method. 

You can learn more about CFGs [here](http://www.tutorialspoint.com/automata_theory/context_free_grammar_introduction.htm).

An example of how this is used can be found within the *test_generation* folder.

### Markov Chain:
Names are generated using a computed table of probabilities to determine which character follows the previous two
characters.

You can learn more about Markov Chains [here](https://en.wikipedia.org/wiki/Markov_chain).

## Combinatorial Generator Test Output:
    Ggis Yamadral
    Jalasun Urkest
    Obdl Kloraerg
    Bofnmar Armabadur
    Theobl Buldud
    Rafdan Azalthec
    Taldhudyr Garkapal
    Yrlim Thranduum
    Thauk Klorfal
    Falold Azdul
    Dwl Tudal
    Kaggratbk Gahad
    Droimfr Moramron
    Dogdtvo Lazafal
    Delnndre Darmek
    Fakas Lazaron
    Rtdo Vonbduum
    Baalak Magamak
    Anamic Theivok
    Bazkdrirr Ruknamzak
    Bicaim Palakbal
    Gnlofr Fadan
    Klobyr Argdal
    Ulturt Drunaald
    Fulhor Vonbal
    Vofagh Gursten
    Delnnhor Kazaald
    Bazkral Raondal

## Consonant Vowel Generator Test Output:
    Youyst
    Oseito
    Ahaont
    Eisoes
    Iitowa
    Owaiea
    Ehiere
    Engiha
    Oalaha
    Ahiied

## Grammar Name Generator Test Output:
    Nalor
    Naror
    Khalo
    Dalor
    Noror
    Falor
    Naloi
    Naroi
    Loror
    Faror

## Markov Name Generator Test Output:
    Ìneamhì
    Ibhliùs
    Ibhia
    Iònansa
    Blamhìn
    Ùsaìdei
    Dhalagh
    Lìonnua
    Rgàilil
    Achalai
    Imeanna
    Athansa
    Ìdhalai
    Tistìne
    Muirigh
    Orighri
    Seònana
    Ghdeitl
    Hlidhal
    Àrlamin
    Leigior
    Blaghna
