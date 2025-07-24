import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    
    @Test
    public void testPersonConstructor() {
        double[] features = {0.5, -0.3, 0.8, 0.2, -0.1};
        Person person = new Person("Alice", features, 1);
        
        assertEquals("Alice", person.getName());
        assertArrayEquals(features, person.getFeatures());
        assertEquals(1, person.getLabel());
    }
    
    @Test
    public void testPersonDefaultConstructor() {
        Person person = new Person();
        
        assertEquals("John Doe", person.getName());
        assertEquals(5, person.getFeatures().length);
        assertEquals(1, person.getLabel());
    }
    
    @Test
    public void testPersonInvalidName() {
        Person person = new Person("", new double[]{0.0, 0.0, 0.0, 0.0, 0.0}, 1);
        assertEquals("John Doe", person.getName());
    }
    
    @Test
    public void testPersonInvalidFeatures() {
        Person person = new Person("Bob", new double[]{2.0, -2.0, 0.5}, 1);
        assertEquals(5, person.getFeatures().length);
    }
    
    @Test
    public void testPersonInvalidLabel() {
        Person person = new Person("Charlie", new double[]{0.0, 0.0, 0.0, 0.0, 0.0}, 5);
        assertEquals(1, person.getLabel());
    }
}
