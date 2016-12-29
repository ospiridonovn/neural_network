package com.ospiridonovn.network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author oleg (22.12.16).
 */
public class Neuron {
    private List<Double> inputsList;
    private List<Double> weightsList;
    private List<Double> deltas;
    private List<Double> errors;

    public List<Double> getErrors() {
        return errors;
    }

    public void setErrors(List<Double> errors) {
        this.errors = errors;
    }

    private double output;

    public List<Double> getInputsList() {
        return inputsList;
    }

    public List<Double> getWeightsList() {
        return weightsList;
    }

    public void initWeights() {
        weightsList = new ArrayList<>(inputsList.size());
        deltas = new ArrayList<>(inputsList.size());
    }

    public void setInputsData(double[] inputs) {
        setDataSize(inputs.length + 1);
        Arrays.stream(inputs).forEach(x -> inputsList.add(x));
        inputsList.add(0.0);
//        for (int i = 0; i < inputs.length + 1; i++) {
//            weightsList.add(0.0);
//            deltas.add(0.0);
//        }
    }

    public void setOnlyInputs(double[] inputs) {
        inputsList = new ArrayList<>(inputs.length + 1);
        Arrays.stream(inputs).forEach(x -> inputsList.add(x));
        inputsList.add(0.0);
    }

    public void setWeightsAndDeltasAsZero() {
        for (int i = 0; i < inputsList.size() + 1; i++) {
            weightsList.add(0.0);
            deltas.add(0.0);
        }
    }

    public void setDataSize(int size) {
        inputsList = new ArrayList<>(size);
        weightsList = new ArrayList<>(size);
        deltas = new ArrayList<>(size);
    }

    public void setWeights(double min, double max) {
        for (int i = 0; i < inputsList.size(); i++) {
            weightsList.add(min + (max - min) * Math.random());
        }
    }

    public void setDeltas(double delta) {
        for (int i = 0; i < inputsList.size(); i++) {
            deltas.add(delta);
        }
    }

    public void setWeightsAndDeltas(double min, double max) {
        setWeights(min, max);
        setDeltas(0.0);
    }

    public List<Double> getDeltas() {
        return deltas;
    }

    public void setDeltas(List<Double> deltas) {
        this.deltas = deltas;
    }

    public void setWeight(int index, double value) {
        weightsList.set(index, value);
    }

    public void setDelta(int index, double value) {
        deltas.set(index, value);
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
