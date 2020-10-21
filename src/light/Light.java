package light;

import math.Vector2;

import java.awt.*;

public class Light {

    private Vector2 position;
    private Color color;
    private int radius, stepResolution;

    public Light(Vector2 position, Color color, int radius, int stepResolution) {
        this.position = position;
        this.color = color;
        this.radius = radius;
        this.stepResolution = stepResolution;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getStepResolution() {
        return stepResolution;
    }

    public void setStepResolution(int stepResolution) {
        this.stepResolution = stepResolution;
    }
}
