import java.util.Scanner;

public class Plumber extends Player 
{
    Scanner scanner;

    public Plumber(){
        super();
        scanner = new Scanner(System.in);
    }

    private static void printMethodName(String methodName){
        System.out.println("\n--------------------");
        System.out.println(methodName);
        System.out.println("--------------------\n");
    }

    private int askQuestion(String question){
        System.out.println(question);
        System.out.println("1. Yes");
        System.out.println("2. No");

        return scanner.nextInt();
    }

    private boolean checkType(){
        printMethodName("checkType()");
        int isPlumber = askQuestion("Are you a plumber?");

        if (isPlumber != 1 && isPlumber != 2){
            System.out.println("Invalid command, pickUp() failed");
            return false;
        }
        else if (isPlumber == 2){
            System.out.println("This player is not a plumber, pickUp() failed");
            return false;
        }

        System.out.println("");
        return true;
    }

    private boolean checkInventory(){
        printMethodName("checkInventory()");
        int isInventoryFree = askQuestion("Is the inventory free?");
        
        if (isInventoryFree != 1 && isInventoryFree != 2){
            System.out.println("Invalid command, pickUp() failed");
            return false;
        }
        else if (isInventoryFree == 2){
            System.out.println("This player is not a plumber, pickUp() failed");
            return false;
        }

        System.out.println("");
        return true;
        
    }

    private boolean getConnected() {
        printMethodName("getConnected()");
        int isConnected = askQuestion("Is the pipe end connected to an element?");
        
        if (isConnected != 1 && isConnected != 2){
            System.out.println("Invalid command, pickUp() failed");
            return false;
        }
        else if (isConnected == 2){
            return false;
        }
        return true;
    }

    private boolean getPipeEnds() {
        printMethodName("getPipeEnds()");
        int hasOtherEnd = askQuestion("Is the other pipe end connected to an active element?");
        
        if (hasOtherEnd != 1 && hasOtherEnd != 2){
            System.out.println("Invalid command, pickUp() failed");
            return false;
        }
        else if (hasOtherEnd == 2){
            System.out.println("The other end of this pipe is not connected to any active elements!");
            return false;
        }

        System.out.println("");
        return true;    
    }

    public void pickUp(){
        printMethodName("pickUp");
        
        System.out.println("You are trying to pick up a pipe end...");
        if (!checkType() || !checkInventory() || !getLocation()){
            return;
        }
        boolean isConnectedToActiveElement = getConnected();
        if (!isConnectedToActiveElement){
            disconnect();
        }
        else if (isConnectedToActiveElement && !getPipeEnds()){
            System.err.println("Pick up failed as the other end of pipe is also disconnected!");
            return;
        }

        System.out.println("Pipe picked up successfully!");

    }

    public void installPump(){
        printMethodName("installPump");
    }

    public void placePipeEnd(){
        printMethodName("placePipeEnd");
    }

    public void connect(){
        printMethodName("connect");
    }

    public void disconnect(){
        printMethodName("disconnect");
    }

    public void fixPipe(){
        printMethodName("fixPipe");
    }

    public void fixPump(){
        printMethodName("fixPump");
    }
}
