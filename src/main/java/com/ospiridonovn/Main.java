package com.ospiridonovn;

import com.ospiridonovn.data.Image;
import com.ospiridonovn.network.Network;
import com.ospiridonovn.network.layer.BackpropLayer;
import com.ospiridonovn.network.layer.Layer;
import com.ospiridonovn.network.layer.SigmoidLayer;
import com.ospiridonovn.util.DataReader;

import java.io.IOException;
import java.util.List;

/**
 * @author oleg (28.12.16).
 */
public class Main {
    public static void main(String[] args) throws IOException {
        DataReader reader = new DataReader();
        List<Image> images = reader.readAndSave();
        Layer firstLayer = new SigmoidLayer(784);
        Layer lastLayer = new SigmoidLayer(10);
        Network network = new Network(new Layer[]{firstLayer, lastLayer}, 0.01, 0.8);
        for (int i = 0; i < 300; i++) {
            network.learnNetwork(images.get(i), 0.01, 0.01);
        }
        for (int i = 0; i < 20; i++) {
            System.out.println("Our value " + network.compute(images.get(980 + i)).getValue() + " - "
                    + images.get(980 + i).getLabel().getValue() + " must be" );
        }

    }
}
