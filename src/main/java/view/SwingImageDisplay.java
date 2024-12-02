package view;

import model.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
//import java.awt.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SwingImageDisplay extends JPanel implements ImageDisplay {

    @Override
    public void show(Image image) {
        try {
            byte[] imageBytes = Files.readAllBytes(Path.of(image.name()));
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            ImageIcon icon = new ImageIcon(bufferedImage);
            JLabel label = new JLabel(icon);

            this.removeAll();
            this.add(label, BorderLayout.CENTER);

            this.revalidate();
            this.repaint();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
