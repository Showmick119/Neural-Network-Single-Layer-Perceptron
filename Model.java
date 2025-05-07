import java.util.Random;

/**
 * Our single-layer perceptron model, the most classic and foundational neural network model.
 * Built on 5 steps, a single input layer, weights, linear classification through summation
 * and bias, activation functions like Rectified Linear Unit, Exponential Linear Unit, 
 * Sigmoid function, and then finally our output layer which returns 0 or 1 (binary classification),
 * indicating whether or not a given Person object is a fan of LLMs like ChatGPT, based on their
 * characteristics like conscientiousness, agreeableness, neuroticism, openness, and extraversion.
 * @author Showmick Das
 * @version 1.0
 */

public class Model {
    private int id;
    private double[] weights;
    private double bias;
    private ActivationType activationType;
    private static String modelHistory = "";
    private static int totalPredictions = 0;
    private static int correctPredictions = 0;

    /**
     * Constructor for the Model class.
     * @param id the unique id for the specific Model Object
     * @param activationType the activation type for the Model Object
     */
    public Model(int id, ActivationType activationType) {
        this.id = id;
        this.activationType = activationType;
        this.weights = new double[5];
        Random rand = new Random();
        for (int i = 0; i < this.weights.length; i++) {
            this.weights[i] = rand.nextDouble() - 1.0;
        }
        this.bias = rand.nextDouble() - 1.0;
    }

    /** 
     * No-arg constructor for the Model class.
    */
    public Model() {
        this(7, ActivationType.ELU);
    }

    /**
     * Copy constructor for the Model class.
     * @param otherModel the other Model object, whose fields will be shallow copied
     */
    public Model(Model otherModel) {
        this.id = otherModel.id;
        this.activationType = otherModel.activationType;
        this.weights = new double[otherModel.weights.length];
        for (int i = 0; i < otherModel.weights.length; i++) {
            this.weights[i] = otherModel.weights[i];
        }
        this.bias = otherModel.bias;
    }

    /**
     * To train on data for a certain number epochs, to improve the weights array
     * @param data contains the Person objects
     * @param epochs the number of times you want to train and adjust the weights for maximum precision and accuracy
     */
    public void train(Person[] data, int epochs) {
        if (epochs < 1) {
            System.out.println("Error! Invalid number of epochs. The model must train " + 
            "on data at least once. Please try again.");
            return;
        }
        for (int i = 0; i <= epochs; i++) {
            for (Person p: data) {
                double[] activated = new double[p.getFeatures().length];
                for (int j = 0; j < activated.length; j++) {
                    if (this.activationType.name().equals("ELU")) {
                        activated[j] = Activation.elu(p.getFeatures()[j], 1.0);
                    } else if (this.activationType.name().equals("RELU")) {
                        activated[j] = Activation.relu(p.getFeatures()[j]);
                    } else if (this.activationType.name().equals("SIGMOID")) {
                        activated[j] = Activation.sigmoid(p.getFeatures()[j]);
                    } else {
                        System.out.println("Error! You are attempting to use an invalid " + 
                        "activation function. Please try again.");
                    }
                }
                double weightedSum = 0;
                for (int j = 0; j < activated.length; j++) {
                    weightedSum += (this.weights[j] * activated[j]);
                }
                weightedSum += this.bias;
                double predicted_value = Activation.sigmoid(weightedSum);
                double error_value = p.getLabel() - predicted_value;

                for (int j = 0; j < this.weights.length; j++) {
                    this.weights[j] = this.weights[j] + (0.01 * error_value * activated[j]);
                }
                this.bias = this.bias + (0.01 * error_value);

                System.out.printf("Epoch %d: Error = %4.2f%n", i, error_value);
            }
        }
        modelHistory += "Model " + this.id + ": Trained on " + data.length + " data points for " +
        epochs + " epochs.\n";
    }

