package com.ospiridonovn.network.layer;

/**
 * @author oleg (28.12.16).
 */
public abstract class BackpropLayer extends Layer {

    public void randomize(double min, double max) {
        neurons.forEach(neuron -> neuron.getWeightsList().forEach(weight ->
                weight = min + (max - min) * Math.random()));
    }

    public void computeBackwardError() {
        // TODO: 28.12.16 Write code
    }

    public void adjust() {
        // TODO: 28.12.16 Write code
    }

}
