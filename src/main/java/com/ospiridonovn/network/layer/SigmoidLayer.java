package com.ospiridonovn.network.layer;

/**
 * @author oleg (27.12.16).
 */
public class SigmoidLayer extends BackpropLayer {

    private final int WEIGTH = 0;

    private final int DELTA = 0;

    @Override
    public double[] computeOutput() {
        double[] output = new double[neurons.size()];
        for (int i = 0; i < neurons.size(); i++) {
            output[i] = neurons.get(i).calculateOutput();
        }
        return output;
    }
}
