import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
    private Timer timer;
    private boolean isManufacturing;

    public Cistern(){
        timer = new Timer();
        isManufacturing = false;
    }
    /**
     * Manufactures new elements in the cistern.
     * The method checks whether the inventoryElement attribute is instantiated or
     * not.
     * Also instantiates new <Pipe> or <Pump> objects separated by random time
     * intervals.
     */
    public void manufactureElement() {
        if(isManufacturing){
            System.out.println("Manufacturing is already in progress. Cannot manufacture another element.\n");
            return;
        }

        Random random = new Random();
        int randomNumber = random.nextInt(2);
        if(randomNumber==0){
            System.out.println("Manufacturing pipe...\n");
            inventoryElement = new Pipe();
            scheduleManufactureCompletion(15);
        }
        else{
            System.out.println("Manufacturing pipe...\n");
            inventoryElement = new Pump();
            scheduleManufactureCompletion(15);
        }
    }
    private void scheduleManufactureCompletion(int seconds){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Manufacturing completed.\n");
                isManufacturing = false;
            }
        }, seconds*1000);
        isManufacturing = true;
    }
}
