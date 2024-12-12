public class Scrabble {

    static final String WORDS_FILE = "dictionary.txt";
    static final int[] SCRABBLE_LETTER_VALUES = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };
    static int HAND_SIZE = 10;
    static int MAX_NUMBER_OF_WORDS = 100000;
    static String[] DICTIONARY = new String[MAX_NUMBER_OF_WORDS];
    static int NUM_OF_WORDS;

    public static void init() {
        In in = new In(WORDS_FILE);
        System.out.println("Loading word list from file...");
        NUM_OF_WORDS = 0;
        while (!in.isEmpty()) {
            DICTIONARY[NUM_OF_WORDS++] = in.readString().toLowerCase();
        }
        System.out.println(NUM_OF_WORDS + " words loaded.");
    }

    public static boolean isWordInDictionary(String word) {
        if (!(word instanceof String) || word.length() == 0) {
            return false;
        }
        for (int i = 0; i < NUM_OF_WORDS; i++) {
            if (word.equals(DICTIONARY[i])) {
                return true;
            }
        }
        return false;
    }

    public static int wordScore(String word) {
        int score = 0;
        for (int i = 0; i < word.length(); i++) {
            int index = (int) word.charAt(i) - 97;
            score += SCRABBLE_LETTER_VALUES[index];
        }
        score *= word.length();
        score = (word.length() == HAND_SIZE) ? score + 50 : score;
        score = (MyString.subsetOf("runi", word)) ? score + 1000 : score;
        return score;
    }

    public static String createHand() {
        String hand = MyString.randomStringOfLetters(HAND_SIZE - 2);
        hand = MyString.insertRandomly('a', hand);
        hand = MyString.insertRandomly('e', hand);
        return hand;
    }

    public static void playHand(String hand) {
        int n = hand.length();
        int score = 0;
        In in = new In();
        while (hand.length() > 0) {
            System.out.println("Current Hand: " + MyString.spacedString(hand));
            System.out.println("Enter a word, or '.' to finish playing this hand:");
            String input = in.readString();

            if (input.equals(".")) {
                break;
            }
            if (MyString.subsetOf(input, hand)) {
                if (isWordInDictionary(input)) {
                    hand = MyString.remove(input, hand);
                    int points = wordScore(input);
                    score += points;
                    System.out.println(input + " earned " + points + " points. Score: " + score + " points");
                } else {
                    System.out.println("No such word in the dictionary. Try again.");
                }
            } else {
                System.out.println("Invalid word. Try again.");
            }
        }
        if (hand.length() == 0) {
            System.out.println("Ran out of letters. Total score: " + score + " points");
        } else {
            System.out.println("End of hand. Total score: " + score + " points");
        }
    }

    public static void playGame() {
        init();
        In in = new In();
        while (true) {
            System.out.println("Enter n to deal a new hand, or e to end the game:");
            String input = in.readString();
            if (input.equals("n")) {
                playHand(createHand());
            }
            if (input.equals("e")) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        playGame();
    }
}
