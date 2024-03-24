import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class Controller {
    private Scanner scanner;

    public Controller(){
        scanner = new Scanner(System.in);
    }

    //its a helper tho, should it be public? make new class for these?
    public static void printMethodName(String methodName){
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
                initGrid();
                startGame();
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
            System.out.println("pickUpPump");
            System.out.println("installPump");
            System.out.println("pickUpPipeEnd");
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

            string command = scanner.nextLine();
            switch (command){
                case "pickUpPipeEnd":
                    Plumber.pickUpPipeEnd();//dont have diagram
                    break;
                case "pickUpPump":
                    Plumber.pickUpPump();
                    break;
                case "installPump":
                    Plumber.installPump();
                    break;
                case "placePipeEnd":
                    Plumber.placePipeEnd();
                    break;
                case "connect":
                    Plumber.connect();
                    break;
                case "disconnect":
                    Plumber.disconnect();
                    break;
                case "fixPipe":
                    Plumber.fixPipe();
                    break;
                case "fixPump":
                    Plumber.fixPump();
                    break;
                case "puncturePipe":
                    Saboteur.puncturePipe();
                    break;
                case "changeInputPipe":
                    Player.changeInputPipe();
                    break;
                case "changeOutputPipe":
                    Player.changeOutputPipe();
                    break;
                case "moveW":
                    Player.moveW();
                    break;
                case "moveA":
                    Player.moveA();
                    break;
                case "moveS":
                    Player.moveS();
                    break;
                case "moveD":
                    Player.moveD();
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    startGame();
            }
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
