package control;

import model.Image;
import view.ImageDisplay;
import view.SwingImageDisplay;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImagePresenter implements Presenter{

    private Image image;
    /*A la hora de hacer el código, puedes cargar las imagenes previas y siguientes en onReleased, lo que optimizaría el onDragged,
    pues ya tendrías cargada la imagen cuando arrastras, pero desfavorecería el onReleased, ya que tiene que cargar
    la imagen previa y siguiente cada vez que sueltas. Por otro lado, podemos no hacer esto, de forma que el onReleased
    estaría optimizado, ya que no debe hacer ninguna carga, pero el onDragged sería lento ya que debe cargar la próxima/anterior
    imagen cada vez que arrastras. Me he decantado por favorecer el onReleased. Si quieres probar a favorecer el onDragged,
    solo debes descomentar las líneas que hay comentadas en este archivo*/
    //private BufferedImage nextImage;
    //private BufferedImage prevImage;
    private final ImageDisplay imageDisplay;
    private final Map<String, BufferedImage> imageCache;



    @Override
    public void show(Image image) {
        this.image = image;
        this.imageDisplay.paintImage(getBufferedImage(image.name()), 0);
        /*
        this.prevImage = getBufferedImage(this.image.prev().name());
        this.nextImage = getBufferedImage(this.image.next().name());*/
    }

    public ImagePresenter() {
        this.imageDisplay = new SwingImageDisplay();
        this.imageDisplay.onDragged(this::onDragged);
        this.imageDisplay.onReleased(this::onReleased);
        this.imageCache = new HashMap<>();
    }

    private void onReleased(int offset){
        if (Math.abs(offset) > imageDisplay.width()/2) {
            this.image = offset < 0 ? image.next() : image.prev();
            this.refresh();/*
            this.prevImage = getBufferedImage(this.image.prev().name());
            this.nextImage = getBufferedImage(this.image.next().name());*/
        }
        else{this.refresh();}
    }

    private void onDragged(int offset) {
        this.imageDisplay.clear();
        this.imageDisplay.paintImage(getBufferedImage(image.name()), offset);
        if (offset == 0) return;
        if (offset > 0) {
            this.imageDisplay.paintImage(getBufferedImage(image.prev().name()), offset - this.imageDisplay.width());
        }
        else{
            this.imageDisplay.paintImage(getBufferedImage(image.next().name()), offset + this.imageDisplay.width());
        }
    }

    @Override
    public Image image(){return this.image;}

    public ImageDisplay imageDisplay() {
        return imageDisplay;
    }


    private BufferedImage getBufferedImage(String name) {
        if (imageCache.containsKey(name)) {
            return imageCache.get(name);
        }

        try {
            BufferedImage image = ImageIO.read(new File(name));
            imageCache.put(name, image);
            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void refresh() {
        this.imageDisplay.clear();
        this.imageDisplay.paintImage(getBufferedImage(this.image.name()), 0);
    }

}
