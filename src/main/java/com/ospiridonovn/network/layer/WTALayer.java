package com.ospiridonovn.network.layer;

/**
 * @author oleg (27.12.16).
 */
public class WTALayer extends Layer {

    private double minLevel;

    public WTALayer(int neuronsNumber, int inputsNumber, double minLevel) {
        super(neuronsNumber, inputsNumber);
        this.minLevel = minLevel;
    }


    @Override
    public double[] computeOutput() {
        return new double[0];
    }
}
