package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SwingImageDisplay extends JPanel implements ImageDisplay<BufferedImage> {

    BufferedImage resizedBufferedImage;
    BufferedImage originalBufferedImage;


    public SwingImageDisplay(Dimension dimension){
        this.setSize(dimension);
    }

    @Override
    public void show(BufferedImage bufferedImage) {
        try {
            this.originalBufferedImage = bufferedImage;
            this.resizedBufferedImage = resize();
            this.repaint();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        try {
            this.resizedBufferedImage = resize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(resizedBufferedImage, 0, 0, null);
    }

    private BufferedImage resize() throws IOException {
        BufferedImage resizedBufferedImage = new BufferedImage(this.getWidth(), this.getWidth(), originalBufferedImage.getType());
        Graphics2D g2d = resizedBufferedImage.createGraphics();
        g2d.drawImage(originalBufferedImage, 0, 0, this.getWidth(), this.getHeight(), null);
        g2d.dispose();
        return resizedBufferedImage;
    }


}
