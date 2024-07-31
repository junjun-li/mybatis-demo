import com.imooc.junit.Calculator;
import org.junit.Test;

public class CalculatorTest {
    private Calculator calculator = new Calculator();

    @Test
    public void add() {
        int res = calculator.add(1, 2);
        System.out.println(res);
    }

    @Test
    public void subtract() {
        int res = calculator.subtract(1, 2);
        System.out.println(res);
    }

    @Test
    public void multiply() {
        int res = calculator.multiply(1, 2);
        System.out.println(res);
    }

    @Test
    public void divide() {
        float res = calculator.divide(1, 2);
        System.out.println(res);
    }

    @Test
    public void divide1() {
        float res = calculator.divide(1, 0);
        System.out.println(res);
    }
}
