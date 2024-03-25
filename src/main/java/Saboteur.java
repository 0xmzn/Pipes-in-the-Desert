import static java.lang.System.out;

public class Saboteur extends Player {

    public Saboteur() {
        super();
    }

    public void puncturePipe() {
        printMethodName("puncturePipe()");

        if (getLocation("Pipe") && !isPunctured()) {
            out.println("Saboteur is on the pipe and it's not punctured. Puncturing Pipe...");
            out.println("Pipe punctured successfully!");
        } else {
            out.println("Saboteur is not on the pipe or the pipe is punctured. Cannot puncture pipe.");
        }
    }
}