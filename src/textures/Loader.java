package textures;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Loader {

    public static final String RES_LOC = "res/";

    public Texture loadTexture(String imageName, float imageScale) {
        PixelData[][] imageData;

        ImageIcon imageIcon = new ImageIcon(RES_LOC + imageName + ".png");
        Image tmpImage = imageIcon.getImage();

        BufferedImage img = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        img.getGraphics().drawImage(tmpImage, 0, 0, null);
        tmpImage.flush();

        int width = (int) (img.getWidth() * imageScale);
        int height = (int) (img.getHeight() * imageScale);

        imageData = new PixelData[(int) (width)][(int) (height)];

        for (int x = 0; x < imageData.length; x += imageScale) {
            for (int y = 0; y < imageData[0].length; y += imageScale) {
                Color imageColor = new Color(img.getRGB((int) (x / imageScale), (int) (y / imageScale)), true);
                for (int i = 0; i < imageScale; i++) {
                    for (int j = 0; j < imageScale; j++) {
                        imageData[Math.min(x + i, imageData.length - 1)][Math.min(y + j, imageData[0].length - 1)] = new PixelData(imageColor);
                    }
                }
            }
        }

        System.out.println("Loaded " + imageName + ".png [" + width + "," + height + "]");
        return new Texture(imageData);
    }

}