    /**
     * To predict whether the specific person is a fan or not of ChatGPT,
     * based on their features and the updated weights to minimize deviance
     * from their label instance field, which indicates whether they are
     * actually a fan of ChatGPT.
     * @param p the specific Person object who you want to make the Prediction for
     * @return the integer value of 1 or 0, indicating whether they are a fan of ChatGPT
     */
    public int predict(Person p) {
        int final_prediction;
        double[] activated = new double[p.getFeatures().length];
        for (int i = 0; i < activated.length; i++) {
            if (this.activationType.name().equals("ELU")) {
                activated[i] = Activation.elu(p.getFeatures()[i], 1.0);
            } else if (this.activationType.name().equals("RELU")) {
                activated[i] = Activation.relu(p.getFeatures()[i]);
            } else if (this.activationType.name().equals("SIGMOID")) {
                activated[i] = Activation.sigmoid(p.getFeatures()[i]);
            } else {
                System.out.println("Error! You are attempting to use an invalid " +
                "activation function. Please try again.");
            }
        }
        double weightedSum = 0;
        for (int i = 0; i < this.weights.length; i++) {
            weightedSum += (this.weights[i] * activated[i]);
        }
        weightedSum += this.bias;
        double predicted_value = Activation.sigmoid(weightedSum);
        if (predicted_value > 0.5) {
            final_prediction = 1;
        } else {
            final_prediction = 0;
        }
        totalPredictions++;
        if (final_prediction == p.getLabel()) {
            correctPredictions++;
        }
        modelHistory += "Model " + this.id + ": Predicted " + p.getName() + " to " +
        ((final_prediction == 0) ? "not":"") + " be a fan of ChatGPT.\n";
        return final_prediction;
    }

    /**
     * Prints out the stats of the model based on the data stored in the static fields.
     */
    public static void printModelStats() {
        double accuracy;
        if (totalPredictions < 1) {
            accuracy = 0;
        } else {
            accuracy = (((double) correctPredictions) / totalPredictions) * 100;
        }
        System.out.println("Model Statistics");
        System.out.printf("Total Predictions made: %d%n", totalPredictions);
        System.out.printf("Overall accuracy: %4.2f%%%n", accuracy);
        System.out.println("Model History:");
        if (modelHistory.trim().isEmpty()) {
            System.out.println("None!");
        } else {
            System.out.println(modelHistory);
        }
    }

    /**
     * Getter method for the instance field of id.
     * @return returns the unique integer for specific Model object
     */
    public int getID() {
        return this.id;
    }

    /**
     * Getter method for the instance field of weights.
     * @return returns the array of weights for the specific Model object
     */
    public double[] getWeights() {
        return this.weights;
    }

    /**
     * Getter method for the instance field of id.
     * @return returns the unique integer id of the specific Model object
     */
    public double getBias() {
        return this.bias;
    }

    /**
     * Getter method for the instance field of activationType.
     * @return returns the activation type of the specific Model object
     */
    public ActivationType getActivationType() {
        return this.activationType;
    }

    /**
     * Getter method for the static field of modelHistory.
     * @return returns the history of the Model class in String format
     */
    public static String getModelHistory() {
        return modelHistory;
    }

    /**
     * Getter method for the static field of totalPredictions.
     * @return returns the integer number of total predictions the Model class has made
     */
    public static int getTotalPredictions() {
        return totalPredictions;
    }

    /**
     * Getter method for the static field of correctPredictions.
     * @return returns the integer number of correct predictions the Model class has made
     */
    public static int getCorrectPredictions() {
        return correctPredictions;
    }

    /**
     * Setter method for the id instance field for Model.
     * @param id the id you want to set the current Model to
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * We don't want to check whether the two objects share the same reference,
     * but we rather want to check whether they are literally the same, in the sense
     * that their fields store the same values.
     * @param other the object being passed in for comparison
     * @return a boolean indicating whether two Model object's are equal
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!other.getClass().equals(this.getClass())) {
            return false;
        }
        Model otherModel = (Model) other;
        if (this.id == otherModel.id) {
            return true; /* As the id is UNIQUE to each model. Being
            equals means they are the same model.*/
        }
        if (this.bias != otherModel.bias) {
            return false;
        }
        if (!this.activationType.name().equals(otherModel.activationType.name())) {
            return false;
        }
        for (int i = 0; i < this.weights.length; i++) {
            if (this.weights[i] != otherModel.weights[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a formatted String describing and representing the specific 
     * Model object.
     * @return a String with details about the specific Model object
     */
    @Override
    public String toString() {
        return "This Model's id is " + this.id + " and it's activation " +
        "function type is " + this.activationType.name().toLowerCase() + ".";
    }
}
