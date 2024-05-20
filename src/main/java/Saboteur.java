import javax.swing.*;

import static java.lang.System.out;

import java.awt.*;

/**
 * The Saboteur class extends the Player class and implements saboteur-specific
 * functionality.
 */
public class Saboteur extends Player {
    /**
     * Constructs a new Saboteur object.
     */
    private Point currentCoordinate;
    private SaboteurView saboteurView;

    public Saboteur(Point coordinate) {
        super(coordinate);
        currentCoordinate = coordinate;
        saboteurView = new SaboteurView();
    }

    @Override
    public void move(int dx, int dy) {
        currentCoordinate.translate(dx, dy);
        moveSaboteurLabel(currentCoordinate.x, currentCoordinate.y);
    }

    /**
     * Punctures the pipe if the Saboteur is on the pipe and it's not already
     * punctured.
     * Prints appropriate messages to indicate the success or failure of the
     * operation.
     *
     * @param pipe the pipe to be punctured
     * @return true if the pipe is punctured successfully, false otherwise
     */
    public boolean puncturePipe(Pipe pipe) {
        if (this.coordinate.equals(pipe.getCoordinate()) && !pipe.getIsPunctured()) {
            pipe.setIsPunctured(true);
            out.println("Pipe punctured successfully!");
            return true;
        } else {
            out.println("Cannot puncture pipe: Either not on the pipe or it is already punctured.");
            return false;
        }
    }
    public void moveSaboteurLabel(int x, int y){
        getSaboteurLabel().setLocation(x,y);
    }
    public JLabel getSaboteurLabel(){
        return saboteurView.getLabel();
    }
}