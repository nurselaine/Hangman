import java.util.Scanner;
import java.util.Random;

public class Hangman {
    public static void main(String[] args) {
        // create a word bank
        String [] wordBankArray = {"asteroid", "astronaut", "astronomer", "astronomical", "astronomy", "binary star", "black body", "black hole",
                "cislunar", "cluster", "comet", "conjunction", "constellation", "Coriolis force", "corona", "cosmic", "dark matter", "day", "declination",
                "deep space", "Deneb", "density", "Earth", "earthbound", "eccentricity", "eclipse", "ecliptic", "falling star", "galaxy", "gravity", "heliocentric", "helium",
                "hydrogen", "hyperbolic orbit", "hypernova", "interstellar", "interstellar dust", "ionosphere", "Jupiter", "lunar", "magnitude", "mare", "Mars", "Mercury",
                "meteor", "meteorite", "Milky Way", "NASA", "nebula", "Neptune", "neutron",  "star", "observatory", "planet", "planetary nebula", "planetoid", "Pluto",
                "pole star", "precession", "probe", "pulsar", "quarter moon", "quasar", "radiant", "radiation", "red dwarf", "red giant star", "rocket", "satellite",
                "Saturn", "scientific notation", "sky", "solar", "solar system", "star", "starlight", "sun", "sunspot", "superior planet", "supernova", "telemetry", "telescope",
                "terminator", "terrestrial", "total eclipse", "umbra", "universe", "Uranus", "white dwarf", "white giant", "wormhole", "x-rays", "yellow dwarf", "zenith"};

        Scanner console = new Scanner(System.in);
        Random rand = new Random();

        String correctLetters = "";
        String userGuessString = "";
        // keep track of wrong & right guesses
        int wrongGuesses = 0;

        // pick a random word
        String randomWord = randomWord(wordBankArray, rand); // chooses a random word from word array
        char [] randomWordArray = new char[randomWord.length()]; // make array for random word
        randomWordArray = randomWord.toCharArray();

        // spells out mystery word with _'s
        String generateSpaces = generateSpaces(randomWordArray);
        System.out.println(generateSpaces);
        char[] generateSpacesArray = new char[randomWordArray.length]; // convert generateSpaces to array
        generateSpacesArray = generateSpaces.toCharArray();

        // use a while loop to repeat until word guessed
        int numberOfGuesses = 0;
        while (numberOfGuesses < randomWord.length() + 7) { // letters in word + 7 hangman guesses

            // prompts user for guess and scans input, returns char
            char userGuess = userGuess(console);
            numberOfGuesses++;

            // store all user guesses in array
            char[] userGuessArray = new char[26]; //26 possible letters to guess
            userGuessString = userGuessString(userGuessString, userGuess);
            int guessCount = 0;

            // compare guess with userWordArray and returns true/false
            boolean correctGuess = correctGuess(randomWordArray, generateSpacesArray, userGuess); //
            if (correctGuess) {
                System.out.println("Your guess '" + userGuess + "' is correct");
            } else {
                System.out.println("Your guess '" + userGuess + "' is incorrect. Try again. ");
                wrongGuesses++;
            }
            // print hangman figure
            String hangMan = "";
            hangMan = hangMan(correctGuess, hangMan, wrongGuesses);
            System.out.println(hangMan);

            // print updated _'s with correct letters
            String generateLetterString = generateLetterSpaces(generateSpacesArray, randomWordArray, userGuess);
            System.out.print(generateLetterString);

            // print guess #
            System.out.println(" Guesses #: " + numberOfGuesses);
            System.out.println("Guesses: " + userGuessString);

            // end game
            if (generateLetterString.equals(randomWord)){ // end while loop if word is guessed
                System.out.print("You Win!");
                numberOfGuesses = randomWord.length() + 7;
            }
            if (wrongGuesses == 7){
                System.out.print("You ran out of Guesses.");
                numberOfGuesses = randomWord.length() + 7;
            }


        }
    }

    // method that picks a random word from wordBankArray
    public static String randomWord(String[] wordBankArray, Random rand){
        String randomWord = "";

        // pick a random word from word bank
        int randWordBank = rand.nextInt(wordBankArray.length);
        randomWord = wordBankArray[randWordBank];
        return randomWord;

    }

    // method to find the length of userWord and return length using "_"
    public static String generateSpaces(char[] randomWordArray){
        String generateSpaces = "";
        char space = ' ';
        for (int i = 0; i < randomWordArray.length; i++){
            generateSpaces += "_";
            // create a " " when needed
            if(randomWordArray[i] == space){
                generateSpaces += " ";
            }
        }
        return generateSpaces;
    }

    // method to prompt user to guess letter and returns array
    public static char userGuess(Scanner console){
        System.out.print("Pick a letter from the alphabet. ");
        char userGuess = console.next().charAt(0);

        return userGuess;
    }

    // method to store all user guesses in an array
    public static String userGuessString(String userGuessString, char userGuess){
        userGuessString = userGuessString + " " + userGuess + " ";

        return userGuessString;
    }

    // method that returns correct guess or prompt user to try again
    public static boolean correctGuess(char [] randomWordArray, char[] generateSpacesArray,
                                       char userGuess){
        boolean correctGuess = false;
        for(int i = 0; i < randomWordArray.length; i++){
            if(randomWordArray[i] == userGuess){ // Character.compare(userWordArray[i], userGuess)
                correctGuess = true;

            }
        }
        return correctGuess;
    }

    // update generate spaces array with correct guesses
    public static String generateLetterSpaces (char[] generateSpacesArray, char[] randomWordArray, char userGuess){
        String generateSpacesString = "";
        // loop through elements in
        for(int i = 0; i < randomWordArray.length; i++){
            if (randomWordArray[i] == userGuess){
                generateSpacesArray[i] = userGuess;
            }
        }
        // convert char[] to string
        generateSpacesString = new String(generateSpacesArray);

        return generateSpacesString;
    }



    // draw a stickman whenever correctGuess is incorrect & count increases)
    public static String hangMan(boolean correctGuess, String hangMan, int wrongGuesses){

        // if(correctGuess == false){
        int i = wrongGuesses;

        if(i == 0){
            hangMan = "___"+ "\n|\n|\n|\n|_____"; // build stand
        } else if (i == 1){
            hangMan = "___"+ "\n|"+ "  o " + "\n|\n|\n|_____"; // build head
        } else if (i == 2){
            hangMan = "___"+ "\n|" + "  o "+ "\n|  |" + "\n|\n|_____"; // build body
        } else if (i == 3){
            hangMan = "___"+ "\n|" + "  o " + "\n|  |" + "\n|  |" + "\n|_____"; // build body
        } else if (i == 4){
            hangMan =  "___"+ "\n|" + "  o "+ "\n| /|"+ "\n|  |" + "\n|_____"; // build L arm
        } else if(i == 5){
            hangMan = "___"+ "\n|" + "  o "+ "\n| /|\\"+ "\n|  |" + "\n|_____"; // build R arm
        } else if(i == 6){
            hangMan = "___"+ "\n|" + "  o " + "\n| /|\\" + "\n|  |" + "\n| /" + "\n|_____"; // build L leg
        } else if(i == 7){
            hangMan = "___"+ "\n|" + "  o " + "\n| /|\\" + "\n|  |" + "\n| / \\" + "\n|game over" + "\n_____";
        }
        return hangMan;
    }
}
