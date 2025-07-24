import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    
    @Test
    public void testModelConstructor() {
        Model model = new Model(1, ActivationType.RELU);
        
        assertEquals(1, model.getID());
        assertEquals(ActivationType.RELU, model.getActivationType());
        assertEquals(5, model.getWeights().length);
    }
    
    @Test
    public void testModelDefaultConstructor() {
        Model model = new Model();
        
        assertEquals(7, model.getID());
        assertEquals(ActivationType.ELU, model.getActivationType());
    }
    
    @Test
    public void testModelCopyConstructor() {
        Model original = new Model(5, ActivationType.SIGMOID);
        Model copy = new Model(original);
        
        assertEquals(original.getID(), copy.getID());
        assertEquals(original.getActivationType(), copy.getActivationType());
        assertArrayEquals(original.getWeights(), copy.getWeights());
    }
    
    @Test
    public void testPredict() {
        Model model = new Model(1, ActivationType.RELU);
        Person person = new Person("Test", new double[]{0.5, 0.3, -0.2, 0.1, 0.4}, 1);
        
        int prediction = model.predict(person);
        assertTrue(prediction == 0 || prediction == 1);
    }
    
    @Test
    public void testTrainInvalidEpochs() {
        Model model = new Model(1, ActivationType.RELU);
        Person[] data = {new Person()};
        
        // Should handle invalid epochs gracefully
        model.train(data, 0);
        model.train(data, -5);
        
        // No exception should be thrown
        assertTrue(true);
    }
    
    @Test
    public void testModelStats() {
        // Simply test that the method runs without error
        Model.printModelStats();
        assertTrue(Model.getTotalPredictions() >= 0);
        assertTrue(Model.getCorrectPredictions() >= 0);
    }
}
