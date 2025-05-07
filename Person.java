/**
 * Represents a Person who is or isn't a fan of LLMs like ChatGPT.
 * Each Person has a name, features, and label field.
 * @author Showmick Das
 * @version 1.0
 */

public class Person {
    private String name;
    private double[] features;
    private int label;

    /**
     * Constructs a Person object.
     * @param name name of the person
     * @param features array of the features of the person which will dictate whether he's a fan of ChatGPT
     * @param label integer value indicating whether the person is a fan of ChatGPT
     */
    public Person(String name, double[] features, int label) {
        if (name == null || name.length() <= 0 || name.trim().isEmpty()) {
            this.name = "John Doe";
        } else {
            this.name = name;
        }

        if (features.length == 5) {
            for (int i = 0; i < features.length; i++) {
                if (features[i] < -1.0 || features[i] > 1.0) {
                features[i] = 0.0;
                }
            }
            this.features = features;
        } else {
            this.features = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        }

        if (label == 0 || label == 1) {
            this.label = label;
        } else {
            this.label = 1;
        }
    }

    /**
     * No-arg constructor for the Person object.
     */
    public Person() {
        this("John Doe", new double[]{0.0, 0.0, 0.0, 0.0, 0.0}, 1);
    }

    /**
     * Copy constructor for the Person object, which deep copies
     * the Person object, but shallow copies all the fields.
     * @param otherPerson the other Person object, whose fields you want to shallow copy
     */
    public Person(Person otherPerson) {
        this.name = otherPerson.name;
        this.features = otherPerson.features;
        this.label = otherPerson.label;
    }

    /**
     * Getter method for the name instance field.
     * @return returns the name field of the Person object
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter method for the features instance field.
     * @return returns the array of features for the Person object
     */
    public double[] getFeatures() {
        return this.features;
    }

    /**
     * Getter method for the label instance field.
     * @return returns the label indicating whether the person is a fan of ChatGPT
     */
    public int getLabel() {
        return this.label;
    }

    /**
     * Setter method for the name instance field.
     * @param name the name you want to set the current name to
     */
    public void setName(String name) {
        if (name!= null && name.length() > 0 && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    /**
     * Setter method for the features instance field.
     * @param feature the feature array you want to set the current feature to
     */
    public void setFeatures(double[] features) {
        if (features.length == 5) {
            for (int i = 0; i < features.length; i++) {
                if (features[i] < -1.0 && features[i] > 1.0) {
                    features[i] = 0.0;
                }
            }
            this.features = features;
        }
    }

    /**
     * Setter method for the label instance field.
     * @param label the label you want to set the current label to
     */
    public void setLabel(int label) {
        if (label == 0 || label == 1) {
            this.label = label;
        }
    }

    /**
     * Returns a formatted String describing and representing the specific 
     * Person object.
     * @return a String with details about the specific Person object
     */
    @Override
    public String toString() {
        return "This Person's name is " + this.name + " and they are " +
        ((this.label == 1) ? "a fan ":"not a fan ") + "of ChatGPT.";
    }

    /**
     * Returns true or false, indicating whether two Person object's are equal.
     * @param other the object being passed in for comparison 
     * @return a boolean indicating whether two Person object's are equal
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
        Person otherPerson = (Person) other;
        return this.name.equals(otherPerson.name) && this.label == otherPerson.label;
    }
}
