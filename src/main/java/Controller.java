import static java.lang.System.exit;
import static java.lang.System.out;
import java.util.Scanner;

import java.util.Scanner;

/**
 * The Controller class handles the game logic and user interactions for the
 * "Pipes in the Desert" game.
 * It is responsible for the game menu and initialization, as well as tracking
 * the water flow and managing the game turns.
 * It also contributes to randomly breaking pumps.
 */
public class Controller {
    private final Scanner scanner;

    /**
     * Constructs a new Controller object. Initializes the scanner to reuse for user
     * input.
     */
    public Controller() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the name of the method that is called.
     *
     * @param methodName The name of the method.
     */
    private static void printMethodName(String methodName) {
        System.out.println("\n------------------------------------------------------------");
        System.out.println(methodName + " method of the Controller class is called.");
        System.out.println("------------------------------------------------------------\n");
    }

    /**
     * Asks a question and displays the options to choose from.
     *
     * @param question The question to ask.
     * @param options  The options to display.
     * @return The user's answer.
     */
    private int askQuestion(String question, String... options) {
        System.out.println(question);

        if (options.length == 0) {
            System.out.println("1. Yes\n2. No");
        } else {
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
        }

        int answer = scanner.nextInt();
        if (options.length == 0) {
            if (answer < 1 || answer > 2)
                System.err.println("Invalid values was chosen for the answer!");
        } else {
            if (answer < 1 || answer > options.length) {
                System.err.println("Invalid values was chosen for the answer!");
            }
        }

        return answer;
    }

