import java.util.List;

/**
 * Represents an active element in the pipe system.
 */
public class ActiveElement implements Element {
    /**
     * List of currently connected ends of pipes to this active element.
     */
    protected List<EndOfPipe> connectedEndsOfPipes;

    /**
     * Connects the active element to an end of a pipe.
     *
     * @param endOfPipe the end of the pipe to connect to
     * @return true if the connection was successful, false otherwise
     */
    public boolean connect(EndOfPipe endOfPipe) {
        // TODO
        return false;
    }

    /**
     * Disconnects the active element from an end of a pipe.
     *
     * @param endOfPipe the end of the pipe to disconnect from
     * @return true if the disconnection was successful, false otherwise
     */
    public boolean disconnect(EndOfPipe endOfPipe) {
        // TODO
        return false;
    }
}
