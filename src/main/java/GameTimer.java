import static java.lang.System.out;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Timer class represents a timer used in a game. It tracks the time of the
 * game and contributes to flowing the turns in the game based on the time
 * passed.
 */
public class GameTimer {
    /**
     * The constant amount of seconds given for each turn.
     */
    private static final int turnTime = 30;

    private static Timer timer;
    private static TimerTask task;
    private static TimerListener listener;

    /**
     * The TimerListener interface provides a callback method to be called when a
     * turn expires.
     */
    public interface TimerListener {
        void onTurnExpired();
    }

    /**
     * Sets the listener to be notified when a turn expires.
     *
     * @param timerListener the listener to be set
     */
    public static void setListener(TimerListener timerListener) {
        listener = timerListener;
    }

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
    /**
     * Starts the timer.
     */
    public static void startTimer() {
        printMethodName("startTimer()");

        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                if (listener != null) {
                    listener.onTurnExpired();
                    out.println("30 seconds have passed.\n");
                }
                resetTimer();
            }
        };
        timer.schedule(task, turnTime * 1000);
    }

    /**
     * Resets the timer.
     * This cancels the current task and starts a new timer.
     */
    public static void resetTimer() {
        task.cancel();
        startTimer();
    }
}
