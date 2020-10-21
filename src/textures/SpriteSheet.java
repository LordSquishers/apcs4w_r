package textures;

import math.Vector2;

import java.util.Arrays;

public class SpriteSheet {

    private Texture sheet;
    private int width, height, tileSize;

    public SpriteSheet(Texture sheet, int tileSize) {
        this.sheet = sheet;
        this.width = sheet.getWidth();
        this.height = sheet.getHeight();
        this.tileSize = tileSize;
    }

    public Texture getTextureAt(Vector2 texLocation) {
        int tcX = (int) (texLocation.x * tileSize);
        int tcY = (int) (texLocation.y * tileSize);

        PixelData[][] sheetData = sheet.getData();
        PixelData[][] sprite = new PixelData[tileSize][tileSize];

        if (tcX > width || tcY > height) return sheet;

        for (int x = 0; x < tileSize; x++) {
            sprite[x] = Arrays.copyOfRange(sheetData[x + tcX], tcY, tcY + tileSize - 1);
        }

        return new Texture(sprite);
    }
}
