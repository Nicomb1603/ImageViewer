package control;

import model.Image;
import view.ImageDisplay;
import view.SwingImageDisplay;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePresenter implements Presenter{

    private Image image;
    private BufferedImage nextImage;
    private BufferedImage prevImage;
    private ImageDisplay imageDisplay;


    @Override
    public void show(Image image) {
        this.image = image;
        this.imageDisplay.paintImage(getBufferedImage(image.name()), 0);
        this.prevImage = getBufferedImage(this.image.prev().name());
        this.nextImage = getBufferedImage(this.image.next().name());
    }

    public ImagePresenter() {
        this.imageDisplay = new SwingImageDisplay();
        this.imageDisplay.onDragged(this::onDragged);
        this.imageDisplay.onReleased(this::onReleased);

    }

    private void onReleased(int offset){
        if (Math.abs(offset) > imageDisplay.width()/3) {
            this.image = offset < 0 ? image.next() : image.prev();
            this.prevImage = getBufferedImage(this.image.prev().name());
            this.nextImage = getBufferedImage(this.image.next().name());
        }
        this.refresh();
    }

    private void onDragged(int offset) {
        this.imageDisplay.clear();
        this.imageDisplay.paintImage(getBufferedImage(image.name()), offset);
        if (offset == 0) return;
        if (offset > 0) {
            this.imageDisplay.paintImage(prevImage, offset - this.imageDisplay.width());
        }
        else{
            this.imageDisplay.paintImage(nextImage, offset + this.imageDisplay.width());
        }
    }

    @Override
    public Image image(){return this.image;}

    public ImageDisplay imageDisplay() {
        return imageDisplay;
    }


    private BufferedImage getBufferedImage(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void refresh() {
        this.imageDisplay.clear();
        this.imageDisplay.paintImage(getBufferedImage(this.image.name()), 0);
    }

}
