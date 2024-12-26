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


    private int x = 0;
    private int startX;
    private List<Order> orders;
    private Offset onDragged = Offset::Null;
    private Offset onReleased = Offset::Null;


    public SwingImageDisplay(){
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.orders = new ArrayList<>();
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int offset = e.getX() - startX;
                onReleased.handle(offset);
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
                int offset = e.getX() - startX;
                onDragged.handle(offset);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    public static class Order{
        BufferedImage image;
        int x;

        public Order(BufferedImage image, int x){
            this.image = image;
            this.x = x;
        }

        public static Order create(BufferedImage image, int x){
            return new Order(image, x);
        }

    }

    @Override
    public void paintImage(BufferedImage image, int x){
        this.orders.add(Order.create(image, x));
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
                g.drawImage(image, order.x, 0, null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public int width() {
        return this.getWidth();
    }

    @Override
    public int height() {
        return this.getHeight();
    }

    @Override
    public void clear() {
        this.orders.clear();
        repaint();
    }

    @Override
    public void onReleased(Offset offset) {
        this.onReleased = offset;
    }

    @Override
    public void onDragged(Offset offset) {
        this.onDragged = offset;
    }

    private BufferedImage resize(BufferedImage image) throws IOException {
        BufferedImage resizedBufferedImage = new BufferedImage(this.getWidth(), this.getWidth(), image.getType());
        Graphics2D g2d = resizedBufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        g2d.dispose();
        return resizedBufferedImage;
    }


}
