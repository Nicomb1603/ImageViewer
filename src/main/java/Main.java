import control.FileImageLoader;
import control.NextImageCommand;
import control.PreviousImageCommand;
import model.Image;

import java.io.File;

public class Main {
    public static final String root = "C:/Users/Usuario/Documents/Pictures";
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        Image image = new FileImageLoader(new File(root)).load();
        frame.imageDisplay().show(image);
        frame.add("<", new PreviousImageCommand(frame.imageDisplay()));
        frame.add(">", new NextImageCommand(frame.imageDisplay()));
        frame.setVisible(true);

    }
}
