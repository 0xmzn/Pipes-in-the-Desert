import java.util.List;

/**
 * Represents a pump in the system.
 */
public class Pump extends ActiveElement{
    /**
     * Stores the amount of water that can be stored in the reservoir.
     */
    private int reservoirCapacity;

    /**
     * Stores the maximum number of pipes that can be connected (2 to 4).
     */
    private int maxPipes;

    /**
     * Keeps track of the pump's state.
     */
    private boolean isPunctured;

    /**
     * List of size maxPipes that stores a list of endOfPipe elements connectable to
     * the pump.
     */
    private List<EndOfPipe> connectedEndsOfPipes;

    /**
     * Keeps track of where the water is flowing from.
     */
    private EndOfPipe inputEndOfPipe;

    /**
     * Keeps track of where the water is flowing to.
     */
    private EndOfPipe outputEndOfPipe;

    /**
     * Returns the value of isPunctured.
     *
     * @return the value of isPunctured
     */
    public boolean getIsPunctured() {
        return isPunctured;
    }

    /**
     * Sets the value of isPunctured to the passed boolean value.
     *
     * @param isPunctured the new value of isPunctured
     */
    public void setIsPunctured(boolean isPunctured) {
        this.isPunctured = isPunctured;
    }

    /**
     * Adds the passed EndOfPipe element to the connectedEndsOfPipes list if the
     * maximum number of connected ends is not reached.
     * It returns true if the operation was successful and returns false otherwise.
     *
     * @param endOfPipe the EndOfPipe element to be added
     * @return true if the operation was successful, false otherwise
     */
    public boolean addEndOfPipe(EndOfPipe endOfPipe) {
        // TODO: update with prototype version
        if (connectedEndsOfPipes.size() < maxPipes) {
            connectedEndsOfPipes.add(endOfPipe);
            return true;
        }
        return false;
    }

    /**
     * Sets the inputEndOfPipe attribute to the passed EndOfPipe argument.
     *
     * @param endOfPipe the EndOfPipe to set as the inputEndOfPipe
     * @return true if the operation was successful, false otherwise
     */
    public boolean setInputEndOfPipe(EndOfPipe endOfPipe) {
        // TODO: update with prototype version
        this.inputEndOfPipe = endOfPipe;
        return true;
    }

    /**
     * Sets the outputEndOfPipe attribute to the passed EndOfPipe argument.
     *
     * @param endOfPipe the EndOfPipe to set as the outputEndOfPipe
     * @return true if the operation was successful, false otherwise
     */
    public boolean setOutputEndOfPipe(EndOfPipe endOfPipe) {
        // TODO: update with prototype version
        this.outputEndOfPipe = endOfPipe;
        return true;
    }
}