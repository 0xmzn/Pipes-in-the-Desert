import java.util.List;

/**
 * Represents an element in the pipeline system. Can be pipe, pump, 
 */
public interface Element {
    /**
     * List of currently connected ends of pipes to this active element.
     */
    protected List<EndOfPipe> connectedEndsOfPipes = new ArrayList<>();

    /**
     * Connects the specified <code>EndOfPipe</code> element to this active element.
     * 
     * @param endOfPipe the <code>EndOfPipe</code> element to connect
     * @return <code>true</code> if the <code>EndOfPipe</code> element was successfully connected, <code>false</code> otherwise
     */
    boolean connect(EndOfPipe endOfPipe);

    /**
     * Disconnects the specified <code>EndOfPipe</code> element from this active element.
     * 
     * @param endOfPipe the <code>EndOfPipe</code> element to disconnect
     * @return <code>true</code> if the <code>EndOfPipe</code> element was successfully disconnected, <code>false</code> otherwise
     */
    boolean disconnect(EndOfPipe endOfPipe);
}
