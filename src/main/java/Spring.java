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

    public void setIsActive(boolean active) {
        this.active = active;
    }

    public boolean getIsActive() {
        return active;
    }

    /**
     * Notifies the Controller about starting the supplementation of water to the
     * pipe system.
     * First, the method checks if the precondition is met:
     * TODO
     */
    void supplyWater() {
        // TODO: update with prototype version
    }
}