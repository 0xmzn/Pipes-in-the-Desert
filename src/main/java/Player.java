public abstract class Player {
    private String inputPipe;
    private String outputPipe;
    private int posX;
    private int posY;

    public Player(String inputPipe, String outputPipe, int startX, int startY) {
        this.inputPipe = inputPipe;
        this.outputPipe = outputPipe;
        this.posX = startX;
        this.posY = startY;
    }

    public void changeInputPipe(String newInputPipe) {
        this.inputPipe = newInputPipe;
        System.out.println("Input pipe changed to: " + newInputPipe);
    }

    public void changeOutputPipe(String newOutputPipe) {
        this.outputPipe = newOutputPipe;
        System.out.println("Output pipe changed to: " + newOutputPipe);
    }
    // what size the grid is? is the player is on the corner or edge what movements are not possible?
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

    // Getter methods to know if the player is near cistern to get a new pump for example
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}

