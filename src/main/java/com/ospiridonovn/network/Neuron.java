package com.ospiridonovn.network;

import java.util.ArrayList;
import java.util.List;

/**
 * @author oleg (22.12.16).
 */
public class Neuron {
    private List<Double> inputsList;
    private List<Double> weightsList;
    private double output;

    public List<Double> getInputsList() {
        return inputsList;
    }

    public List<Double> getWeightsList() {
        return weightsList;
    }

    public void setDataSize(int size) {
        inputsList = new ArrayList<>(size);
        weightsList = new ArrayList<>(size);
    }

    public void setWeight(int index, double value) {
        weightsList.set(index, value);
    }

    private double calculateInput() {
        double input = 0.0;
        for (int i = 0; i < inputsList.size(); i++) {
            input += inputsList.get(i) * weightsList.get(i);
        }

        return input;
    }


    private double activationFunction(double input) {
        return 1 / (1 + Math.exp(-input));
    }

    public double calculateOutput() {
        return activationFunction(calculateInput());
    }

}
