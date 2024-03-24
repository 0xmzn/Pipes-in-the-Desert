import static java.lang.System.out;

public class Timer {
    private static void printMethodName(String methodName) {
        out.println("\n------------------------------------------------------------");
        out.println(methodName + " method of the Timer class is called.");
        out.println("------------------------------------------------------------\n");
    }

    public static void startTimer() {
        printMethodName("startTimer()");
    }
}
