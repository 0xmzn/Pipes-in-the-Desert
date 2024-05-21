import javax.imageio.ImageIO;
import javax.swing.*;
import static java.lang.System.out;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;  // to avoid ambiguity of instantiation of the List container 
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.*;

/**
 * The Controller class handles the game logic and user interactions for the
 * "Pipes in the Desert" game.
 * It is responsible for the game menu and initialization, as well as tracking
 * the water flow and managing the game turns.
 * It also contributes to randomly breaking pumps.
 */

public class Controller {
    private List<Integer> startPoints= new ArrayList<>(){{add(1);add(4); add(7); }};
    private final Scanner scanner;
    /**
     * The grid that stores objects, which implement the <Element> interface, i.e.
     * <Cistern>, <Pump>, <Pipe>, <Spring> objects.
     */
    private static GridController grid;

    /**
     * An amount of water collected by the team of plumbers.
     */
    private int plumberScore = 0;

    /**
     * An amount of water collected by the team of saboteurs.
     */
    private int saboteurScore = 0;

    /**
     * A number of turns that have passed from the start of the game.
     */
    private int turn;
    int pipeId;

    /**
     * A constant speed with which water passes through the pipe network.
     */
    private int waterSpeed;

    /**
     * Stores from round to round whether the water is flowing through the pipe
     * network or not.
     */
    private boolean isWaterFlowing;
    private final Plumber plumber1;
    private final Plumber plumber2;
    private final Saboteur saboteur1;
    private final Saboteur saboteur2;
    private int round;
    private boolean gameRunning;

    private Map<Integer, Pipe> pipes;
    private Map<Integer, Pump> pumps;

    private final Cistern cistern1;
    private final Cistern cistern2;
    private final Cistern cistern3;

    private final Spring spring1;
    private final Spring spring2;
    private final Spring spring3;

    private final JFrame gameFrame;
    private JLabel plumberScoreLabel;
    private JLabel saboteurScoreLabel;
    private final JLabel timerLabel;
    private final JLabel roundLabel;

    private int remainingTime;

    private Point Plumber1Coordinate;
    private Point Plumber2Coordinate;
    private Point Saboteur1Coordinate;
    private Point Saboteur2Coordinate;

    private Player activePlayer;
    private static final int GRID_ROWS = 10;
    private static final int GRID_COLS = 10;

    /**
     * Constructs a new Controller object. Initializes the scanner to reuse for user
     * input.
     */
    public Controller() {
        scanner = new Scanner(System.in);
        plumberScore = 0;
        saboteurScore = 0;
        Plumber1Coordinate = new Point(115,130);
        Plumber2Coordinate = new Point(115,270);
        Saboteur1Coordinate = new Point(605,130);
        Saboteur2Coordinate = new Point(605,280);
        plumber1 = new Plumber(Plumber1Coordinate);
        plumber2 = new Plumber(Plumber2Coordinate);
        saboteur1 = new Saboteur(Saboteur1Coordinate);
        saboteur2 = new Saboteur(Saboteur2Coordinate);
        pipeId = 1;
        round = 1;

        gameRunning = true;

        pipes = new HashMap<>();
        pumps = new HashMap<>();

        gameFrame = new JFrame("PIPES IN THE DESERT");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setPreferredSize(new Dimension(800,600));

        cistern1 = new Cistern();
        cistern2 = new Cistern();
        cistern3 = new Cistern();

        spring1 = new Spring();
        spring2 = new Spring();
        spring3 = new Spring();

        plumberScoreLabel = new JLabel("Plumber Score: "+ plumberScore);
        saboteurScoreLabel = new JLabel(saboteurScore+ " :Saboteur Score");
        timerLabel = new JLabel("Time: "+remainingTime);
        roundLabel = new JLabel("Round: "+round);
    }

