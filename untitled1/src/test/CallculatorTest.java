import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CallculatorTest {
    private final Calculator calc = new Calculator();

    @Test
    void testAdd(){
        Assertions.assertEquals(5, calc.add(2,3));
    }

    @Test
    void testSubTract(){
        Assertions.assertEquals(1,calc.subtract(4,3));
    }

    @Test
    void testMultiply(){
        Assertions.assertEquals(12,calc.multply(4,3));
    }

    @Test
    void testDivide(){
        Assertions.assertEquals(2.5, calc.divide(5,2));
    }

    @Test
    void testDivideByZero(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.divide(5,0));
    }
}