    /**
     * Displays the initial main menu of the game and handles user input.
     */
    public void displayMenu() {
        printMethodName("displayMenu()");

        System.out.println("Welcome to Pipes in the Desert!\n");
        System.out.println("Please select an option from the menu below:");
        System.out.println("1. Start a new game\n2. Exit");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                initGrid();
                startGame();
                break;
            case 2:
                onExit();
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    /**
     * Initializes the game grid.
     */
    public void initGrid() {
        printMethodName("initGrid()");
    }

    /**
     * Starts the game and handles user commands. Called in the main game loop.
     * Gives users all the callable methods to interact with the game.
     */
    public void startGame() {
        printMethodName("startGame()");
        System.out.println("THE GAME HAS STARTED!!\n");

        System.out.println("The possible commands are:");
        System.out.println("manufacturePump");
        System.out.println("manufacturePipe");
        System.out.println("breakPump");
        System.out.println("giveTurn");
        System.out.println("endGame");
        System.out.println("moveW");
        System.out.println("moveA");
        System.out.println("moveS");
        System.out.println("moveD");
        System.out.println("changeInputPipe");
        System.out.println("changeOutputPipe");
        System.out.println("pickUpPump");
        System.out.println("installPump");
        System.out.println("pickUpPipeEnd");
        System.out.println("placePipeEnd");
        System.out.println("connect");
        System.out.println("disconnect");
        System.out.println("fixPipe");
        System.out.println("fixPump");
        System.out.println("puncturePipe");

        String command = scanner.next();
        Plumber plumber = new Plumber();
        Saboteur player = new Saboteur();
        switch (command) {
            case "manufacturePump":
                this.manufacturePump();
                break;
            case "manufacturePipe":
                this.manufacturePipe();
                break;
            case "breakPump":
                this.breakPump();
                break;
            case "giveTurn":
                this.giveTurn();
                break;
            case "endGame":
                this.endGame();
                break;
            case "moveW":
                player.moveW();
                break;
            case "moveA":
                player.moveA();
                break;
            case "moveS":
                player.moveS();
                break;
            case "moveD":
                player.moveD();
                break;
            case "changeInputPipe":
                player.changeInputPipe();
                break;
            case "changeOutputPipe":
                player.changeOutputPipe();
                break;
            case "pickUpPipeEnd":
                plumber.pickUpPipeEnd(); // dont have diagram
                break;
            case "pickUpPump":
                plumber.pickUpPump();
                break;
            case "installPump":
                plumber.installPump();
                break;
            case "placePipeEnd":
                plumber.placePipeEnd();
                break;
            case "connect":
                plumber.connect();
                break;
            case "disconnect":
                plumber.disconnect();
                break;
            case "fixPipe":
                plumber.fixPipe();
                break;
            case "fixPump":
                plumber.fixPump();
                break;
            case "puncturePipe":
                player.puncturePipe();
                break;
            default:
                System.out.println("Invalid command! Please try again.");
                startGame();
        }
    }

    /**
     * Tracks the water flow in the game and increments corresponding saboteur or
     * plumber scores.
     */
    public void trackWaterFlow() {
        printMethodName("trackWaterFlow()");

        int answer = askQuestion("Where does the water flow?", "Cisterns", "Desert");
        if (answer == 1) {
            fillUpPlumber();
        } else {
            fillUpSaboteur();
        }
    }

    /**
     * Increments the plumber's scores. Counts the total water transported to the
     * city cisterns.
     */
    public void fillUpPlumber() {
        printMethodName("fillUpPlumber()");
    }

    /**
     * Increments the saboteur's scores. Counts the total water leaked to the
     * desert.
     */
    public void fillUpSaboteur() {
        printMethodName("fillUpSaboteur()");
    }

    /**
     * Manufactures a new pump. Checks if the cisterns have the capability.
     */
    public void manufacturePump() {
        printMethodName("manufacturePump()");

        int answer = askQuestion("Can a pump be manufactured at any of the cisterns?");
        if (answer == 1) {
            System.out.println("New pump is manufactured!");
        } else {
            System.out.println("New pump cannot be manufactured!");
        }
    }

    /**
     * Manufactures a new pipe. Checks if the cisterns have the capability.
     */
    public void manufacturePipe() {
        printMethodName("manufacturePipe()");

        int answer = askQuestion("Can a pipe be manufactured at any of the cisterns?");
        if (answer == 1) {
            System.out.println("New pipe is manufactured!");
        } else {
            System.out.println("New pipe cannot be manufactured!");
        }
    }

    /**
     * Breaks a pump if there is one not broken currently.
     */
    public void breakPump() {
        printMethodName("breakPump()");

        int breakingAnswer = askQuestion("Is there a pump to be broken?");
        if (breakingAnswer == 1) {
            System.out.println("The pump is broken!");

            int fillingAnswer = askQuestion("Is water flowing into the pump?");
            if (fillingAnswer == 1)
                fillUpPumpReservoir();
        } else {
            System.out.println("There are no pumps to break!");
        }
    }

    /**
     * Fills up the reservoir of a broken pump. Called in case a pump is broken and
     * water tries to be transported in the system.
     */
    public void fillUpPumpReservoir() {
        printMethodName("fillUpPumpReservoir()");

        int answer = askQuestion("Is the reservoir of the pump full?");
        if (answer == 1) { // the water is leaking from the pump to the desert
            fillUpSaboteur();
        } else {
            System.out.println("The reservoir is filling in!");
        }
    }

    /**
     * Takes the turn from the active player and gives to the next player.
     */
    public void giveTurn() {
        printMethodName("giveTurn()");

        if (Timer.turnExpired()) {
            takeTurn();
            manageRounds();
            Timer.startTimer();
        } else {
            System.out.println("We cannot change the turn, as the previous player has not finished!");
        }
    }

    /**
     * Takes the current player's turn.
     */
    public void takeTurn() {
        printMethodName("takeTurn()");
    }

    /**
     * Manages the rounds of the game. Increments the rounds counter if all 4
     * players have played their turns.
     */
    public void manageRounds() {
        printMethodName("manageRounds()");

        int answer = askQuestion("Has the whole round passed, i.e. 4 turns?");
        if (answer == 1) {
            incrementRounds();
        }
    }

    /**
     * Increments the round counter.
     */
    public void incrementRounds() {
        printMethodName("incrementRounds");
    }

    /**
     * Ends the game and performs cleanup tasks.
     */
    public void endGame() {
        printMethodName("endGame()");
        onExit();
    }

    /**
     * Performs cleanup tasks and exits the game.
     */
    public void onExit() {
        printMethodName("onExit()");
        System.exit(0);
    }
}