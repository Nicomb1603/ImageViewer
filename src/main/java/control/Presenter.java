package control;

import model.Image;
import view.ImageDisplay;

public interface Presenter {
    void show(Image image);
    ImageDisplay imageDisplay();
    Image image();
}
