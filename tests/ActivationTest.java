import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActivationTest {
    
    @Test
    public void testRelu() {
        assertEquals(0.0, Activation.relu(-5.0), 0.001);
        assertEquals(0.0, Activation.relu(0.0), 0.001);
        assertEquals(3.5, Activation.relu(3.5), 0.001);
    }
    
    @Test
    public void testSigmoid() {
        assertEquals(0.5, Activation.sigmoid(0.0), 0.001);
        assertTrue(Activation.sigmoid(5.0) > 0.9);
        assertTrue(Activation.sigmoid(-5.0) < 0.1);
    }
    
    @Test
    public void testElu() {
        assertEquals(2.0, Activation.elu(2.0, 1.0), 0.001);
        assertTrue(Activation.elu(-1.0, 1.0) < 0);
        assertEquals(0.0, Activation.elu(0.0, 1.0), 0.001);
    }
    
    @Test
    public void testEluInvalidAlpha() {
        // Should default alpha to 1.0 when invalid
        double result = Activation.elu(-1.0, -0.5);
        assertTrue(result < 0);
    }
}
