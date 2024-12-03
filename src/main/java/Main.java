import control.FileImageLoader;
import control.NextImageCommand;
import control.PreviousImageCommand;
import model.Image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static final String root = "C:/Users/Usuario/Documents/Pictures";
    public static void main(String[] args) throws IOException {
        MainFrame frame = new MainFrame();
        Image image = new FileImageLoader(new File(root)).load();
        frame.imageDisplay().show(image);
        frame.add("<", new PreviousImageCommand(frame.imageDisplay()));
        frame.add(">", new NextImageCommand(frame.imageDisplay()));
        frame.setVisible(true);

    }
}
