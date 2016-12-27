package com.ospiridonovn.network;

import com.ospiridonovn.data.Image;
import com.ospiridonovn.data.Label;
import com.ospiridonovn.network.layer.Layer;

import java.util.List;

/**
 * @author oleg (26.12.16).
 */
public class Network {
    private Layer[] layers;

    public Network() {
    }

    public Network(Layer[] layers) {
        this.layers = layers;
    }

    public void train(List<Image> images) {

    }

    public Label cumpute(Image image) {
        return Label.ZERO;
    }
}
