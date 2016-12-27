package com.ospiridonovn.network.layer;

import com.ospiridonovn.network.Neuron;

import java.util.List;

/**
 * @author oleg (27.12.16).
 */
public class SigmoidLayer extends Layer {

    private final int WEIGTH = 0;

    private final int DELTA = 0;

    public SigmoidLayer(int neuronsNumber, int inputsNumber) {
        super(neuronsNumber, inputsNumber);
    }


    @Override
    public double[] computeOutput() {
        double[] output = new double[neurons.size()];
        for (int i = 0; i < neurons.size(); i++) {
            output[i] = neurons.get(i).calculateOutput();
        }
        return output;
    }
}
