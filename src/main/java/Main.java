import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
/**
 * The Main class is the entry point of the program.
 * It contains the main method that starts the game and calls the game loop.
 */
public class Main {

    /**
     * The main method is the entry point of the program.
     * It creates an instance of the Controller class, displays the menu,
     * and allows the user to play the game multiple times until they choose to
     * exit.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            Font customFont = loadFont("res/Sabo-Filled.otf", 24);
            setUIFont(new FontUIResource(customFont));
            createGUI();
        });

    }
    private static void createGUI(){
        JFrame frame = new JFrame("PIPES IN THE DESERT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800,600));

        JPanel backgroundPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("res/9259354.jpg");
                g.drawImage(imageIcon.getImage(),0,0,getWidth(),getHeight(),this);
            }
        };

        backgroundPanel.setLayout(null);

        JLabel gameLabel = new JLabel("PIPES IN THE DESERT");
        //gameLabel.setForeground(Color.WHITE);
        gameLabel.setFont(loadFont("res/Sabo-Filled.otf",40));
        gameLabel.setBounds(140, 140, 600, 50);


        JButton startButton = new JButton("Start Game");
        JButton exitButton = new JButton("Exit");

        Color buttonColor = new Color(255,248,199,255);
        startButton.setBackground(buttonColor);
        exitButton.setBackground(buttonColor);
        startButton.setFocusable(false);
        exitButton.setFocusable(false);

        Dimension buttonSize = new Dimension(200,50);
        startButton.setPreferredSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);
        startButton.setBounds(280, 270, 200, 50);
        exitButton.setBounds(280, 330, 200, 50);

        Font buttonFont = loadFont("res/Sabo-Filled.otf", 22);
        startButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameController c = new GameController();
                frame.dispose();
                c.startGame();

            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        backgroundPanel.add(startButton);
        backgroundPanel.add(exitButton);
        backgroundPanel.add(gameLabel);
        frame.getContentPane().add(backgroundPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static Font loadFont(String path, int size){
        try {
            File fontFile = new File (path);
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            return font.deriveFont(Font.PLAIN, size);
        } catch (Exception ex){
            ex.printStackTrace();
            return new Font("Arial", Font.PLAIN,size);
        }
    }
    public static void setUIFont(FontUIResource f) {
        UIManager.put("Button.font", f);
        UIManager.put("Label.font", f);
        UIManager.put("TextField.font", f);
    }
}