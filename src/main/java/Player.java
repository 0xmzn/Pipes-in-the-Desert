import java.util.Scanner;

public abstract class Player {
    private String inputPipe;
    private String outputPipe;
    private int posX;
    private int posY;
    protected Pump pump;

    public Player(String inputPipe, String outputPipe, int startX, int startY) {
        this.inputPipe = inputPipe;
        this.outputPipe = outputPipe;
        this.posX = startX;
        this.posY = startY;
        this.pump = pump;
    }

    public void changeInputPipe() {
        if (getLocation() && !pump.isPunctured()) {
            System.out.println("Player is on the pump and it's not punctured. Attempting to change the input pipe...");
            inputPipe = "NextPipe"; // Simplified for demonstration
            System.out.printf("Input pipe changed to: %s\n", inputPipe);
        } else if (!getLocation()) {
            System.out.println("Player is not on the pump. Cannot change the input pipe.");
        } else if (pump.isPunctured()) {
            System.out.println("The pump is punctured. Cannot change the input pipe.");
        }
    }

    public void changeOutputPipe() {
        if (getLocation() && !pump.isPunctured()) {
            System.out.println("Player is on the pump and it's not punctured. Attempting to change the output pipe...");
            outputPipe = "NextPipe"; // Simplified for demonstration
            System.out.printf("Output pipe changed to: %s\n", outputPipe);
        } else if (!getLocation()) {
            System.out.println("Player is not on the pump. Cannot change the output pipe.");
        } else if (pump.isPunctured()) {
            System.out.println("The pump is punctured. Cannot change the output pipe.");
        }
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

    public boolean getLocation() {
        System.out.println("getLocation");
        return true; // for now sends true so the player stands on the pump
    }
}
