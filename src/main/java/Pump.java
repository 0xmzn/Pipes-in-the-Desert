import java.util.Scanner;

public class Pump {

    private Scanner scanner;

    private static void printMethodName(String methodName){
        System.out.println("\n--------------------");
        System.out.println(methodName);
        System.out.println("--------------------\n");
    }

    private int askQuestion(String question){
        System.out.println(question);
        System.out.println("1. Yes");
        System.out.println("2. No");

        return scanner.nextInt();
    }


}
