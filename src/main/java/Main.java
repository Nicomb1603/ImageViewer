import io.FileImageLoader;
import control.NextImageCommand;
import control.PreviousImageCommand;
import model.Image;

import java.io.File;

public class Main {
    public static final String root = "Pictures";
    public static void main(String[] args) {
        MainFrame frame = MainFrame.createMainframe();
        Image image = FileImageLoader.createFileImageLoader(new File(root)).load();
        frame.presenter().show(image);
        frame.add("<", PreviousImageCommand.createCommand(frame.presenter()))
                .add(">", NextImageCommand.createCommand(frame.presenter()))
                .setVisible(true);

    }
}
