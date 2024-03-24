import static java.lang.System.out;

public class Saboteur {
    private static void printMethodName(String methodName){
        out.println("\n------------------------------------------------------------");
        out.println(methodName + " method of the Saboteur class is called.");
        out.println("------------------------------------------------------------\n");
    }

    public static void puncturePipe() {
        printMethodName("puncturePipe()");
    }
}
