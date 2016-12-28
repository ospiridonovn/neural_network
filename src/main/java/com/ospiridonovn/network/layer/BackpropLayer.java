package com.ospiridonovn.network.layer;

import java.util.Collections;

/**
 * @author oleg (28.12.16).
 */
public abstract class BackpropLayer extends Layer {

    public void randomize(double min, double max) {
        neurons.forEach(neuron -> {
            neuron.getWeightsList().forEach(weight ->
                    weight = min + (max - min) * Math.random());
            Collections.fill(neuron.getDeltas(), 0.0);
        });
    }

    public double[] computeBackwardError(double[] inputs, double[] errors) {
        double[] output = computeOutput();
        final int layerSize = getSize();
        double[] backwardError = new double[inputsNumber];

        for (int i = 0; i < inputsNumber; i++) {
            backwardError[i] = 0;
            for (int j = 0; j < layerSize; j++) {
                backwardError[i] += errors[j] * neurons.get(j).getWeightsList().get(i + 1) * output[j] * (1 - output[j]);
            }
        }
        return backwardError;
    }

    public void adjust(double[] input, double[] error, double rate, double momentum) {
        double[] output = computeOutput();
        final int layerSize = getSize();

        for (int i = 0; i < layerSize; i++) {
            final double grad = error[i] * (output[i] * (1 - output[i]));
            
            neurons.get(i).getDeltas().set(0, rate * grad + momentum * neurons.get(i).getDeltas().get(0));
            neurons.get(i).getWeightsList().set(0, neurons.get(i).getDeltas().get(0));

            for (int j = 0; j < inputsNumber; j++) {
                neurons.get(i).getDeltas().set(j + 1, rate * input[j] * grad + momentum * neurons.get(i).getDeltas().get(j + 1));
                neurons.get(i).getWeightsList().set(j + 1, neurons.get(i).getDeltas().get(0));
            }


        }

        // TODO: 28.12.16 Write code
    }

}
