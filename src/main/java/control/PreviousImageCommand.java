package control;

import view.ImageDisplay;

public class PreviousImageCommand implements Command{
    ImageDisplay imageDisplay;

    public PreviousImageCommand(ImageDisplay imageDisplay){this.imageDisplay = imageDisplay;}

    @Override
    public void execute() {
        System.out.println("Previous Pressed");
        imageDisplay.show(imageDisplay.image().prev());
    }
}
