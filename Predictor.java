/**
 * To run predictions for whether the object of the Person
 * class is a fan of LLMs like ChatGPT. Will allow us to test
 * our code and logic.
 * @author Showmick Das
 * @version 1.0
 */

public class Predictor {
    /**
     * The driver of the program. This will be used to create objects and
     * test the methods we created.
     * @param args the default parameter for the main() method in Java
     */
    public static void main(String[] args) {
       Person p1 = new Person("Showmick Das", new double[]{0.9, 0.6, -0.3, 0.2, 0.5}, 0);
       Person p2 = new Person();
       Person p3 = new Person("Ronjan Mian", new double[]{0.82, 0.54, -0.23, 0.21, 0.58}, 0);
       Person[] data = new Person[]{p1, p2, p3};

       Model m1 = new Model(32, ActivationType.ELU);
       Model m2 = new Model(47, ActivationType.SIGMOID);
       Model m3 = new Model(62, ActivationType.RELU);

       m1.train(data, 10);
       System.out.println(m1.predict(p1));
       System.out.println(m1.predict(p2));
       System.out.println(m1.predict(p3));
       Model.printModelStats();

       m2.train(data, 20);
       System.out.println(m2.predict(p1));
       System.out.println(m2.predict(p2));
       System.out.println(m2.predict(p3));
       Model.printModelStats();

       m3.train(data, 30);
       System.out.println(m3.predict(p1));
       System.out.println(m3.predict(p2));
       System.out.println(m3.predict(p3));
       Model.printModelStats();
    }
}
