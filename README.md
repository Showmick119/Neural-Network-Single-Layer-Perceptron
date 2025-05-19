# Binary Classification Neural Network — Single-Layer Perceptron in Java

This project implements a fully functional **single-layer neural network** (perceptron) in Java, designed to predict whether a person is a fan of LLMs based on their **Big Five personality traits**. It demonstrates how foundational machine learning models can be built from scratch using clean **Object-Oriented Programming (OOP)** design principles.

---

## Key Features

- Custom Neural Network (Single-Layer Perceptron)
- Binary Classification Output (0 or 1)
- Random Weight & Bias Initialization
- Trainable with Epochs and Error Correction
- 3 Activation Functions Supported:
  - ReLU (Rectified Linear Unit)
  - Sigmoid
  - ELU (Exponential Linear Unit)
- Encapsulated Class Structure
- Statistical Tracking (Prediction Accuracy, Model History)

---

## Neural Network Concepts Implemented

| Concept            | Details |
|-------------------|---------|
| Input Features     | Big Five personality traits `[x1, x2, x3, x4, x5]` |
| Weighted Sum       | `y = w·x + b` where `w` = weights, `b` = bias |
| Activation Layer   | Applied to inputs (RELU, SIGMOID, ELU) |
| Output Function    | Sigmoid function to squash result to [0, 1] |
| Training           | Epoch-based updates using error calculation |
| Learning Rate      | Fixed at 0.01 for all weight updates |
| Prediction         | Binary output: `1` if prediction > 0.5, else `0` |
| Accuracy Tracking  | Tracks total and correct predictions globally |

---

## Class Overview

### `Person.java`
Encapsulates a person's:
- Name (validated)
- Five personality traits (validated between -1.0 and 1.0)
- Label (0 or 1) indicating ChatGPT preference

### `Model.java`
Core class representing the neural network:
- Stores weights, bias, and activation type
- Contains `train()` and `predict()` methods
- Tracks total predictions, correct predictions, and training history

### `ActivationType.java`
Enum listing supported activation functions:
- `RELU`, `SIGMOID`, `ELU`

### `Activation.java`
Static class implementing the formulas for:
- ReLU: `max(0, x)`
- Sigmoid: `1 / (1 + e^(-x))`
- ELU: `x if x > 0, else alpha * (e^x - 1)`

### `Predictor.java`
Main driver class:
- Instantiates Person and Model objects
- Trains each model on sample data
- Prints predictions and overall performance metrics

---

## Sample Output

```
Epoch 1: Error = 0.81
Epoch 2: Error = 0.77
...
Model 32: Predicted Showmick Das to not be a fan of ChatGPT.
Model Statistics
Total Predictions made: 62
Overall accuracy: 96.67%
```

---

## How to Compile and Run

Ensure all `.java` files are in the same folder:

```bash
javac *.java
java Predictor
```

---

## Possible Future Extensions

- Support for multi-layer (deep) networks
- Backpropagation and momentum-based optimizers
- Input from real CSV datasets
- Adjustable learning rate and activation via CLI
- Confusion matrix and precision-recall metrics

---

## Concepts Reinforced

- Neural network fundamentals
- Manual implementation of activation functions
- Model training using gradient descent-style error updates
- Binary classification logic
- Encapsulation, constructors, enums, and method overloading in Java

---

## Author and Motivation

**Showmick Das** 
- This project was developed to explore the intersection of foundational machine learning and Object Oriented Principles in Java.

---
