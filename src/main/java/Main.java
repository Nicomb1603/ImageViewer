import model.Image;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static final String root = "C:/Users/Usuario/Documents/Pictures/adfadf.jpg";
    public static void main(String[] args) throws IOException {
        Image image = new Image() {
            @Override
            public String name() {
                return root;
            }

            @Override
            public Image next() {
                return null;
            }

            @Override
            public Image prev() {
                return null;
            }
        };
        MainFrame mainFrame = new MainFrame(image);
        mainFrame.setVisible(true);

    }
}
