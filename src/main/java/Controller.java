import static java.lang.System.exit;
import static java.lang.System.out;
import java.util.Scanner;

public class Controller {
    private final Scanner scanner;

    public Controller(){
        scanner = new Scanner(System.in);
    }

    private static void printMethodName(String methodName){
        out.println("\n------------------------------------------------------------");
        out.println(methodName + " method of the Controller class is called.");
        out.println("------------------------------------------------------------\n");
    }

    private int askQuestion(String question, String... options) {
        out.println(question);

        if (options.length == 0) {
            out.println("1. Yes\n2. No");
        } else {
            for(int i = 0; i < options.length; i++) {
                out.println((i + 1) + ". " + options[i]);
            }
        }

        int answer = scanner.nextInt();
        if (options.length == 0) {
            if (answer < 1 || answer > 2)
                System.err.println("Invalid values was chosen for the answer!");
        } else {
            if(answer < 1 || answer > options.length) {
                System.err.println("Invalid values was chosen for the answer!");
            }
        }
        
        return answer;
    }

    public void displayMenu(){
        printMethodName("displayMenu()");

        out.println("Welcome to Pipes in the Desert!\n");
        out.println("Please select an option from the menu below:");
        out.println("1. Start a new game\n2. Exit");
        
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
                out.println("Invalid choice! Please try again.");
                displayMenu();
        }
    }

    public void initGrid(){
        printMethodName("initGrid()");
    }

    public void startGame(){
        printMethodName("startGame()");
        out.println("THE GAME HAS STARTED!!\n");

        out.println("The possible commands are:");
        out.println("manufacturePump");
        out.println("manufacturePipe");
        out.println("breakPump");
//        out.println("giveTurn");
        out.println("endGame");
        out.println("moveW");
        out.println("moveA");
        out.println("moveS");
        out.println("moveD");
        out.println("changeInputPipe");
        out.println("changeOutputPipe");
//        out.println("pickUpPump");
//        out.println("installPump");
//        out.println("pickUpPipeEnd");
//        out.println("placePipeEnd");
//        out.println("connect");
//        out.println("disconnect");
//        out.println("fixPipe");
//        out.println("fixPump");
        out.println("puncturePipe");

        String command = scanner.nextLine();
        Saboteur player = new Saboteur();
        switch (command){
            case "manufacturePump":
                this.manufacturePump();
                break;
            case "manufacturePipe":
                this.manufacturePipe();
                break;
            case "breakPump":
                this.breakPump();
                break;
//            case "giveTurn":
//                this.giveTurn();
//                break;
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
//            case "pickUpPipeEnd":
//                Plumber.pickUpPipeEnd();  // dont have diagram
//                break;
//            case "pickUpPump":
//                Plumber.pickUpPump();
//                break;
//            case "installPump":
//                Plumber.installPump();
//                break;
//            case "placePipeEnd":
//                Plumber.placePipeEnd();
//                break;
//            case "connect":
//                Plumber.connect();
//                break;
//            case "disconnect":
//                Plumber.disconnect();
//                break;
//            case "fixPipe":
//                Plumber.fixPipe();
//                break;
//            case "fixPump":
//                Plumber.fixPump();
//                break;
            case "puncturePipe":
               player.puncturePipe();
               break;
            default:
                out.println("Invalid command! Please try again.");
                startGame();
        }
    }

    public void trackWaterFlow() {
        printMethodName("trackWaterFlow()");

        int answer = askQuestion("Where does the water flow?", "Cisterns", "Desert");
        if (answer == 1) {
            fillUpPlumber();
        } else {
            fillUpSaboteur();
        }
    }

    public void fillUpPlumber() {
        printMethodName("fillUpPlumber()");
    }

    public void fillUpSaboteur() {
        printMethodName("fillUpSaboteur()");
    }

    public void manufacturePump() {
        printMethodName("manufacturePump()");

        int answer = askQuestion("Can a pump be manufactured at any of the cisterns?");
        if (answer == 1) {
            out.println("New pump is manufactured!");
        } else {
            out.println("New pump cannot be manufactured!");
        }
    }

    public void manufacturePipe() {
        printMethodName("manufacturePipe()");

        int answer = askQuestion("Can a pipe be manufactured at any of the cisterns?");
        if (answer == 1) {
            out.println("New pipe is manufactured!");
        } else {
            out.println("New pipe cannot be manufactured!");
        }
    }

    public void breakPump() {
        printMethodName("breakPump()");

        int breakingAnswer = askQuestion("Is there a pump to be broken?");
        if (breakingAnswer == 1) {
            out.println("The pump is broken!");

            int fillingAnswer = askQuestion("Is water flowing into the pump?");
            if (fillingAnswer == 1)
                fillUpPumpReservoir();
        } else {
            out.println("There are no pumps to break!");
        }
    }

    public void fillUpPumpReservoir() {
        printMethodName("fillUpPumpReservoir()");

        int answer = askQuestion("Is the reservoir of the pump full?");
        if (answer == 1) {  // the water is leaking from the pump to the desert
            fillUpSaboteur();
        } else {
            out.println("The reservoir is filling in!");
        }
    }

//    public void giveTurn(){
//        printMethodName("giveTurn()");
//
//        int answer = askQuestion("Has the turn of the previous player expired?");
//        if (answer == 1) {
//            takeTurn();
//            Timer.startTimer();
//
//            manageRounds();
//        } else {
//            out.println("We cannot change the turn, as the previous player has not finished!");
//        }
//    }

    public void takeTurn() {
        printMethodName("takeTurn()");
    }
    
    public void manageRounds(){
        printMethodName("manageRounds()");

        int answer = askQuestion("Has the whole round passed, i.e. 4 turns?");
        if (answer == 1) {
            incrementRounds();
        }
    }

    public void incrementRounds(){
        printMethodName("incrementRounds");
    }

    public void endGame(){
        printMethodName("endGame()");
        displayMenu();
    }

    public void onExit(){
        printMethodName("onExit()");
        System.exit(0);
    }
}
