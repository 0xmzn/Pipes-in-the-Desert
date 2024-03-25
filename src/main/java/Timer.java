import static java.lang.System.out;
import java.util.Scanner;

/**
 * The Timer class represents a timer used in a game. It tracks the time of the
 * game and contributes to flowing the turns in the game based on the time
 * passed.
 */
public class Timer {
    /**
     * Prints the name of the method that is called.
     *
     * @param methodName the name of the method
     */
    private static void printMethodName(String methodName) {
        out.println("\n------------------------------------------------------------");
        out.println(methodName + " method of the Timer class is called.");
        out.println("------------------------------------------------------------\n");
    }

    /**
     * Asks a question and returns the user's answer.
     *
     * @param question the question to ask
     * @param options  the options for the answer
     * @return the user's answer
     */
    private static int askQuestion(String question, String... options) {
        Scanner questionScanner = new Scanner(System.in);

        out.println(question);

        if (options.length == 0) {
            out.println("1. Yes\n2. No");
        } else {
            for (int i = 0; i < options.length; i++) {
                out.println((i + 1) + ". " + options[i]);
            }
        }

        int answer = questionScanner.nextInt();
        if (options.length == 0) {
            if (answer < 1 || answer > 2)
                System.err.println("Invalid values was chosen for the answer!");
        } else {
            if (answer < 1 || answer > options.length) {
                System.err.println("Invalid values was chosen for the answer!");
            }
        }

        return answer;
    }

    /**
     * Starts the timer.
     */
    public static void startTimer() {
        printMethodName("startTimer()");
    }

    /**
     * Checks if the turn of the previous player has expired.
     *
     * @return true if the turn has expired, false otherwise
     */
    public static boolean turnExpired() {
        printMethodName("turnExpired()");

        int answer = askQuestion("Has the turn of the previous player expired?");
        return (answer == 1);
    }
}
