package control;

import model.Image;

public class ImagePresenter implements Presenter{

    private Image image;

    @Override
    public void show(Image image) {
        this.image = image;
    }
}
