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
    private ImageDisplay imageDisplay;


    @Override
    public void show(Image image) {
        this.image = image;
        this.imageDisplay.show(getBufferedImage(image.name()));
    }

    public ImagePresenter(Dimension dimension) {
        this.imageDisplay = new SwingImageDisplay(dimension);
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

}
