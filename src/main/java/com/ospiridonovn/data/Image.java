package com.ospiridonovn.data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.ospiridonovn.data.Images.*;

/**
 * @author oleg (22.12.16).
 */
public class Image {
    private int[] pixels = new int[Images.pixelsInImage];
    private Label label = null;
    private Label computedLabel = null;
    private Long id = null;

    public Image(BufferedImage image, int[] pixels, Label label) {
        this.pixels = pixels;
        this.label = label;
    }

    public int getLabelAsInt() {
        return label.getValue();
    }

    public Label getLabel() {
        return label;
    }

    public double[] getData() {
        double[] data = new double[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            data[i] = 1.0 - pixels[i] / Images.pixelMaxValue;
        }
        return data;
    }

    public Image(int[] pixels) {
        this.pixels = pixels;
    }

    public void saveAsFile() throws IOException {
        BufferedImage image = new BufferedImage(columns, rows, BufferedImage.TYPE_INT_ARGB);
        image.setRGB(0, 0, columns, rows, pixels, 0, columns);

        File outputFile = new File(outputPath + "/" + label + "/" + ++labelsCount[label.getValue()] + "." + Images.imageFormat);
        outputFile.createNewFile();

        ImageIO.write(image, Images.imageFormat, outputFile);
    }
}
