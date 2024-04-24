/**
 * Represents a cistern that manufactures and stores elements.
 */
public class Cistern extends ActiveElement {
    /**
     * A <Pipe> or <Pump> object, which implements Element interface, that was
     * manufactured by a <Cistern> object
     * and is currently located at the <Cistern>.
     */
    private Element inventoryElement;

    /**
     * Manufactures new elements in the cistern.
     * The method checks whether the inventoryElement attribute is instantiated or
     * not.
     * Also instantiates new <Pipe> or <Pump> objects separated by random time
     * intervals.
     */
    public void manufactureElement() {
        // TODO: update with prototype version
        // two old method implementations are provided below
    }

    /**
     * Manufactures a new pump. Checks if the cisterns have the capability.
     */
    public void manufacturePump() {
        //TODO: update with prototype version
        // decide whether to remove this after implementing manufactureElement()
        printMethodName("manufacturePump()");

        int answer = askQuestion("Can a pump be manufactured at any of the cisterns?");
        if (answer == 1) {
            System.out.println("New pump is manufactured!");
        } else {
            System.out.println("New pump cannot be manufactured!");
        }
    }

    /**
     * Manufactures a new pipe. Checks if the cisterns have the capability.
     */
    public void manufacturePipe() {
        //TODO: update with prototype version
        // decide whether to remove this after implementing manufactureElement()
        printMethodName("manufacturePipe()");

        int answer = askQuestion("Can a pipe be manufactured at any of the cisterns?");
        if (answer == 1) {
            System.out.println("New pipe is manufactured!");
        } else {
            System.out.println("New pipe cannot be manufactured!");
        }
    }
}
