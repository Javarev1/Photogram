package codes.revqz.photogram.pipeline;

import java.awt.image.BufferedImage;

public record Tile(int column, int row, BufferedImage pixels, String fingerprint) {
}
