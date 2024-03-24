import static java.lang.System.out;
import java.util.Scanner;

public class Timer {
    private static void printMethodName(String methodName) {
        out.println("\n------------------------------------------------------------");
        out.println(methodName + " method of the Timer class is called.");
        out.println("------------------------------------------------------------\n");
    }

    private static int askQuestion(String question, String... options) {
        Scanner questionScanner = new Scanner(System.in);

        out.println(question);

        if (options.length == 0) {
            out.println("1. Yes\n2. No");
        } else {
            for(int i = 0; i < options.length; i++) {
                out.println((i + 1) + ". " + options[i]);
            }
        }

        int answer = questionScanner.nextInt();
        if (options.length == 0) {
            if (answer < 1 || answer > 2)
                System.err.println("Invalid values was chosen for the answer!");
        } else {
            if(answer < 1 || answer > options.length) {
                System.err.println("Invalid values was chosen for the answer!");
            }
        }

        questionScanner.close();
        
        return answer;
    }

    public static void startTimer() {
        printMethodName("startTimer()");
    }

    public static boolean turnExpired() {
        printMethodName("turnExpired()");

        int answer = askQuestion("Has the turn of the previous player expired?");
        return (answer == 1);
    }
}
