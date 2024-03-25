import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Controller c = new Controller();
        c.displayMenu();
        while (true) {
            System.out.println("Do you want to play again? \n1. Yes \n2. No");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 2) {
                System.out.println("Goodbye!");
                break;
            }
            c.startGame();
        }
    }
}