    /**
     * Prints the name of the method that is called.
     *
     * @param methodName The name of the method.
     */
    private static void printMethodName(String methodName) {
        System.out.println("\n------------------------------------------------------------");
        System.out.println(methodName + " method of the Controller class is called.");
        System.out.println("------------------------------------------------------------\n");
    }

    /**
     * Initializes the game grid.
     */
    public void initGrid() {
        printMethodName("initGrid()");

        JPanel gameGridPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("res/gamegrid2.jpg");
                Image backImage = imageIcon.getImage();
                g.drawImage(backImage,0,0,getWidth(),getHeight(),this);
            }
        };

        int boardWidth = 450;
        int boardHeight = 450;
        grid = new GridController(new GridView(boardWidth, boardHeight));
        JPanel gameBoard = grid.getGridPanel();
        gameBoard.setBounds(160, 50, boardWidth, boardHeight);
        gameBoard.setOpaque(false);

        plumberScoreLabel.setForeground(Color.WHITE);
        saboteurScoreLabel.setForeground(Color.WHITE);
        timerLabel.setForeground(Color.WHITE);
        roundLabel.setForeground(Color.WHITE);
        plumberScoreLabel.setBounds(10, 20, 400, 20); // x, y, width, height
        saboteurScoreLabel.setBounds(480, 20, 400, 20); // x, y, width, height
        timerLabel.setBounds(620, 540, 150, 20);
        roundLabel.setBounds(450, 540, 150, 20);

        gameFrame.getContentPane().add(plumberScoreLabel);
        gameFrame.getContentPane().add(saboteurScoreLabel);
        gameFrame.getContentPane().add(timerLabel);
        gameFrame.getContentPane().add(roundLabel);

        //1st
        spring1.getSpringLabel().setBounds(350,-185,600,600);
        spring1.getSpringLabel().setOpaque(true);
        gameFrame.getContentPane().add(spring1.getSpringLabel());

        //2nd
        spring2.getSpringLabel().setBounds(350,-45,600,600);
        spring2.getSpringLabel().setOpaque(true);
        gameFrame.getContentPane().add(spring2.getSpringLabel());

        //3rd
        spring3.getSpringLabel().setBounds(350,85,600,600);
        spring3.getSpringLabel().setOpaque(true);
        gameFrame.getContentPane().add(spring3.getSpringLabel());

        //Cisterns
        addCisternLabels(cistern1, new Point(-10,-30));
        addCisternLabels(cistern2, new Point(-10,100));
        addCisternLabels(cistern3, new Point(-10,240));
        cistern1.setCoordinate(new Point(90,80));
        cistern2.setCoordinate(new Point(90,210));
        cistern3.setCoordinate(new Point(90,350));

        for(int i = 0; i<GRID_ROWS; i++){
            if(i!=3 && i!=6){
                placePipes(new Pipe(), new Point(i,1));
            }
            else{
                placePumps(new Pump(), new Point(i,1));
            }
        }

        for(int i = 0; i<GRID_ROWS; i++){
            if(i!=3 && i!=6){
                placePipes(new Pipe(), new Point(i,4));
            }
            else{
                placePumps(new Pump(), new Point(i,4));
            }
        }

        for(int i = 0; i<GRID_ROWS; i++){
            if(i!=3 && i!=6){
                placePipes(new Pipe(), new Point(i,7));
            }
            else{
                placePumps(new Pump(), new Point(i,7));
            }
        }

        //walking area
        walkArea(gameFrame.getContentPane(), new Color(94, 59, 28), new Point(99, 50), new Dimension(60, 450));
        walkArea(gameFrame.getContentPane(), new Color(94, 59, 28), new Point(612, 50), new Dimension(60, 450));

        //pumps manufacture area
        walkArea(gameFrame.getContentPane(),new Color(51,25,0), new Point(50,95), new Dimension(50,50));
        walkArea(gameFrame.getContentPane(),new Color(51,25,0), new Point(50,225), new Dimension(50,50));
        walkArea(gameFrame.getContentPane(),new Color(51,25,0), new Point(50,365), new Dimension(50,50));

        gameFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e, activePlayer);
            }
        });

        gameFrame.add(gameBoard, BorderLayout.CENTER);
        gameFrame.getContentPane().add(gameGridPanel);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }

    /**
     * Handles the key press event and performs the corresponding actions based on the pressed key.
     *
     * @param e            the KeyEvent object representing the key press event
     * @param activePlayer the active player object
     */
    private void handleKeyPress(KeyEvent e, Player activePlayer){
        int key = e.getKeyCode();
        int moveX=0;
        int moveY=0;
        switch (key){
            case KeyEvent.VK_W:
                moveY = -10;  // Move plumber up
                handleMove(moveX, moveY);
                break;
            case KeyEvent.VK_S:
                moveY = 10;   // Move plumber down
                handleMove(moveX, moveY);
                break;
            case KeyEvent.VK_A:
                moveX = -10;  // Move plumber left
                handleMove(moveX, moveY);
                break;
            case KeyEvent.VK_D:
                moveX = 10;   // Move plumber right
                handleMove(moveX, moveY);
                break;
            case KeyEvent.VK_P:
                if(activePlayer instanceof Plumber){
                    pickUpPumpAction((Plumber)activePlayer);
                }else{
                    System.out.println("Not your command");
                }
                break;
            case KeyEvent.VK_I:
                if(activePlayer instanceof Plumber){
                    installPumpAction((Plumber)activePlayer);

                }else{
                    System.out.println("Not your command");
                }
                break;
            case KeyEvent.VK_B:
                if(activePlayer instanceof Saboteur){
                    puncturePipe((Saboteur) activePlayer);
                }
                else{
                    System.out.println("Not your command");
                }
                break;
            case KeyEvent.VK_F:
                if(activePlayer instanceof Plumber){
                    fixPipe((Plumber) activePlayer);
                }
                else{
                    System.out.println("Not your command");
                }
                break;
        }

    }

    private void fixPipe(Plumber plumber){
        Point saboteurPos = plumber.getCurrentCoordinate();
        Point gridCoordinate = convertCoordinates(saboteurPos);

        int row = gridCoordinate.x;
        int col = gridCoordinate.y;

        Element elementAtPos = grid.getElementsGrid()[row][col];
        if(elementAtPos instanceof Pipe){
            Pipe pipe = (Pipe) elementAtPos;
            pipe.setIsPunctured(false);

            gameFrame.repaint();
            System.out.println("Pipe fixed at position: " + row + ", " + col);
        }
        else {
            if (elementAtPos instanceof Pump){
                Pump pump = (Pump) elementAtPos;
                pump.setIsPunctured(false);

                gameFrame.repaint();

                System.out.println("Pump fixed at position: " + row + ", " + col);
            }

            System.out.println("No pipe or pump to fix at position: " + row + ", " + col);
        }
    }

    private void puncturePipe(Saboteur saboteur){
        Point saboteurPos = saboteur.getCurrentCoordinate();
        Point gridCoordinate = convertCoordinates(saboteurPos);

        int row = gridCoordinate.x;
        int col = gridCoordinate.y;

        Element elementAtPos = grid.getElementsGrid()[row][col];
        if(elementAtPos instanceof Pipe){
            Pipe pipe = (Pipe) elementAtPos;
            pipe.setIsPunctured(true);

            grid.getElementsGrid()[row][col] = pipe;

            gameFrame.repaint();
            System.out.println("Pipe punctured at position: " + row + ", " + col);
        }
        else {
            System.out.println("No pipe to puncture at position: " + row + ", " + col);
        }
    }
    private void handleMove(int moveX, int moveY){

        //Defining the walking area at cisterns bounds
        int minX1 = 90;
        int maxX1 = 130;
        int minY1 = 10;
        int maxY1 = 440;

        //Defining the walking area at springs bounds
        int minX2 = 585;
        int maxX2 = 630;
        int minY2 = -10;
        int maxY2 = 430;

        // Calculate the new position
        int newX = activePlayer.getCurrentCoordinate().x + moveX;
        int newY = activePlayer.getCurrentCoordinate().y + moveY;
        Point gridnum = convertCoordinates(newX, newY);

        int i = gridnum.x;
        int j = gridnum.y;
        Element tmp= null;
        if(i >= 0 && j>=0 && i<10 && j<10) {
            tmp = grid.getElementsGrid()[i][j];
        }
        out.println("X: "+ activePlayer.getCurrentCoordinate().x+" Y: "+ activePlayer.getCurrentCoordinate().y + " X: "+ gridnum.x+" Y: "+ gridnum.y);
        // Check if the new position is within bounds
        if ((newX >= minX1 && newY >= minY1 && newX <=maxX2 && newY <= maxY2)) {
            if ((newX <= maxX1 && newY <= maxY1) || (newX >= minX2 && newY >= minY2)) {
                activePlayer.move(moveX, moveY);  // Move player
            } else if (((newX > maxX1 || newY > maxY1) && (newX < minX2 || newY < minY2)) && tmp != null) {
                if (tmp.isWalkable()) {
                    activePlayer.move(moveX, moveY);
                }
                else{
                    out.println("The index is out of bounds: i: "+ i+ "j: "+ j);
                }
            }
            gameFrame.repaint();
        }
    }
    //Rendering the players
    public void renderPlayers(){
        plumber1.getPlumberLabel().setBounds(Plumber1Coordinate.x, Plumber1Coordinate.y, 40, 70);
        plumber1.getPlumberLabel().setOpaque(true);
        gameFrame.getContentPane().add(plumber1.getPlumberLabel());

        plumber2.getPlumberLabel().setBounds(Plumber2Coordinate.x, Plumber2Coordinate.y, 40, 70);
        plumber2.getPlumberLabel().setOpaque(true);
        gameFrame.getContentPane().add(plumber2.getPlumberLabel());

        saboteur1.getSaboteurLabel().setBounds(Saboteur1Coordinate.x, Saboteur1Coordinate.y, 60, 70);
        saboteur1.getSaboteurLabel().setOpaque(true);
        gameFrame.getContentPane().add(saboteur1.getSaboteurLabel());

        saboteur2.getSaboteurLabel().setBounds(Saboteur2Coordinate.x, Saboteur2Coordinate.y, 60, 70);
        saboteur2.getSaboteurLabel().setOpaque(true);
        gameFrame.getContentPane().add(saboteur2.getSaboteurLabel());
    }

    /**
     * Adds labels for the cistern, pump place, and pipe label place to the game frame at the specified location.
     *
     * @param cistern   the cistern object containing the labels to be added
     * @param location  the location where the labels should be placed
     */
    private void addCisternLabels(Cistern cistern, Point location){
        cistern.getCisternLabel().setBounds(location.x,location.y, 300,300);
        gameFrame.add(cistern.getCisternLabel());

        cistern.getPumpPlaceLabel().setBounds(location.x+60,location.y+100,100,100);
        gameFrame.add(cistern.getPumpPlaceLabel());

        cistern.getPipeLabelPlace().setBounds(location.x+60,location.y+100,100,100);
        gameFrame.add(cistern.getPipeLabelPlace());
    }

    private void placePipes(Pipe pipe, Point location){
        Point coordinate = convertToPixels(location);
        pipe.setID(pipeId++);
        pipe.getPipeLabel().setBounds(coordinate.x+2,coordinate.y+20,100,100);
        gameFrame.add(pipe.getPipeLabel());
        Grid.setElement(location, pipe);
        placePipeorPump(location.x, location.y, pipe);
        pipes.put(pipe.getID(), pipe);
        gameFrame.repaint();
    }
    private void placePumps(Pump pump, Point location){
        Point coordinate = convertToPixels(location);
        pump.setID(pipeId++);
        pump.getPumpLabel().setBounds(coordinate.x+2,coordinate.y+20,100,100);
        gameFrame.add(pump.getPumpLabel());
        Grid.setElement(location, pump);
        placePipeorPump(location.x, location.y, pump);
        pumps.put(pump.getID(), pump);
        gameFrame.repaint();

        // BREAKS ALL PUMPS BY DEFAULT
        //pump.RandomBreak();
    }
    /**
     * Places a pipe or pump element at the specified row and column in the grid.
     *
     * @param row     the row index of the grid
     * @param col     the column index of the grid
     * @param element the element to be placed (pipe or pump)
     */
    public static void placePipeorPump(int row, int col, Element element){
        if(row >=0 &&row<GRID_ROWS&&col>=0 && col<GRID_COLS){
            grid.getElementsGrid()[row][col] = element;
            System.out.println("putted to the grid");
        }
    }

    /**
     * Places a pipe or pump element at the specified coordinate in the grid.
     * Overload with Point parameter
     *
     * @param coordinate the coordinate where the element should be placed
     * @param element the element to be placed (pipe or pump)
     */
    public static void placePipeorPump(Point coordinate, Element element){
        int row = coordinate.x;
        int col = coordinate.y;
        if(row >=0 &&row<GRID_ROWS&&col>=0 && col<GRID_COLS){
            grid.getElementsGrid()[row][col] = element;
        }

    }
    /**
     * Converts pixel coordinates to a custom coordinate system.
     *
     * @param pixelCoordinates the pixel coordinates to convert
     * @return the converted coordinates
     */
    public static Point convertCoordinates(Point pixelCoordinates){
        return new Point(
                (int)(pixelCoordinates.getX() - 130)/45,
                (int) (pixelCoordinates.getY())/45);
    }

    /**
     * Converts pixel coordinates to a custom coordinate system.
     * Overload with int parameters
     *
     * @param x the x-coordinate to convert
     * @param y the y-coordinate to convert
     * @return the converted coordinates
     */
    public static Point convertCoordinates(int x, int y){
        return new Point(
                (x- 130)/45,
                y/45);
    }

    /**
     * Converts custom coordinates to pixel coordinates.
     *
     * @param coordinates the custom coordinates to convert
     * @return the converted pixel coordinates
     */
    public static Point convertToPixels(Point coordinates){
        return new Point( 45*coordinates.x +130, 45*coordinates.y);
    }

    /**
     * Starts the game and handles user commands. Called in the main game loop.
     * Gives users all the callable methods to interact with the game.
     */
    public void startGame() {
        printMethodName("startGame()");
        System.out.println("THE GAME HAS STARTED!!\n");

        activePlayer = plumber1;
        renderPlayers();

        initGrid();

        startNewRound();
    }
    /**
     * Draw walking area on the content pane.
     *
     * @param contentPane The content pane to draw on.
     * @param color       The color of the rectangle.
     * @param location    The location of the rectangle.
     * @param size        The size of the rectangle.
     */
    private void walkArea(Container contentPane, Color color, Point location, Dimension size) {
        JPanel rectanglePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(color);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        rectanglePanel.setBounds(location.x, location.y, size.width, size.height);
        rectanglePanel.setOpaque(false);
        contentPane.add(rectanglePanel);
    }

    /**
     * Tracks the water flow in the game and increments corresponding saboteur or
     * plumber scores.
     * 
     * @param activeSpring The active spring that determines the water flow.
     */
    public void trackFlow(Spring activeSpring) {
        printMethodName("trackWaterFlow()");
        
        Point activeSpringCoordinate = activeSpring.getCoordinate();
        int activeSpringX = (int)activeSpringCoordinate.getX();
        int activeSpringY = (int)activeSpringCoordinate.getY();

        ActiveElement nextConnectedActiveElement = ((EndOfPipe)grid.getElementsGrid()[activeSpringX][activeSpringY]).getPairEndOfPipe().getActiveElement();
        
        while (activeSpring.getIsActive()) {
            // need to implement in a separate thread in GUI application
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                System.err.println("Excpetion in TrackFlow() is caught!");
            }

            if (nextConnectedActiveElement == null) {
                saboteurScore++;
                out.println("Increasing saboteur teams's score.");
            } else if (nextConnectedActiveElement instanceof Cistern) {
                plumberScore++;
                out.println("Increasing plumber teams's score.");
            } else if (nextConnectedActiveElement instanceof Pump) {
                Pump currentPump = (Pump)nextConnectedActiveElement;
                if (currentPump.getIsPunctured()) {
                    if (currentPump.isReservoirFull()) {
                        saboteurScore++;
                        out.println("Increasing saboteur teams's score.");
                    } else {
                        currentPump.fillReservoir();
                        out.println("Filling the reservoir of the pump.");
                    }
                }

                nextConnectedActiveElement = currentPump.getOutputEndOfPipe().getPairEndOfPipe().getActiveElement();
            } else {
                out.println("Pipe network goes from one spring to another spring.");
            }
        }
    }

    /**
     * Increments the plumber's scores. Counts the total water transported to the
     * city cisterns.
     */
    public void fillUpPlumber() {
        printMethodName("fillUpPlumber()");
    }

    /**
     * Increments the saboteur's scores. Counts the total water leaked to the
     * desert.
     */
    public void fillUpSaboteur() {
        printMethodName("fillUpSaboteur()");
    }

    /**
     * Updates the timer label with the remaining time.
     *
     * @param seconds the remaining time in seconds
     */
    public void updateTimerLabel(int seconds) {
        this.checkWater();
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText("Time: " + seconds);
        });
    }

    // Modified method to start a new round
    public void startNewRound() {
        if (!gameRunning) {
            endGame();
            return;
        }
        remainingTime = 30;
        updateTimerLabel(remainingTime);
        Timer roundTimer = new Timer();
        roundTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (remainingTime > 0) {
                    remainingTime--;
                    updateTimerLabel(remainingTime);
                } else {
                    roundTimer.cancel();
                    manageRounds();
                    startNewRound();
                }
            }
        }, 0, 1000);
    }

    public void checkWater(){
        boolean test=true;
        for(int i : this.startPoints){
            test=true;
            for(int j=9; j>=0; j--){
                if(!grid.getElementsGrid()[j][i].WaterGoing())
                {
                    test=false;
                    break;
                }
            }
            if(test){
                this.plumberScore++;
            }
            else{
                this.saboteurScore++;
            }
        }
        this.saboteurScoreLabel.setText("saboteur:"+Integer.toString(this.saboteurScore));
        this.plumberScoreLabel.setText("plumber:"+Integer.toString(this.plumberScore));

    }

    /**
     * Breaks a pump if there is one not broken currently.
     */
    public void breakPump() {
        printMethodName("breakPump()");

        if(pumps.isEmpty()){
            System.out.println("No pumps available to break;");
            return;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(pumps.size());
        Pump pumpToBreak = pumps.get(randomIndex);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                pumpToBreak.setIsPunctured(true);
                breakPump();

            }
        }, 20 * 1000);
    }

    /**
     * Fills up the reservoir of a broken pump. Called in case a pump is broken and
     * water tries to be transported in the system.
     */
    public void fillUpPump() {
        // TODO: update with prototype version
        printMethodName("fillUpPump()");
    }

    /**
     * Takes the turn from the active player and gives to the next player.
     *
     */
    public void giveTurn() {
        printMethodName("giveTurn()");
        if(activePlayer == plumber1){
            activePlayer = plumber2;
        }else if(activePlayer == plumber2){
            activePlayer = saboteur1;
        }else if(activePlayer== saboteur1){
            activePlayer = saboteur2;
        }else if(activePlayer==saboteur2){
            activePlayer = plumber1;
            round++;
        }
        roundLabel.setText("Round: " + round);
    }

    /**
     * Manages the rounds of the game. Increments the rounds counter if all 4
     * players have played their turns.
     */
    public void manageRounds() {
        printMethodName("manageRounds()");
        // Print the round value
        System.out.println("Round " + round);
        giveTurn();
        if(round > 40){
            gameRunning = false;
        }
    }

    /**
     * Ends the game and performs cleanup tasks.
     */
    public void endGame() {
        printMethodName("endGame()");
        System.out.println("GAME OVER");
        System.out.println("Plumber Score: " + plumberScore);
        System.out.println("Saboteur Score: " + saboteurScore);
        scanner.close();
        onExit();
    }

    /**
     * Performs cleanup tasks and exits the game.
     */
    public void onExit() {
        printMethodName("onExit()");
        scanner.close();
        System.exit(0);
    }

    private void pickUpPumpAction(Plumber plumber){
        printMethodName("PickUpPump");
        Point plumberPos = plumber.getCurrentCoordinate();

        System.out.println(plumberPos);

        Cistern target = null;
        if(isNear(plumberPos, cistern1)){
            printMethodName("Picking up from cistern1");
            target = cistern1;
        } else if(isNear(plumberPos,cistern2)) {
            printMethodName("Picking up from cistern2");
            target = cistern2;
        } else if(isNear(plumberPos, cistern3)){
            printMethodName("Picking up from cistern3");
            target = cistern3;
        }

        if(target!=null){
            Pump pump = target.getInventoryPump();
            if(pump!=null){
                if(plumber.pickUpPump(target)){
                    System.out.println("Picked up");
                    target.manufactureElement();
                    gameFrame.repaint();
                }
            }else{
                System.out.println("No pump to pick up");
            }
        }else{
            System.out.println("Not close to cisterns");
        }

    }


    /**
     * Installs a pump at the next cell in the grid, if possible.
     *
     * @param plumber the plumber object
     */
    private void installPumpAction(Plumber plumber){
        printMethodName("InstallPump");

        Point chosenCell = convertCoordinates(plumber.getCurrentCoordinate());
        chosenCell.x +=1;

        if (chosenCell.getX() >= 0 && chosenCell.getY() >=0 && chosenCell.getX() < 10 && chosenCell.getY() < 10){
            if (grid.getElementsGrid()[chosenCell.x][chosenCell.y] == null
                    && plumber.inventory != null
                    && plumber.inventory instanceof Pump) {
                int x = convertToPixels(chosenCell).x;
                int y = convertToPixels(chosenCell).y;
                Pump pump = (Pump)plumber.inventory;
                placePumps(new Pump(), chosenCell);
//                JLabel pumpLabel = ((Pump) plumber.inventory).getPumpLabel();
//
//                pumpLabel.setBounds(x, y, pumpLabel.getWidth(), pumpLabel.getHeight());
//
//                gameFrame.getContentPane().add(pumpLabel);
//                pumpLabel.setVisible(true);
//                gameFrame.repaint();

                //out.println("THE PUMP IS AT X: " + pumpLabel.getX() + " Y: " + pumpLabel.getY());
                plumber.installPump(chosenCell);
            }
            else{
                out.println("The pump can not be installed!!");
            }
        }
    }

    /**
     * Checks if the plumber is near a cistern.
     *
     * @param plumberPos the plumber's position
     * @param element    the element object
     * @return true if the plumber is near the cistern, false otherwise
     */
    private boolean isNear(Point plumberPos, Element element){
        printMethodName("IsNear");
        Point cisternPos = element.getCoordinate();
        System.out.println(cisternPos);
        int proximity = 20;
        return plumberPos.distance(cisternPos) < proximity;
    }
}