package com.ospiridonovn.network;

import com.ospiridonovn.data.Image;
import com.ospiridonovn.data.Label;
import com.ospiridonovn.network.layer.BackpropLayer;
import com.ospiridonovn.network.layer.Layer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author oleg (26.12.16).
 */
public class Network {
    private Layer[] layers;
    private int layersNumber;
    private double[][] outputs;

    public Network() {
    }

    public Network(Layer[] layers) {
        this.layers = layers;
        this.layersNumber = layers.length;
        this.outputs = new double[this.layersNumber][];
    }

    public void train(List<Image> images) {
        List<Double> startInputs = new ArrayList<>(images.size());
        images.forEach(image -> Arrays.stream(image.getData()).forEach(startInputs::add));
        layers[0].setInputs(startInputs.stream().mapToDouble(Double::doubleValue).toArray());
        
        outputs[0] = layers[0].computeOutput();
        for (int i = 1; i < layersNumber; i++) {
            layers[i].setInputs(outputs[i - 1]);
            outputs[i] = layers[i].computeOutput();
        }

        int layerSize = layers[layers.length - 1].getSize();
        double[] error = new double[layerSize];

    }

    public double learnNetwork(Image image, double rate, double momentum) {
        double[] startInputs = image.getData();

        layers[0].setInputs(startInputs);
        outputs[0] = layers[0].computeOutput();
        for (int i = 1; i < layersNumber; i++) {
            layers[i].setInputs(outputs[i - 1]);
            outputs[i] = layers[i].computeOutput();
        }

        // calculate error on last layer
        Layer layer = layers[layersNumber - 1];
        final int layerSize = layer.getSize();
        double[] layerErrors = new double[layerSize];
        double totalError = 0;
        double[] expectedLayerOutput = new double[layerSize];
        for (int i = 0; i < expectedLayerOutput.length; i++) {
            expectedLayerOutput[i] = i == image.getLabelAsInt() ? 1 : 0;
        }

        for (int i = 0; i < layerSize; i++) {
            layerErrors[i] = expectedLayerOutput[i] - outputs[layersNumber - 1][i];
        }

        double maxValue = expectedLayerOutput[0];
        for (int i = 1; i < expectedLayerOutput.length; i++) {
            if (maxValue < expectedLayerOutput[i])
                maxValue = expectedLayerOutput[i];
        }

        if (layer instanceof BackpropLayer) {
            ((BackpropLayer) layer).adjust(layersNumber == 1 ? startInputs : outputs[layersNumber - 2],
                    layerErrors, rate, momentum);
        }

        double[] previousLayerErrors = layerErrors;
        Layer previousLayer = layer;

        for (int i = layersNumber - 2; i >= 0; i--, previousLayerErrors = layerErrors, previousLayer = layer) {
            layer = layers[i];

            if (previousLayer instanceof BackpropLayer) {
                layerErrors = ((BackpropLayer) previousLayer).computeBackwardError(outputs[i], previousLayerErrors);
            } else {
                layerErrors = previousLayerErrors;
            }

            if (layer instanceof BackpropLayer) {
                ((BackpropLayer) layer).adjust(i == 0 ? startInputs : outputs[i - 1], layerErrors, rate, momentum);
            }

        }

        return 1 - maxValue;

    }

    public Label cumpute(Image image) {
        return Label.ZERO;
    }
}
