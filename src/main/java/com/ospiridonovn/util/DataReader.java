package com.ospiridonovn.util;

import com.ospiridonovn.data.Image;
import com.ospiridonovn.data.Images;
import com.ospiridonovn.data.Label;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author oleg (20.12.16).
 */
public class DataReader {
    private File imagesFile = new File("mnist/train-images.idx3-ubyte");
    private File labelsFile = new File("mnist/train-labels.idx1-ubyte");

    public List<Image> readAndSave() throws IOException {
        FileInputStream imagesInputStream = new FileInputStream(imagesFile);
        FileInputStream labelsInputStream = new FileInputStream(labelsFile);

        Arrays.stream(Label.values()).forEach(x -> new File(Images.outputPath + "/" + x).mkdir());

        int s;
        int magicNumberImages = (imagesInputStream.read() << 24) | (imagesInputStream.read() << 16) | (imagesInputStream.read() << 8) | (imagesInputStream.read());
        int numberOfImages = (imagesInputStream.read() << 24) | (imagesInputStream.read() << 16) | (imagesInputStream.read() << 8) | (imagesInputStream.read());
        int numberOfRows  = (imagesInputStream.read() << 24) | (imagesInputStream.read() << 16) | (imagesInputStream.read() << 8) | (imagesInputStream.read());
        int numberOfColumns = (imagesInputStream.read() << 24) | (imagesInputStream.read() << 16) | (imagesInputStream.read() << 8) | (imagesInputStream.read());

        System.out.println(magicNumberImages);
        System.out.println(numberOfImages);
        System.out.println(numberOfRows);
        System.out.println(numberOfColumns);

        int magicNumberLabels = (labelsInputStream.read() << 24) | (labelsInputStream.read() << 16) | (labelsInputStream.read() << 8) | (labelsInputStream.read());
        int numberOfLabels = (labelsInputStream.read() << 24) | (labelsInputStream.read() << 16) | (labelsInputStream.read() << 8) | (labelsInputStream.read());

        System.out.println("\n");
        System.out.println(magicNumberLabels);
        System.out.println(numberOfLabels);

        int[] imgPixels = new int[Images.pixelsInImage];

        List<Image> images = new ArrayList<>();

        for(int i = 0; i < 1000; i++) {
            BufferedImage image = new BufferedImage(numberOfColumns, numberOfRows, BufferedImage.TYPE_INT_ARGB);

            if(i % 100 == 0) {System.out.println("Number of images extracted: " + i);}

            for(int p = 0; p < Images.pixelsInImage; p++) {
                int gray = 255 - imagesInputStream.read();
                imgPixels[p] = gray;
            }


            int label = labelsInputStream.read();

            image.setRGB(0, 0, numberOfColumns, numberOfRows, imgPixels, 0, numberOfColumns);
            Image img = new Image(image, imgPixels, Label.values()[label]);
            images.add(img);
//            img.saveAsFile();
        }
        return images;
    }
}
