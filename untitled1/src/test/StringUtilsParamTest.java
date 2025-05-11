import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsParamTest {
//    @ParameterizedTest(name="{0}->{1}")
//    @NullSource
//    @ValueSource(strings ={
//            "abc",
//            "",
//            "racecar",
//            "hello"
//    })
//    void testReverseCsv(String input){
//        String expected = (input == null) ? null : new StringBuilder(input).reverse().toString();
//        assertEquals(expected, StringUtils.reverse(input));
//    }
    @ParameterizedTest(name = "reverse({0}) => {1}")
    @CsvSource(
            value = {
                    "null, null",
                    "abc, cba",
                    "'', ''",
                    "racecar, racecar"
            },
            nullValues = "null"
    )
    void testReverse(String input, String expected) {
        assertEquals(expected, StringUtils.reverse(input));
    }
}
