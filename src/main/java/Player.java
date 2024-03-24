import java.util.Scanner;

public abstract class Player {
    private String inputPipe;
    private String outputPipe;
    private int posX;
    private int posY;

    private Scanner scanner;

    public Player(String inputPipe, String outputPipe, int startX, int startY) {
        this.inputPipe = inputPipe;
        this.outputPipe = outputPipe;
        this.posX = startX;
        this.posY = startY;
    }

    public void changeInputPipe() {
        if (getLocation() && !isPunctured()) {
            System.out.println("Player is on the pump and it's not punctured. Attempting to change the input pipe...");
            inputPipe = "NextPipe"; // Simplified for demonstration
            System.out.printf("Input pipe changed to: %s\n", inputPipe);
        } else if (!getLocation()) {
            System.out.println("Player is not on the pump. Cannot change the input pipe.");
        } else if (isPunctured()) {
            System.out.println("The pump is punctured. Cannot change the input pipe.");
        }
    }

    public void changeOutputPipe() {
        if (getLocation() && !isPunctured()) {
            System.out.println("Player is on the pump and it's not punctured. Attempting to change the output pipe...");
            outputPipe = "NextPipe"; // Simplified for demonstration
            System.out.printf("Output pipe changed to: %s\n", outputPipe);
        } else if (!getLocation()) {
            System.out.println("Player is not on the pump. Cannot change the output pipe.");
        } else if (isPunctured()) {
            System.out.println("The pump is punctured. Cannot change the output pipe.");
        }
    }

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

    private boolean getLocation(){
        printMethodName("getLocation()");
        int isStandingOnPump = askQuestion("Is the Player standing on the Pump?");

        if (isStandingOnPump != 1 && isStandingOnPump != 2){
            System.out.println("Invalid command, changeInput/OutputPipe() failed");
            return false;
        }
        else if (isStandingOnPump == 2){
            System.out.println("This player is not standing on the Pump, changeInput/OutputPipe() failed");
            return false;
        }

        System.out.println("This player is standing on the Pump");
        return true;
    }
    
    public boolean isPunctured(){
        printMethodName("isPunctured()");
        int isPumpPunctured = askQuestion("Is the Pump punctured?");

        if (isPumpPunctured != 1 && isPumpPunctured != 2){
            printMethodName("Invalid command, changeInputPipe() failed");
            return false;
        }
        else if (isPumpPunctured == 1){
            printMethodName("This pump is punctured, changeInputPipe() failed");
            return true;
        }

        printMethodName("This pump is not punctured");
        return false;
    }

    public void moveW() {
        this.posY -= 1; // Assuming positive Y-axis is downward
        System.out.println("Moved up to position: (" + posX + ", " + posY + ")");
    }

    public void moveA() {
        this.posX -= 1; // Move left
        System.out.println("Moved left to position: (" + posX + ", " + posY + ")");
    }

    public void moveS() {
        this.posY += 1; // Move down
        System.out.println("Moved down to position: (" + posX + ", " + posY + ")");
    }

    public void moveD() {
        this.posX += 1; // Move right
        System.out.println("Moved right to position: (" + posX + ", " + posY + ")");
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
