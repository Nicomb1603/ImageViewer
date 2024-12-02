package view;

import model.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
//import java.awt.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SwingImageDisplay extends JPanel implements ImageDisplay {

    @Override
    public void show(Image image, Dimension size) {
        try {
            BufferedImage bufferedImage = resize(image, size);
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

    private BufferedImage resize(Image image, Dimension size) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(image.name()));
        BufferedImage resizedBufferedImage = new BufferedImage(size.width, size.height, bufferedImage.getType());
        Graphics2D g2d = resizedBufferedImage.createGraphics();
        g2d.drawImage(bufferedImage, 0, 0, size.width, size.height, null);
        g2d.dispose();
        return resizedBufferedImage;
    }
}
