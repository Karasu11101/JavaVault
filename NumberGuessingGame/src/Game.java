import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game implements Random {

    int max = 20;
    int min = 1;
    int range = max - min + 1;
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    int counter = 3;
    public Score playerScore = new Score();


    public void showCommands() { // only displays the menu options
        System.out.println("Select a command from the list:" +
                "\t 1. Start game" +
                "\t 2. Show score" +
                "\t 3. Show commands" +
                "\t 4. Quit");
    }

    public void interactiveMenu() throws IOException { // calls on showCommands() to display menu options and let the user choose one
        System.out.println("Welcome to Guess a Number!");
        System.out.println();

        while (true) {
            showCommands();
            int option = Integer.parseInt(bufferedReader.readLine());
            switch (option) {
                case 1 -> guessNumber();
                case 2 -> showScore();
                case 3 -> showCommands();
                case 4 -> {
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                }
                default -> System.out.println("Unknown command: please select one from the given list");
            }
        }
    }


    @Override
    public int genRandomNum() { // generates a random number within the range given by max and min fields
        return (int) (Math.random() * range) + min;
    }

    public void guessNumber() throws IOException, NumberFormatException { // calls on genRandomNum() to generate a random number, then let the user input their guess
        counter = 3;
        int random = genRandomNum();
        while (true) {
            if (counter == 0) {
                retry();
            }
            System.out.println("Pick a number from " + min + " to " + max + " and try your luck: ");
            try {
                int input = Integer.parseInt(bufferedReader.readLine());
                System.out.println();
                if (input > max || input < min) {
                    System.out.println("Please, only enter a number within the given range\n");
                    continue;
                }
                if (input == random) {
                    System.out.println("Well done, you guessed right!");
                    playerScore.increasePlayerScore();
                    retry();
                    break;
                } else {
                    counter -= 1;
                    System.out.println("Wrong answer! You still have " + counter + " tries\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Only numbers are accepted");
            }
        }
    }
    private void retry() throws IOException { // called in guessNumber() when the player has no tries remaining, or when they guess the number right and want to play again
        System.out.println("Would you like to play another round? Enter 'Y' to play again, 'N' to quit the game, or 'B' to go back to the menu");
        while (true) {
            String input2 = bufferedReader.readLine().toLowerCase();
            switch(input2) {
                case "y" -> guessNumber();
                case "n" -> {System.out.println("Your overall score is " + playerScore.getScore() + ". Thanks for playing!");
                    System.exit(0);}
                case "b" -> interactiveMenu();
                default -> System.out.println("Input 'Y' to play again, 'N' to quit the game");
            }
        }
    }

    private void showScore() {
        System.out.println("You scored " + playerScore.getScore() + " point(s) so far!");
    }
}
