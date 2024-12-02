import model.Image;
import view.SwingImageDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MainFrame extends JFrame {
    private Dimension screenSize;
    public MainFrame(Image image) throws IOException {
        this.setTitle("Image Viewer");
        setScreenSize();
        setSize(screenSize);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        SwingImageDisplay swingImageDisplay = new SwingImageDisplay();
        this.add(swingImageDisplay);
        swingImageDisplay.show(image, screenSize);
    }

    private void setScreenSize(){screenSize = Toolkit.getDefaultToolkit().getScreenSize();}


}
