import java.util.Scanner;

public class Game {
    public static void main(String args[]) {
        Hangman game = new Hangman();

        String answer = play();
        while (answer.equals("yes")){
// 2. hangman - method to start hangman
            game.start();
            answer = playAgain(game);
        }

    }
    // 1. ask user what game they want to play (method)\
    public static String play(){
        Scanner console = new Scanner(System.in);
        System.out.println("Do you want to play a game? ");
        System.out.println("Yes or No ");
        String answer = console.nextLine();
        return answer.toLowerCase();
    }
    // 3. prompt user to play again method
    public static String playAgain(Hangman game){
        Scanner console = new Scanner(System.in);

        System.out.println("Do you want to play again?");
        System.out.println("Yes or No ");
        String answer = console.nextLine();
        game.restart();
        return answer.toLowerCase();
    }
}










