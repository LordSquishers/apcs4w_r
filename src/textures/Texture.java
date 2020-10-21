package textures;

public class Texture {

    private PixelData[][] imageData;
    private int renderMode;

    public Texture(PixelData[][] imageData) {
        this.imageData = imageData;
    }

    public Texture(PixelData[][] imageData, int renderMode) {
        this.imageData = imageData;
        this.renderMode = renderMode;
    }

    public PixelData[][] getData() {
        return imageData;
    }

    public int getWidth() {
        return imageData.length;
    }

    public int getHeight() {
        return imageData[0].length;
    }

    public int getRenderMode() {
        return renderMode;
    }

    public Texture setRenderMode(int renderMode) {
        this.renderMode = renderMode;
        return this;
    }
}
