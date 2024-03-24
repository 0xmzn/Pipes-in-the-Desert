import java.util.Scanner;
import static java.lang.System.out;

public class Saboteur extends Player {

    private Scanner scanner;

    public Saboteur(){
        scanner = new Scanner(System.in);
    }

    private static void printMethodName(String methodName){
        out.println("\n------------------------------------------------------------");
        out.println(methodName + " method of the Controller class is called.");
        out.println("------------------------------------------------------------\n");
    }

    private int askQuestion(String question){
        System.out.println(question);
        System.out.println("1. Yes");
        System.out.println("2. No");

        return scanner.nextInt();
    }

    public void puncturePipe() {
        printMethodName("puncturePipe()");
        if (getLocation() && !isPunctured()) {
            printMethodName("Saboteur is on the pipe and it's not punctured. Puncturing Pipe...");
        } else {
            printMethodName("Saboteur is not on the pipe or the pipe is punctured. Cannot puncture pipe.");
        } 
    }
}
