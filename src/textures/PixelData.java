package textures;

import main.Main;

import java.awt.*;

public class PixelData {

    private int r, g, b, a;

    public PixelData(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 255;
    }

    public PixelData(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public PixelData(Color c) {
        this.r = c.getRed();
        this.g = c.getGreen();
        this.b = c.getBlue();
        this.a = c.getAlpha();
    }

    public PixelData(int rgb) {
        this.r = (rgb >> 16) & 0xFF;
        this.g = (rgb >> 8) & 0xFF;
        this.b = rgb & 0xFF;
        this.a = 255;
    }

    @Override
    public String toString() {
        return "PixelData{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                ", a=" + a +
                '}';
    }

    /**
     * Does not include alpha value.
     *
     * @return RGB Color (no A)
     */
    public Color toColor() {
        return new Color(r, g, b);
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int toRGB() {
        return Main.RGBToInt(r, g, b);
    }
}
