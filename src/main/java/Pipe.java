import java.util.List;

/**
 * Represents a pipe.
 */
public class Pipe implements Element{
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

    /**
     * Returns the punctured state of the pipe.
     *
     * @return true if the pipe is punctured, false otherwise.
     */
    public boolean isPunctured() {
        return isPunctured;
    }

    /**
     * Sets the punctured state of the pipe.
     *
     * @param puncturedState the new punctured state of the pipe.
     */
    public void setPunctured(boolean puncturedState) {
        isPunctured = puncturedState;
    }

    /**
     * Returns the water flowing state of the pipe.
     *
     * @return true if water is flowing through the pipe, false otherwise.
     */
    public boolean isWaterFlowing() {
        return isWaterFlowing;
    }

    /**
     * Sets the water flowing state of the pipe.
     *
     * @param waterFlowingState the new water flowing state of the pipe.
     */
    public void setWaterFlowing(boolean waterFlowingState) {
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

    /**
     * Sets the list of pipe ends.
     *
     * @param pipeEnds the new list of pipe ends.
     */
    public void setPipeEnds(List<EndOfPipe> pipeEnds) {
        this.pipeEnds = pipeEnds;
    }
}
