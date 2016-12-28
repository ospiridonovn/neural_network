package com.ospiridonovn.network.layer;

import com.ospiridonovn.network.Neuron;

import java.util.ArrayList;

/**
 * @author oleg (27.12.16).
 */
public class SigmoidLayer extends BackpropLayer {

    private final int WEIGTH = 0;

    private final int DELTA = 0;

    public SigmoidLayer(int neuronsNumber) {
        neurons = new ArrayList<>(neuronsNumber);
        for (int i = 0; i < neuronsNumber; i++) {
            Neuron n = new Neuron();
            neurons.add(n);
        }
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
