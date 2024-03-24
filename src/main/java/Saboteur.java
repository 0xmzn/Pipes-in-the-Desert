public class Saboteur extends Player {
    public Saboteur(String inputPipe, String outputPipe, int startX, int startY) {
        super(inputPipe, outputPipe, startX, startY);
    }
    public void puncturePipe() {
        System.out.println("Saboteur has punctured the pipe!");
    }
}
