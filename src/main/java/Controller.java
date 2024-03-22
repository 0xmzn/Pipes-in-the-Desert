import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class Controller {

    private Player activePlayer;
    private Timer timer;
    private Scanner scanner;
    private int turn;

    public Controller(){
        scanner = new Scanner(System.in);
        turn = 0;
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
            System.out.println("THE GAME HAS STARTED!!\n");
            System.out.println("The possible commands are:");
            System.out.println("pickUp");
            System.out.println("installPump");
            System.out.println("placePipeEnd");
            System.out.println("connect");
            System.out.println("disconnect");
            System.out.println("fixPipe");
            System.out.println("fixPump");
            System.out.println("puncturePipe");
            System.out.println("changeInputPipe");
            System.out.println("changeOutputPipe");
            System.out.println("moveW");
            System.out.println("moveA");
            System.out.println("moveS");
            System.out.println("moveD");

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
        
        int round = turn/4+1;
        int turnInRound = turn%4+1;
        System.out.println("This is round #"+round+"! turn #"+turnInRound);
        turn++;
        giveTurn();
        incrementRounds();
    }

    private void incrementRounds(){
        printMethodName("incrementRounds");
    }

    public void giveTurn(){
        printMethodName("giveTurn");
        
        System.out.println("the player given up the turn?");
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
