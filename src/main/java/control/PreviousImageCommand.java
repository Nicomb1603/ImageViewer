package control;

import view.ImageDisplay;

public class PreviousImageCommand implements Command{
    ImageDisplay imageDisplay;

    public PreviousImageCommand(ImageDisplay imageDisplay){this.imageDisplay = imageDisplay;}

    @Override
    public void execute() {
        imageDisplay.show(imageDisplay.image().prev());
    }
}
