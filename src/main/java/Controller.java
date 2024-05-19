import javax.swing.*;
import static java.lang.System.out;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Console;
import java.util.*;
import java.util.List;  // to avoid ambiguity of instantiation of the List container 
import java.util.Timer;
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
    private final Scanner scanner;
    /**
     * The grid that stores objects, which implement the <Element> interface, i.e.
     * <Cistern>, <Pump>, <Pipe>, <Spring> objects.
     */
    private Element[][] grid;

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

    List<Pipe> pipes;
    List<Pump> pumps;

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
    private final int GRID_ROWS = 10;
    private final int GRID_COLS = 10;
    /**
     * Constructs a new Controller object. Initializes the scanner to reuse for user
     * input.
     */
    public Controller() {
        scanner = new Scanner(System.in);
        grid = new Element[GRID_ROWS][GRID_COLS];
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

        round = 1;

        gameRunning = true;

        pipes = new ArrayList<>();
        pumps = new ArrayList<>();

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

        for(int i = 0; i<GRID_ROWS; i++){
            for(int j = 0; j<GRID_COLS; j++){
                grid[i][j] = null; //initially nothing
            }
        }

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
        GridController gridController = new GridController(new GridView(boardWidth, boardHeight));
        JPanel gameBoard = gridController.getGridPanel();
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

    private void handleKeyPress(KeyEvent e, Player activePlayer){
        int key = e.getKeyCode();
        int moveX=0;
        int moveY=0;
        switch (key){
            case KeyEvent.VK_W:
                moveY = -10;  // Move plumber up
                break;
            case KeyEvent.VK_S:
                moveY = 10;   // Move plumber down
                break;
            case KeyEvent.VK_A:
                moveX = -10;  // Move plumber left
                break;
            case KeyEvent.VK_D:
                moveX = 10;   // Move plumber right
                break;
        }

        //Defining the walking area at cisterns bounds
        int minX1 = 90;
        int maxX1 = 130;
        int minY1 = 10;
        int maxY1 = 440;

        //Defining the walking area at springs bounds
        int minX2 = 590;
        int maxX2 = 630;
        int minY2 = -10;
        int maxY2 = 430;


        // Calculate the new position
        int newX = activePlayer.getCurrentCoordinate().x + moveX;
        int newY = activePlayer.getCurrentCoordinate().y + moveY;
        int j=(newX-maxX1)/45;
        int i=(newY)/45;
        Element tmp= null;
        if(i >= 0 && j>=0 && i<10 && j<10) {
            tmp = grid[i][j];
        }

        // Check if the new position is within bounds
        if ((newX >= minX1 && newY >= minY1 && newX <=maxX2 && newY <= maxY2)) {
            if((newX <= maxX1 && newY<=maxY1) ||  (newX>=minX2 && newY>=minY2)) {
                activePlayer.move(moveX, moveY);  // Move player
            } else if(((newX > maxX1 || newY>maxY1) && (newX <minX2 || newY<minY2)) && tmp !=null ){
                if(tmp.isWalkable()){
                    activePlayer.move(moveX, moveY);
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

        saboteur1.getPlumberLabel().setBounds(Saboteur1Coordinate.x, Saboteur1Coordinate.y, 60, 70);
        saboteur1.getPlumberLabel().setOpaque(true);
        gameFrame.getContentPane().add(saboteur1.getPlumberLabel());

        saboteur2.getPlumberLabel().setBounds(Saboteur2Coordinate.x, Saboteur2Coordinate.y, 60, 70);
        saboteur2.getPlumberLabel().setOpaque(true);
        gameFrame.getContentPane().add(saboteur2.getPlumberLabel());
    }

    private void addCisternLabels(Cistern cistern, Point location){
        cistern.getCisternLabel().setBounds(location.x,location.y, 300,300);
        gameFrame.add(cistern.getCisternLabel());

        cistern.getPumpPlaceLabel().setBounds(location.x+60,location.y+100,100,100);
        gameFrame.add(cistern.getPumpPlaceLabel());

        cistern.getPipeLabelPlace().setBounds(location.x+165,location.y+100,100,100);
        gameFrame.add(cistern.getPipeLabelPlace());
    }
    public void placePipeorPump(int row, int col, Element element){
        if(row >=0 &&row<GRID_ROWS&&col>=0 && col<GRID_COLS){
            grid[row][col] = element;
        }
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

        cistern1.manufactureElement();
        pumps.add(cistern1.getInventoryPump());
        pipes.add(cistern1.getInventoryPipe());
        if(cistern1.getInventoryPipe()!=null){
            placePipeorPump(1,0, cistern1.getInventoryPipe());
        }
        cistern2.manufactureElement();
        pumps.add(cistern2.getInventoryPump());
        pipes.add(cistern2.getInventoryPipe());
        if(cistern2.getInventoryPipe()!=null){
            placePipeorPump(4,0, cistern2.getInventoryPipe());
        }
        cistern3.manufactureElement();
        pumps.add(cistern3.getInventoryPump());
        pipes.add(cistern3.getInventoryPipe());
        if(cistern3.getInventoryPipe()!=null){
            placePipeorPump(7,0, cistern3.getInventoryPipe());
        }


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
        // TODO
        printMethodName("trackWaterFlow()");
        
        Point activeSpringCoordinate = activeSpring.getCoordinate();
        int activeSpringX = (int)activeSpringCoordinate.getX();
        int activeSpringY = (int)activeSpringCoordinate.getY();

        ActiveElement nextConnectedActiveElement = ((EndOfPipe)grid[activeSpringX][activeSpringY]).getPairEndOfPipe().getActiveElement();
        
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
    /**
     * Breaks a pump if there is one not broken currently.
     */
    public void breakPump() {
        // TODO: update with prototype version
        printMethodName("breakPump()");

        if(pumps.isEmpty()){
            System.out.println("No pumps available to break;");
            return;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(pumps.size());
        Pump pumpToBreak = pumps.get(randomIndex);
        pumpToBreak.setIsPunctured(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                breakPump();
            }
        }, 50 * 1000);
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
}