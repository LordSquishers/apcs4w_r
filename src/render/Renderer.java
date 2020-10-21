package render;

import entity.Entity;
import light.Light;
import main.Main;
import math.Vector2;
import textures.PixelData;
import textures.Texture;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Renderer {

    public static final int PIXEL_RESOLUTION = 1;
    public static final int RES_WIDTH = Main.WIDTH / PIXEL_RESOLUTION;
    public static final int RES_HEIGHT = Main.HEIGHT / PIXEL_RESOLUTION;
    public static final PixelData BG_COLOR = new PixelData(Color.BLACK);

    private List<Entity> entities = new ArrayList<>();
    private List<Light> lights = new ArrayList<>();

    private int[] pixels;

    public PixelData[][] pixelData = new PixelData[RES_WIDTH][RES_HEIGHT];

    public Renderer(int[] pixels) {
        this.pixels = pixels;
        prepare();
    }

    // "render" method
    public void render() {
        renderPixelData();
        entities.clear();
        lights.clear();
    }

    private void renderPixelData() {
        for (int y = 0, pos = 0; y < Main.HEIGHT; y++) {
            for (int x = 0; x < Main.WIDTH; x++, pos++) {
                pixels[pos] = pixelData[x / PIXEL_RESOLUTION][y / PIXEL_RESOLUTION].toRGB();
            }
        }
    }

    //
//    public void processEntities() {
//        for (Entity e : entities) {
//            Texture currentTex = e.hasComponent("Animation") ? (Texture) e.getComponent("Animation").execute() : e.getTexture();
//            if(currentTex == null) continue;
//            PixelData[][] data = currentTex.getData();
//
//            Vector2 screenPos = e.getPosition();
//            Vector2 centerPoint = new Vector2(screenPos.x + e.getTexture().getWidth() / 2, screenPos.y + e.getTexture().getHeight() / 2);
//
//            for (int x = 0; x < data.length; x++) {
//                for (int y = 0; y < data[0].length; y++) {
//                    if (data[x][y].getA() < 127) continue;
//
//                    Vector2 localPosition = Vector2.add(screenPos, new Vector2(x, y));
//
//                    setPixelAt(localPosition, data[x][y].toPixelData(), e.getTexture().getRenderMode());
//                }
//            }
//        }
//    }
//
    public void processLights() {
        for (Light l : lights) {
            for (int i = 1; i <= l.getStepResolution(); i++) {
                drawCircle(l.getPosition(), (int) (Math.abs(Math.sin(System.currentTimeMillis() / 500.) / 20 + 1) * (l.getRadius() / i)), new PixelData(l.getColor()));
            }
        }
    }

    public void prepare() {
        for (PixelData[] row : pixelData) {
            Arrays.fill(row, null);
        }
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public void drawLine(Vector2 pos, int angleInDegrees, int length, PixelData c, int renderMode) {
        double angle = (angleInDegrees - 90) * Math.PI / 180.;

        for (int i = 0; i < length; i++) {
            double xPos = pos.x + (i * Math.cos(angle));
            double yPos = pos.y + (i * Math.sin(angle));

            setPixelAt((int) (xPos), (int) (yPos), c);
        }
    }

    public void setPixelAt(int x, int y, PixelData color) {
        if (x < 0 || x > RES_WIDTH - 1 || y < 0 || y > RES_HEIGHT - 1) return;
        PixelData current = pixelData[x][y];
        int newMix = color.getA() / 255;
        int oldMix = (1 - newMix);

        int red = Math.max(color.getR() * newMix + current.getR() * oldMix, 0);
        int green = Math.max(color.getG() * newMix + current.getG() * oldMix, 0);
        int blue = Math.max(color.getB() * newMix + current.getB() * oldMix, 0);

        pixelData[x][y] = new PixelData(red, green, blue);
    }

    public void setPixelAt(Vector2 pos, PixelData color) {
        setPixelAt((int) pos.x, (int) pos.y, color);
    }

    public void setPixelsFrom(int x1, int y1, int x2, int y2, PixelData color) {
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                setPixelAt(x, y, color);
            }
        }
    }

    public void drawCircle(Vector2 centerPos, int radius, PixelData color) {
        PixelData[][] data = new PixelData[RES_WIDTH][RES_HEIGHT];

        for (int angle = 0; angle <= 360; angle += 1) {
            for (int r = 0; r < radius; r++) {
                int x1 = (int) (r * Math.cos(angle * Math.PI / 180));
                int y1 = (int) (r * Math.sin(angle * Math.PI / 180));

                if (centerPos.x + x1 < 0 || centerPos.y + y1 < 0 || centerPos.x + x1 > RES_WIDTH - 1 || centerPos.y + y1 > RES_HEIGHT - 1)
                    continue;
                data[(int) (x1 + centerPos.x)][(int) (y1 + centerPos.y)] = color;
            }
        }

        for (int x = 0; x < data.length; x++) {
            for (int y = 0; y < data[0].length; y++) {
                if (data[x][y] == null || data[x][y].getA() < 127) continue;

                setPixelAt(new Vector2(x, y), data[x][y]);
            }
        }
    }

    public void renderTexture(Texture texture) {
        PixelData[][] data = texture.getData();

        for (int x = 0; x < RES_WIDTH; x++) {
            for (int y = 0; y < RES_HEIGHT; y++) {
                if (data[x][y] == null) continue;
                setPixelAt(x, y, data[x][y]);
            }
        }
    }

    public PixelData getPixelAt(int x, int y) {
        return pixelData[x][y];
    }

    public void addEntities(List<Entity> entities) {
        this.entities.addAll(entities);
    }

    public void addLights(List<Light> lights) {
        this.lights.addAll(lights);
    }
}
