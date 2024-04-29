/**
 * Represents a Spring, which is an ActiveElement in the pipe system.
 */
public class Spring extends ActiveElement {
    /**
     * The speed of the water flow.
     */
    int speed;

    /**
     * Indication of whether the spring is connected to the pipe system or not.
     */
    boolean active;

    /**
     * Constructs a new Spring object with default values.
     * The speed is set to 1 and the spring is initially inactive.
     */
    public Spring() {
        super();
        speed = 1;
        active = false;
    }

    /**
     * Sets the activation status of the spring.
     * @param active true if the spring is active, false otherwise
     */
    public void setIsActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns the activation status of the spring.
     * @return true if the spring is active, false otherwise
     */
    public boolean getIsActive() {
        return active;
    }
}