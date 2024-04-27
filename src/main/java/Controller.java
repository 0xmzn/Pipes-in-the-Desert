import static java.lang.System.exit;
import static java.lang.System.out;

import java.util.*;

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
     * The grid that stores objects, which implement the <Element> interface, i.e.
     * <Cistern>, <Pump>, <Pipe>, <Spring> objects.
     */
    private Element[][] grid;

    /**
     * An amount of water collected by the team of plumbers.
     */
    private int plumberScore;

    /**
     * An amount of water collected by the team of saboteurs.
     */
    private int saboteurScore;

    /**
     * A number of turns that have passed from the start of the game.
     */
    private int turn;

    /**
     * A constant speed with which water passes through the pipe network.
     */
    private int waterSpeed;

    /**
     * Stores from round to round whether the water is flowing through the pipe
     * network or not.
     */
    private boolean isWaterFlowing;
    private Plumber plumber1;
    private Plumber plumber2;
    private Saboteur saboteur1;
    private Saboteur saboteur2;
    private int round;
    private boolean gameRunning;

    List<Pipe> pipes;
    List<Pump> pumps;


    /**
     * Constructs a new Controller object. Initializes the scanner to reuse for user
     * input.
     */
    public Controller() {
        scanner = new Scanner(System.in);
        grid = new Element[10][12];
        plumberScore = 0;
        saboteurScore = 0;
        plumber1 = new Plumber();
        plumber2 = new Plumber();
        saboteur1 = new Saboteur();
        saboteur2 = new Saboteur();
        round = 0;
        gameRunning = true;
        pipes = new ArrayList<>();
        pumps = new ArrayList<>();
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
     * Displays the initial main menu of the game and handles user input.
     */
    public void displayMenu() {
        // TODO: update with prototype version
        printMethodName("displayMenu()");

        System.out.println("WELCOME TO THE \"PIPES IN THE DESERT\" GAME\n");
        System.out.println("Choose the following options:");
        System.out.println("1.Start Game\n2.Exit");

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
                displayMenu();
                break;
        }
    }

    /**
     * Initializes the game grid.
     */
    public void initGrid() {
        // TODO: update with prototype version
        printMethodName("initGrid()");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = null;
            }
        }
        grid[0][3] = new Cistern();
        grid[0][6] = new Cistern();
        grid[0][9] = new Cistern();

        grid[11][3] = new Spring();
        grid[11][6] = new Spring();
        grid[11][9] = new Spring();
    }

    /**
     * Starts the game and handles user commands. Called in the main game loop.
     * Gives users all the callable methods to interact with the game.
     */
    public void startGame() {
        // TODO: update with prototype version
        printMethodName("startGame()");
        System.out.println("THE GAME HAS STARTED!!\n");
        //start the game timer
        GameTimer.startTimer();

        while(gameRunning){
            manageRounds();
        }
        endGame();
    }

    /**
     * Tracks the water flow in the game and increments corresponding saboteur or
     * plumber scores.
     * 
     * @param activeSpring The active spring that determines the water flow.
     */
    public void trackFlow(Spring activeSpring) {
        // TODO
        printMethodName("trackWaterFlow()");

    }

    /**
     * Updates the water flow based on the changes in
     * the state of the elements in the pipe system.
     *
     * @param activeSprings The list of active springs.
     * @return The updated water flow status.
     */
    public int updateFlow(List<Spring> activeSprings) {
        // TODO
        printMethodName("updateFlow()");
        return 0;
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
     * Breaks a pump if there is one not broken currently.
     */
    public void breakPump() {
        // TODO: update with prototype version
        printMethodName("breakPump()");

        if(pumps.isEmpty()){
            System.out.println("No pumps available to break;");
            return;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(pumps.size());
        Pump pumpToBreak = pumps.get(randomIndex);
        pumpToBreak.setIsPunctured(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                breakPump();
            }
        }, 50 * 1000);
    }

    /**
     * Fills up the reservoir of a broken pump. Called in case a pump is broken and
     * water tries to be transported in the system.
     */
    public void fillUpPump() {
        // TODO: update with prototype version
        printMethodName("fillUpPump()");

    }

    /**
     * Takes the turn from the active player and gives to the next player.
     * 
     * @param nextPlayer The next player to receive the turn.
     * @return true if the turn was successfully given, false otherwise.
     */
    public boolean giveTurn(Player nextPlayer) {
        // TODO: update with prototype version
        printMethodName("giveTurn()");

        if(nextPlayer instanceof Plumber){
            System.out.println("List of commands:\n");
            System.out.println("move\n");
            System.out.println("fix\n");
            System.out.println("pickPipeEnd\n");
            System.out.println("pickPump\n");
            System.out.println("placePipeEnd\n");
            System.out.println("installPump\n");
            System.out.println("changeWaterDirection\n");
            System.out.println("connect\n");
            System.out.println("disconnect\n");
        }else if (nextPlayer instanceof Saboteur){
            System.out.println("List of commands:\n");
            System.out.println("move\n");
            System.out.println("puncture\n");
            System.out.println("changeWaterDirection\n");
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up! Next player's turn.");
                takeTurn();
                timer.cancel();
            }
        },30*1000);

        String command = scanner.nextLine().trim().toLowerCase();
        switch(command){
            case "fix":
                if (nextPlayer instanceof Plumber) {
                    ((Plumber) nextPlayer).fixElement(); // Call fixPipe method from Plumber class
                } else {
                    System.out.println("Invalid command for current player.");
                }
                break;
            case "puncture":
                if (nextPlayer instanceof Saboteur) {
                    ((Saboteur) nextPlayer).puncturePipe(); // Call fixPipe method from Plumber class
                } else {
                    System.out.println("Invalid command for current player.");
                }
                break;
            case "changeWaterDirection":
                nextPlayer.changeInputPipe();
                nextPlayer.changeOutputPipe();
                break;
            case "move":
                nextPlayer.move();
                break;
            case "pickPipeEnd":
                if (nextPlayer instanceof Plumber) {
                    ((Plumber) nextPlayer).pickUpPipeEnd(); // Call fixPipe method from Plumber class
                } else {
                    System.out.println("Invalid command for current player.");
                }
                break;
            case "pickPump":
                if (nextPlayer instanceof Plumber) {
                    ((Plumber) nextPlayer).pickUpPump(); // Call fixPipe method from Plumber class
                } else {
                    System.out.println("Invalid command for current player.");
                }
                break;
            case "placePipeEnd":
                if (nextPlayer instanceof Plumber) {
                    ((Plumber) nextPlayer).placePipeEnd(); // Call fixPipe method from Plumber class
                } else {
                    System.out.println("Invalid command for current player.");
                }
                break;
            case "installPump":
                if (nextPlayer instanceof Plumber) {
                    ((Plumber) nextPlayer).installPump(); // Call fixPipe method from Plumber class
                } else {
                    System.out.println("Invalid command for current player.");
                }
                break;
            case "connect":
                if (nextPlayer instanceof Plumber) {
                    ((Plumber) nextPlayer).connect(); // Call fixPipe method from Plumber class
                } else {
                    System.out.println("Invalid command for current player.");
                }
                break;
            case "disconnect":
                if (nextPlayer instanceof Plumber) {
                    ((Plumber) nextPlayer).disconnect(); // Call fixPipe method from Plumber class
                } else {
                    System.out.println("Invalid command for current player.");
                }
                break;
        }
        return true;
    }

    /**
     * Takes the current player's turn.
     *
     * @return true if the turn was successfully taken, false otherwise.
     */
    public boolean takeTurn() {
        printMethodName("takeTurn()");
        // Increment the round manually
        round++;

        // Print the round value
        System.out.println("Round " + round % 4);

        // Switch the turn to the next player based on the round
        switch ((round - 1) % 4) {
            case 0:
                giveTurn(plumber1);
                break;
            case 1:
                giveTurn(plumber2);
                break;
            case 2:
                giveTurn(saboteur1);
                break;
            case 3:
                giveTurn(saboteur2);
                break;
        }
        return true;
    }

    /**
     * Manages the rounds of the game. Increments the rounds counter if all 4
     * players have played their turns.
     */
    public void manageRounds() {
        // TODO: update with prototype version
        printMethodName("manageRounds()");
        // Increment the round
        round++;

        // Print the round value
        System.out.println("Round " + round % 4);

        // Switch the turn to the next player based on the round
        switch (round % 4) {
            case 0:
                giveTurn(plumber1);
                break;
            case 1:
                giveTurn(plumber2);
                break;
            case 2:
                giveTurn(saboteur1);
                break;
            case 3:
                giveTurn(saboteur2);
                break;
        }

        // Set up the listener for the next round
        GameTimer.setListener(new GameTimer.TimerListener() {
            @Override
            public void onTurnExpired() {
                manageRounds();
            }
        });
        if(round > 10){
            gameRunning = false;
        }
    }

    /**
     * Ends the game and performs cleanup tasks.
     */
    public void endGame() {
        // TODO: update with prototype version
        printMethodName("endGame()");
        System.out.println("GAME OVER");
        System.out.println("Plumber Score: " + plumberScore);
        System.out.println("Saboteur Score: " + saboteurScore);
        scanner.close();
        onExit();
    }

    /**
     * Performs cleanup tasks and exits the game.
     */
    public void onExit() {
        // TODO: update with prototype version
        printMethodName("onExit()");
        scanner.close();
        System.exit(0);
    }
}