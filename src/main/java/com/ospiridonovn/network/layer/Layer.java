package com.ospiridonovn.network.layer;

import com.ospiridonovn.network.Neuron;

import java.util.ArrayList;
import java.util.List;

/**
 * @author oleg (26.12.16).
 */
public abstract class Layer {
    protected List<Neuron> neurons;
    protected int inputsNumber;

//    public Layer(List<Neuron> neurons) {
//        this.neurons = neurons;
//    }
//    public Layer(int neuronsNumber, int inputsNumber) {
//        neurons = new ArrayList<>(neuronsNumber);
//        neurons.forEach(x -> x.setDataSize(inputsNumber));
//        this.inputsNumber = inputsNumber;
//    }

    public void addNeuron(Neuron neuron) {
        neurons.add(neuron);
    }

    public int getSize() {
        return neurons.size();
    }

    abstract public double[] computeOutput();
}
