package com.ospiridonovn.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author oleg (22.12.16).
 */
public class Images {
    public static int columns = 28;
    public static int rows = 28;
    public static int pixelsInImage = columns * rows;
    public static String outputPath = "results/";
    public static String imageFormat = "png";
    public static double pixelMaxValue;

    public static int[] labelsCount = new int[10];

    private List<Image> imagesList = new ArrayList<>();
}
