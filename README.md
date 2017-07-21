#### License: 

Use this code however you wish. Modifications, improvements, additional lists of names, and new algorithms are welcome.

Please refer to the LICENSE file for additional information.

---

[![MIT License](https://img.shields.io/badge/license-MIT_License-green.svg)](https://github.com/Valkryst/VNameGenerator/blob/master/LICENSE) ![](https://travis-ci.org/Valkryst/VNameGenerator.svg?branch=master) [![codecov](https://codecov.io/gh/Valkryst/VNameGenerator/branch/master/graph/badge.svg)](https://codecov.io/gh/Valkryst/VNameGenerator) [![Release](https://jitpack.io/v/Valkryst/VNameGenerator.svg)](https://jitpack.io/#Valkryst/VNameGenerator)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/d62947885d774bb48eb0618f8e5f4c54)](https://www.codacy.com/app/Valkryst/VNameGenerator?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Valkryst/VNameGenerator&amp;utm_campaign=Badge_Grade)

## Jar Files & Maven

To use this project as a Maven dependency, click on the JitPack badge [![Release](https://jitpack.io/v/Valkryst/VNameGenerator.svg)](https://jitpack.io/#Valkryst/VNameGenerator), select a version, click the "Get it!" button, and then follow the instructions.

If you would rather use a Jar file, then you can find the Jars on the [releases](https://github.com/Valkryst/VNameGenerator/releases) page.

## How to Use:

Examples of each algorithm in-use can be found within the */test/com/valkryst/generator/* folder. These tests aren't
documented, but should be simple to understand after reading the JavaDoc documentation within the generator classes
located in the */src/com/valkryst/generator/* folder.

Lists of names to use with the generators can be found within the */res/* folder.

## JavaDoc Documentation:

Whenever a Travis CI build passes, the JavaDocs are auto-generated and made available at the following link.

https://valkryst.github.io/VNameGenerator/

## Included Algorithms:

### Combinatorial:
Names are generated using a set of beginnings, optional set of middles, and a set of endings.

### Consonant-Vowel:
Names are generated using a set of consonants and vowels. A name begins with a vowel which is followed by a consonant
which is followed by a vowel... and so on, until the name is generated.

### Context Free Grammar:
Names are generated using a context free grammar method. 

You can learn more about CFGs [here](http://www.tutorialspoint.com/automata_theory/context_free_grammar_introduction.htm).

An example of how this is used can be found within the */test/com/valkryst/generator/* folder.

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
