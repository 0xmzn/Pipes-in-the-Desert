import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PipeView {
    private JLabel pipeLabel;
    private boolean isPunctured;
    public PipeView() {
        initializePipeLabel(isPunctured);
    }

    public void initializePipeLabel(boolean isPunctured){
        BufferedImage image;
        if(!isPunctured){
            System.out.println("Not punctured Pipe");
            try{
                image = ImageIO.read(new File("res/pipe.png"));
                ImageIcon pumpIcon = new ImageIcon(image);
                pipeLabel = new JLabel(pumpIcon);
                pipeLabel.setBackground(new Color(0, 0, 0, 0));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Punctured Pipe");
            try{
                image = ImageIO.read(new File("res/leaking.png"));
                ImageIcon pumpIcon = new ImageIcon(image);
                pipeLabel = new JLabel(pumpIcon);
                pipeLabel.setBackground(new Color(0, 0, 0, 0));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public JLabel getLabel(){
        printMethodName("getLabel");
        return pipeLabel;
    }
    public void setPunctured(boolean isPunctured){
        this.isPunctured = isPunctured;
    }
    private static void printMethodName(String methodName) {
        System.out.println("\n------------------------------------------------------------");
        System.out.println(methodName + " method of the PipeView class is called.");
        System.out.println("------------------------------------------------------------\n");
    }
}
