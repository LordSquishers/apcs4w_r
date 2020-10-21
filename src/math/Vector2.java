package math;

import java.awt.*;

public class Vector2 {

    // Members
    public double x;
    public double y;

    // Constructors
    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Point pos) {
        this.x = pos.x;
        this.y = pos.y;
    }

    public Vector2(double speed) {
        this.x = speed;
        this.y = speed;
    }

    public static Vector2 add(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x + v2.x, v1.y + v2.y);
    }

    public static Vector2 subtract(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x - v2.x, v1.y - v2.y);
    }

    // Compare two vectors
    public boolean equals(Vector2 other) {
        return (this.x == other.x && this.y == other.y);
    }

    public static double distance(Vector2 a, Vector2 b) {
        double v0 = b.x - a.x;
        double v1 = b.y - a.y;
        return Math.sqrt(v0 * v0 + v1 * v1);
    }

    public static Vector2 xyDistance(Vector2 a, Vector2 b) {
        double v0 = b.x - a.x;
        double v1 = b.y - a.y;
        return new Vector2(v0, v1);
    }

    public double distance(Vector2 b) {
        return distance(this, b);
    }

    public Vector2 xyDistance(Vector2 b) {
        return xyDistance(this, b);
    }

    public void normalize() {
        double length = Math.sqrt(x * x + y * y);

        if (length != 0.0) {
            double s = 1.0f / (double) length;
            x = (double) (x * s);
            y = (double) (y * s);
        }
    }

    public double length() {
        return length(this);
    }

    public static double length(Vector2 a) {
        return Math.sqrt(a.x * a.x + a.y * a.y);
    }

    @Override
    public String toString() {
        return "Vector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Vector2 subtract(double radius) {
        this.x -= radius;
        this.y -= radius;

        return this;
    }

    public Vector2 subtract(Vector2 vector2) {
        return subtract(this, vector2);
    }

    public Vector2 add(Vector2 vector2) {
        return add(this, vector2);
    }

    public Vector2 add(double radius) {
        return add(this, new Vector2(radius));
    }

    public static double getAngleToVector(Vector2 origin, Vector2 pos) {
        int dx = (int) (pos.x - origin.x);
        int dy = (int) (pos.y - origin.y);

        double theta = Math.atan2(-dy, -dx);
        return (int) (theta * 180 / Math.PI) + 90;
    }

    public Vector2 divide(double i) {
        return divide(this, new Vector2(i, i));
    }

    public static Vector2 divide(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x / v2.x, v1.y / v2.y);
    }
}
