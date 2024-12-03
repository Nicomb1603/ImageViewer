package view;

import model.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface ImageDisplay {
    void show(Image image);
    Image image();
    Dimension size();
}
