import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class Controller {

    private Player activePlayer;
    private Timer timer;
    private Scanner scanner;

    public Controller(){
        scanner = new Scanner(System.in);
    }

    //its a helper tho, should it be public? make new class for these?
    public void printMethodName(String methodName){
        System.out.println("\n--------------------");
        System.out.println(methodName);
        System.out.println("--------------------\n");
    }

    private void sleep(int seconds){
        try{
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e){
            System.err.println("An error occurred while waiting.");
        }
    }
    public void displayMenu(){
        printMethodName("displayMenu");

        System.out.println("Welcome to Pipes in the Desert!\nPlease select an option from the menu below:");
        System.out.println("1. Start the game");
        System.out.println("2. Exit");
        
        int choice = scanner.nextInt();
        switch(choice){
            case 1:
                startGame();
                initGrid();
                break;
            case 2:
                onExit();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayMenu();
        }
    }

    public void startGame(){
        printMethodName("startGame");

        System.out.println("Is the program ready to start the game?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        
        int programReady = scanner.nextInt();

        if (programReady == 1){
            manageRounds();
        } else {
            System.err.println("The game cannot start until the program is ready, please wait...");
            sleep(1);
            startGame();
        }
    }

    public void initGrid(){
        printMethodName("initGrid");

    }
    
    public void onExit(){
        printMethodName("onExit");
    }

    public void endGame(){
        printMethodName("endGame");
        displayMenu();

    }
    public void manageRounds(){
        printMethodName("manageRounds");
        giveTurn();
        incrementRounds();

    }

    private void incrementRounds(){
        printMethodName("incrementRounds");
    }

    public void giveTurn(){
        printMethodName("giveTurn");
        
        System.out.println("Has the timer expired?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        
        int timerExpired = scanner.nextInt();

        if (timerExpired == 1){
            //maybe have activePlayer set to next?
        }
        else{
            System.out.println("The timer has not expired yet. Please wait...");
            sleep(1);
            giveTurn();
        }
    }


}
