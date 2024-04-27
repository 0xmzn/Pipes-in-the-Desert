import static java.lang.System.out;

/**
 * The Saboteur class extends the Player class and implements saboteur-specific
 * functionality.
 */
public class Saboteur extends Player {

    /**
     * Constructs a new Saboteur object.
     */
    public Saboteur() {
        super();
    }

    /**
     * Punctures the pipe if the Saboteur is on the pipe and it's not already
     * punctured.
     * Prints appropriate messages to indicate the success or failure of the
     * operation.
     * 
     * @param pipe the pipe to be fixed punctured
     * @return true if the element is punctured successfully, false otherwise
     */
    public boolean puncturePipe(Pipe pipe) {
        // TODO: update with prototype version
        printMethodName("puncturePipe()");

        if (this.getLocation() == pipe.getCoordinate() && !pipe.getIsPunctured()) {
            out.println("Saboteur is on the pipe and it's not punctured. Puncturing Pipe...");
            out.println("Pipe punctured successfully!");
            return true;
        } else {
            out.println("Saboteur is not on the pipe or the pipe is punctured. Cannot puncture pipe.");
            return false;
        }
    }
}