package textures;

import math.Vector2;

public class Animation {

    private SpriteSheet textureSheet;
    private long startTime;
    private int state, totalStates, msPerFrame;
    private boolean isRunning;

    public Animation(SpriteSheet textureSheet, int totalStates, int msPerFrame) {
        this.textureSheet = textureSheet;
        this.totalStates = totalStates;
        this.msPerFrame = msPerFrame;

        this.startTime = System.currentTimeMillis();
    }

    public Texture update() {
        if (isRunning) {
            int runTime = (int) (System.currentTimeMillis() - startTime);
            state = (int) Math.floor(runTime / msPerFrame);

            if (state >= totalStates) {
                start();
                state = 0;
            }

        }

        return textureSheet.getTextureAt(new Vector2(state, 0));
    }

    public void stop() {
        this.isRunning = false;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.isRunning = true;
    }

    public int getMsPerFrame() {
        return msPerFrame;
    }

    public void setMsPerFrame(int msPerFrame) {
        this.msPerFrame = msPerFrame;
    }

    public long getRuntime() {
        return System.currentTimeMillis() - startTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getTotalStates() {
        return totalStates;
    }
}
