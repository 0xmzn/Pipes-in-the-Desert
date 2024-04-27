import java.util.Scanner;

/**
 * The Main class is the entry point of the program.
 * It contains the main method that starts the game and calls the game loop.
 */
public class Main {

    /**
     * The main method is the entry point of the program.
     * It creates an instance of the Controller class, displays the menu,
     * and allows the user to play the game multiple times until they choose to
     * exit.
     *
     * @param args the command-line arguments
     */
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