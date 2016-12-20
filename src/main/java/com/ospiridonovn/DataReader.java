package com.ospiridonovn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author oleg (20.12.16).
 */
public class DataReader {
    public void read() {
        try {
            File imagesFile = new File("sources/train-images.idx3-ubyte");
            File labelsFile = new File("sources/train-images.idx3-ubyte");

            FileInputStream imagesInputStream = new FileInputStream(imagesFile);
            FileInputStream labelsInputStream = new FileInputStream(labelsFile);

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
