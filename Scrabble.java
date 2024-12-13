import java.util.Scanner;

public class Scrabble {

    private static final int HAND_SIZE = 7;
    private MyString hand;

    public Scrabble(String hand) {
        this.hand = new MyString(hand);
    }

    // Calculates the score of a given word based on letter values
    public int wordScore(String word) {
        int score = 0;
        for (char c : word.toCharArray()) {
            score += Character.toLowerCase(c) - 'a' + 1;
        }
        // Bonus points for words containing "runi"
        if (word.contains("runi")) {
            score += 1000;
        }
        // Bonus points for using all letters in the hand
        if (word.length() == HAND_SIZE) {
            score += 50;
        }
        return score;
    }

    // Plays a Scrabble hand, prompting the user for input
    public void playHand() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Displays the current hand to the user
            System.out.println("Your hand: " + hand);
            System.out.print("Enter a word (or '.' to quit): ");
            String word = scanner.nextLine();

            // Exits the game loop if the user enters '.'
            if (".".equals(word)) {
                break;
            }

            // Validates that the word can be formed with the current hand
            MyString wordStr = new MyString(word);
            if (!wordStr.subsetOf(hand)) {
                System.out.println("Invalid word: Not all letters are in your hand.");
                continue;
            }

            // Calculates and displays the score for the word
            int score = wordScore(word);
            System.out.println("Word score: " + score);
            hand.remove(wordStr);

            // Checks if the hand is empty after the word is used
            if (hand.toString().isEmpty()) {
                System.out.println("Congratulations! You've used all the letters in your hand.");
                break;
            }
        }
        scanner.close();
    }

    // Main method to start the game
    public static void main(String[] args) {
        Scrabble scrabble = new Scrabble("runiabc");
        scrabble.playHand();
    }
}