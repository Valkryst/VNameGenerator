#### License: 

Use this code however you wish. Modifications, improvements, additional lists of names, and new algorithms are welcome.

Please refer to the LICENSE file for additional information.

---

## How to Setup:

1. Clone the project from GitHub.
2. Open your project in IntelliJ.
3. Open the *Project Structure* menu. (S + ALT + CTRL + SHIFT)
4. Enter the *Modules* subsection.
5. Click the green "+" at the top of the window.
6. Select *Import Module*.
7. Navigate to the cloned project and double-click on *pom.xml*.
8. Click on the name of your project in the *Modules* subsection.
9. Enter the *Dependencies* tab of your project.
10. Click on the green "+" at the right-edge of the window.
11. Click *Module Dependency...*.
12. Select *VNameGenerator*.

You can now use the VNameGenerator in your project.

---

## How to Use:

Examples of each algorithm in-use can be found within the */test/com/valkryst/generator/* folder. These tests aren't
documented, but should be simple to understand after reading the JavaDoc documentation within the generator classes
located in the */src/com/valkryst/generator/* folder.

Lists of names to use with the generators can be found within the */res/* folder.

---

## Included Algorithms:

### Combinatorial:
Names are generated using a set of beginnings, optional set of middles, and a set of endings.

### Consonant-Vowel:
Names are generated using a set of consonants and vowels. A name begins with a vowel which is followed by a consonant
which is followed by a vowel... and so on, until the name is generated.

### Context Free Grammar:
Names are generated using a very crude context free grammar method. 

You can learn more about CFGs [here](http://www.tutorialspoint.com/automata_theory/context_free_grammar_introduction.htm).

An example of how this is used can be found within the */test/com/valkryst/generator/* folder.

### Markov Chain:
Names are generated using a computed table of probabilities to determine which character follows the previous two
characters.

You can learn more about Markov Chains [here](https://en.wikipedia.org/wiki/Markov_chain).

---

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
