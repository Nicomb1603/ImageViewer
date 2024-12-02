import model.Image;
import view.SwingImageDisplay;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    public MainFrame(Image image){
        this.setTitle("Image Viewer");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        SwingImageDisplay swingImageDisplay = new SwingImageDisplay();
        this.add(swingImageDisplay);
        swingImageDisplay.show(image);

    }
}
