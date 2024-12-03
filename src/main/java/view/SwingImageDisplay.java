package view;

import model.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SwingImageDisplay extends JPanel implements ImageDisplay {

    Image image;
    Dimension size;

    public SwingImageDisplay(Dimension dimension){this.size = dimension;}

    @Override
    public void show(Image image) {
        try {
            this.image = image;
            BufferedImage bufferedImage = resize(image);
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

    private BufferedImage resize(Image image) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(image.name()));
        BufferedImage resizedBufferedImage = new BufferedImage(size.width, size.height, bufferedImage.getType());
        Graphics2D g2d = resizedBufferedImage.createGraphics();
        g2d.drawImage(bufferedImage, 0, 0, size.width, size.height, null);
        g2d.dispose();
        return resizedBufferedImage;
    }

    public Image image(){return this.image;}


}
