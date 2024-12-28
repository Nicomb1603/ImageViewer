import io.FileImageLoader;
import control.NextImageCommand;
import control.PreviousImageCommand;
import model.Image;

import java.io.File;

public class Main {
    public static final String root = "Pictures";
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        Image image = new FileImageLoader(new File(root)).load();
        frame.presenter().show(image);
        frame.add("<", new PreviousImageCommand(frame.presenter()))
                .add(">", new NextImageCommand(frame.presenter()))
                .setVisible(true);

    }
}
