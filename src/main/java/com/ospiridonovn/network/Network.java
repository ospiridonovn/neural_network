package com.ospiridonovn.network;

import com.ospiridonovn.data.Image;
import com.ospiridonovn.data.Label;
import com.ospiridonovn.network.layer.BackpropLayer;
import com.ospiridonovn.network.layer.Layer;

/**
 * @author oleg (26.12.16).
 */
public class Network {
    private Layer[] layers;
    private int layersNumber;
    private double[][] outputs;

    public Network() {
    }

    public Network(Layer[] layers, double min, double max) {
        this.layers = layers;
        this.layersNumber = layers.length;
        this.outputs = new double[this.layersNumber][];
    }

    public void randomize(double from, double to) {
        layers[0].setInputsSize(785);
        layers[1].setInputsSize(785);
        ((BackpropLayer) layers[0]).initAndSetRandomizeWeights(from, to);
        ((BackpropLayer) layers[1]).initAndSetRandomizeWeights(from, to);
    }

    public double learnNetwork(Image image, double rate, double momentum) {
        double[] startInputs = image.getData();

        layers[0].setOnlyInputs(startInputs);
        outputs[0] = layers[0].computeOutput();
        for (int i = 1; i < layersNumber; i++) {
            layers[i].setOnlyInputs(outputs[i - 1]);
            outputs[i] = layers[i].computeOutput();
        }

        // calculate error on last layer
        Layer layer = layers[layersNumber - 1];
        final int layerSize = layer.getSize();
        double[] layerErrors = new double[layerSize];
        double[] expectedLayerOutput = new double[layerSize];
        double totalError = calculateError(outputs[layersNumber - 1], image.getLabel());
        for (int i = 0; i < expectedLayerOutput.length; i++) {
            expectedLayerOutput[i] = i == image.getLabelAsInt() ? 1.0 : 0.0;
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

        for (int i = layersNumber - 1; i >= 0; i--, previousLayerErrors = layerErrors, previousLayer = layer) {
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
//
        return totalError;

    }
    
    private double calculateError(double[] output, Label expectedLabel) {
        double error = 0;
        for (int i = 0; i < output.length; i++) {
            error += Math.pow(output[i] - (expectedLabel.getValue() == i ? 1.0 : 0.0), 2);
        }
        return error / output.length;
    }

    public Label compute(Image image) {
        double[] startInputs = image.getData();
        layers[0].setOnlyInputs(startInputs);
        double[][] outputs = new double[layersNumber][];
//        ((BackpropLayer) layers[0]).initAndSetRandomizeWeights(0.01, 0.8);
        outputs[0] = layers[0].computeOutput();
        for (int i = 1; i < layersNumber; i++) {
            layers[i].setOnlyInputs(outputs[i - 1]);
//            ((BackpropLayer) layers[i]).initAndSetRandomizeWeights(0.01, 0.8);
            outputs[i] = layers[i].computeOutput();
        }
        double max = 0;
        int index = 0;
        for (int i = 0; i < outputs[layersNumber - 1].length; i++) {
            if (max < outputs[layersNumber - 1][i]) {
                max = outputs[layersNumber - 1][i];
                index = i;
            }
        }

        return Label.values()[index];
    }
}
