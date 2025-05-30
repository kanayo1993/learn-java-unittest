import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class test {
    private final Calculator calc = new Calculator();

    @Test
    void testAdd(){
        assertThat(calc.add(2,3))
                .as("2+3=5")
                .isEqualTo(5);
    }

    @Test
    void testNonEmptyFilters(){
        List<String> input = Arrays.asList("foo", "", "bar", null, "baz");
        List<String> result = ListUtils.nonEmpty(input);

        assertThat(result)
                .hasSize(3)
                .contains("foo", "bar", "baz")
                .allMatch(s -> !s.isEmpty());
    }

    @Test
    void testSqrtApproximation(){
        double actual = Calculator.sqrt(2.0);
        assertThat(actual)
                .as("√2は約1.4142")
                .isCloseTo(1.4142, within(0.0001));
    }

    @Test
    void testParsePositiveIntError(){
        assertThatThrownBy(() -> ListUtils.parsePositiveInt("-5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("positive");
        assertThatThrownBy(() -> ListUtils.parsePositiveInt("あいうえお"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("numeric");
    }
}
