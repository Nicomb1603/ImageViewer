package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SwingImageDisplay extends JPanel implements ImageDisplay<BufferedImage> {

    BufferedImage resizedBufferedImage;
    BufferedImage originalBufferedImage;
    private int x = 0;
    private int previousX;
    private List<Order> orders;


    public SwingImageDisplay(){
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.orders = new ArrayList<>();
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                previousX = e.getX();
                //System.out.println(e.getX());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x = 0;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int currentX = e.getX();
                int deltaX = currentX - previousX;
                x += deltaX;
                previousX = currentX;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
    }
/*
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
*/
    public static class Order{
        BufferedImage image;

        public Order(BufferedImage image){
            this.image = image;
        }

        public static Order create(BufferedImage image){
            return new Order(image);
        }

    }

    @Override
    public void paintImage(BufferedImage image){
        this.orders.add(Order.create(image));
        this.repaint();
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        for(Order order : orders){
            BufferedImage image = null;
            try {
                image = resize(order.image);
                g.drawImage(image, x, 0, null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private BufferedImage resize(BufferedImage image) throws IOException {
        BufferedImage resizedBufferedImage = new BufferedImage(this.getWidth(), this.getWidth(), image.getType());
        Graphics2D g2d = resizedBufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        g2d.dispose();
        return resizedBufferedImage;
    }


}
