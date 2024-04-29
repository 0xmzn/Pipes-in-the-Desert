import java.awt.*;
import java.util.List;

/**
 * Represents a pipe.
 */
public class Pipe implements Element {
    /**
     * Keeps track of the coordinates of the pipe on the game grid.
     */
    private Point coordinate;

    /**
     * Keeps track of whether the pipe is punctured or not.
     */
    private boolean isPunctured;

    /**
     * Keeps track of whether water is flowing through the pipe or not.
     */
    private boolean isWaterFlowing;

    /**
     * Stores 2 <EndOfPipe> elements referring to both sides of the pipe.
     */
    private List<EndOfPipe> pipeEnds;

    public Pipe(EndOfPipe endOfPipe1, EndOfPipe endOfPipe2) {
        // stupid, we will change it...
        endOfPipe1.setPairEndOfPipe(endOfPipe2);
        endOfPipe2.setPairEndOfPipe(endOfPipe1);

        this.pipeEnds.add(endOfPipe1);
        this.pipeEnds.add(endOfPipe2);
    }

    @Override
    public Point getCoordinate() {
        return coordinate;
    }

    @Override
    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Returns the punctured state of the pipe.
     *
     * @return true if the pipe is punctured, false otherwise.
     */
    public boolean getIsPunctured() {
        return isPunctured;
    }

    /**
     * Sets the punctured state of the pipe.
     *
     * @param puncturedState the new punctured state of the pipe.
     */
    public void setIsPunctured(boolean puncturedState) {
        isPunctured = puncturedState;
    }

    /**
     * Returns the water flowing state of the pipe.
     *
     * @return true if water is flowing through the pipe, false otherwise.
     */
    public boolean getIsWaterFlowing() {
        return isWaterFlowing;
    }

    /**
     * Sets the water flowing state of the pipe.
     *
     * @param waterFlowingState the new water flowing state of the pipe.
     */
    public void setIsWaterFlowing(boolean waterFlowingState) {
        isWaterFlowing = waterFlowingState;
    }

    /**
     * Returns the list of pipe ends.
     *
     * @return the list of pipe ends.
     */
    public List<EndOfPipe> getPipeEnds() {
        return pipeEnds;
    }

}
