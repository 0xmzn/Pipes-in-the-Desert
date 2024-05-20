import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PipeView {
    private JLabel pipeLabel;

    public PipeView() {
        initializePipeLabel();
    }

    public void initializePipeLabel(){
        BufferedImage image;
        try{
            image = ImageIO.read(new File("res/pipe.png"));
            ImageIcon pumpIcon = new ImageIcon(image);
            pipeLabel = new JLabel(pumpIcon);
            pipeLabel.setBackground(new Color(0, 0, 0, 0));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void drawPipe(boolean isPunctured){
        BufferedImage image;
        if(!isPunctured){
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
        return pipeLabel;
    }
}
