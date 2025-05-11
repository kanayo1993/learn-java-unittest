import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {
    @Test
    void testReverseNormal(){
        assertEquals("cba", StringUtils.reverse("abc"), "abc -> cba");
    }

    @Test
    void testReverseEmpty(){
        assertEquals("", StringUtils.reverse(""), "空文字");
    }

    @Test
    void testReverseNull(){
        assertNull(StringUtils.reverse(null),"null");
    }
}
