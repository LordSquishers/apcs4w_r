package main;

import input.Keyboard;
import light.Light;
import math.Vector2;
import render.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

public class Main extends Canvas implements Runnable {

    public static final int WIDTH = 1024, HEIGHT = WIDTH * 9 / 16;
    public static final String WINDOW_TITLE = "APCS4W_R";

    private Renderer renderer;
    private boolean running = false;

    private JFrame frame;

    private Thread thread;
    private BufferedImage image;
    private int[] pixels;

    private Keyboard keyboard;

    private List<Light> lights = new ArrayList<>();

    public Main() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame = new JFrame();
        initializeFrame();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        renderer = new Renderer(pixels);
        keyboard = new Keyboard();
        addKeyListener(keyboard);
    }

    public void init() {
        lights.add(new Light(new Vector2(100, 100), Color.WHITE, 100, 1));
    }

    public void update() {
        renderer.addLights(lights);
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        renderer.prepare();

        //entities, tiles, lights, etc.
        //renderer.setPixelsFrom(200, 200, 300, 300, new PixelData(0, 255, 255));
        renderer.processLights();

        renderer.render();

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();

        bs.show();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime(), now = 0, timer = System.currentTimeMillis();
        double nsPerTick = 1E9 / 60, delta = 0;
        int ticks = 0, frames = 0;

        init();

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            while (delta >= 1) {
                update();

                ticks++;
                delta--;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                frame.setTitle(WINDOW_TITLE + " | ups: " + ticks + " fps: " + frames);

                ticks = 0;
                frames = 0;
            }
        }
    }

    public void initializeFrame() {
        frame.setTitle(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        setFocusable(true);
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.start();
    }

    public static int RGBToInt(int red, int green, int blue) {
        int rgb = red;
        rgb = (rgb << 8) + green;
        rgb = (rgb << 8) + blue;
        return rgb;
    }

}
