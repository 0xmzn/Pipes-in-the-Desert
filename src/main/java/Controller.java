import javax.swing.*;
import static java.lang.System.out;
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

    private Point coordinate;
    /**
     * Constructs a new Controller object. Initializes the scanner to reuse for user
     * input.
     */
    public Controller() {
        scanner = new Scanner(System.in);
        grid = new Element[10][12];
        plumberScore = 0;
        saboteurScore = 0;
        coordinate = new Point(150,60);
        plumber1 = new Plumber(coordinate);
        plumber2 = new Plumber(coordinate);
        saboteur1 = new Saboteur(coordinate);
        saboteur2 = new Saboteur(coordinate);
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
        JPanel gameBoard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(232, 140, 35,200)); // Brown color
                g.fillRect(160, 50, boardWidth, boardHeight);
                // Draw grid lines or other game elements as needed
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(2));
                g.setColor(new Color(153, 88, 14,200));
                int cellWidth = boardWidth / 10;
                int cellHeight = boardHeight / 10;
                // Draw grid lines
                for (int i = 0; i <= 10; i++) {
                    g.drawLine(160, 50+i * cellHeight, 160+boardWidth, 50+i * cellHeight);
                }
                for (int j = 0; j <= 10; j++) {
                    g.drawLine(160+j * cellWidth, 50, 160+j * cellWidth, 50+boardHeight);
                }
            }
        };
        gameBoard.setOpaque(false);
        gameGridPanel.setLayout(new BorderLayout());
        gameGridPanel.add(gameBoard, BorderLayout.CENTER);

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
        cistern1.getCisternLabel().setBounds(-8,-30, 300,300);
        cistern1.getCisternLabel().setOpaque(true);
        gameFrame.getContentPane().add(cistern1.getCisternLabel());

        spring1.getSpringLabel().setBounds(350,-185,600,600);
        spring1.getSpringLabel().setOpaque(true);
        gameFrame.getContentPane().add(spring1.getSpringLabel());

        //2nd
        cistern2.getCisternLabel().setBounds(-8,100, 300,300);
        cistern2.getCisternLabel().setOpaque(true);
        gameFrame.getContentPane().add(cistern2.getCisternLabel());

        spring2.getSpringLabel().setBounds(350,-45,600,600);
        spring2.getSpringLabel().setOpaque(true);
        gameFrame.getContentPane().add(spring2.getSpringLabel());

        //3rd
        cistern3.getCisternLabel().setBounds(-8,240, 300,300);
        cistern3.getCisternLabel().setOpaque(true);
        gameFrame.getContentPane().add(cistern3.getCisternLabel());

        spring3.getSpringLabel().setBounds(350,85,600,600);
        spring3.getSpringLabel().setOpaque(true);
        gameFrame.getContentPane().add(spring3.getSpringLabel());

        gameFrame.getContentPane().add(gameGridPanel);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }
    /**
     * Starts the game and handles user commands. Called in the main game loop.
     * Gives users all the callable methods to interact with the game.
     */
    public void startGame() {
        printMethodName("startGame()");
        System.out.println("THE GAME HAS STARTED!!\n");
        initGrid();
        startNewRound();
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
     * @param nextPlayer The next player to receive the turn.
     * @return true if the turn was successfully given, false otherwise.
     */
    public void giveTurn(Player nextPlayer) {
        // TODO: update with prototype version
        printMethodName("giveTurn()");

        if (nextPlayer instanceof Plumber) {
            System.out.println("List of commands:\n");
            System.out.println("moveA\n");
            System.out.println("moveD\n");
            System.out.println("moveS\n");
            System.out.println("moveW\n");
            System.out.println("fix\n");
            System.out.println("pickPipeEnd\n");
            System.out.println("pickPump\n");
            System.out.println("placePipeEnd\n");
            System.out.println("installPump\n");
            System.out.println("changeWaterDirection\n");
            System.out.println("connect\n");
            System.out.println("disconnect\n");
        } else if (nextPlayer instanceof Saboteur) {
            System.out.println("List of commands:\n");
            System.out.println("moveA\n");
            System.out.println("moveD\n");
            System.out.println("moveS\n");
            System.out.println("moveW\n");
            System.out.println("puncture\n");
            System.out.println("changeWaterDirection\n");
        }
    }

    /**
     * Manages the rounds of the game. Increments the rounds counter if all 4
     * players have played their turns.
     */
    public void manageRounds() {
        printMethodName("manageRounds()");
        // Increment the round
        round++;
        // Print the round value
        System.out.println("Round " + round);

        // Switch the turn to the next player based on the round
        switch (round % 4) {
            case 0:
                giveTurn(saboteur1);
                break;
            case 1:
                giveTurn(saboteur2);
                break;
            case 2:
                giveTurn(plumber1);
                break;
            case 3:
                giveTurn(plumber2);
                break;
        }
        if(round > 40){
            gameRunning = false;
        }
    }

    /**
     * Ends the game and performs cleanup tasks.
     */
    public void endGame() {
        // TODO: update with prototype version
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
        // TODO: update with prototype version
        printMethodName("onExit()");
        scanner.close();
        System.exit(0);
    }
}