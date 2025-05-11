import Mockito.InventoryClient;
import Mockito.OrderService;
import Mockito.PaymentClient;
import Mockito.PaymentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    InventoryClient inventory;

    @Mock
    PaymentClient payment;

    @InjectMocks
    OrderService service;

    @Test
    void testPlaceOrder_withAnyMatcher() throws PaymentException {
        when(inventory.isInStock(any(), eq(5))).thenReturn(true);
        doNothing().when(payment).charge(anyString(), anyDouble());

        boolean result = service.placeOrder("ITEM-XYZ", 5,100);
        assertTrue(result);

        verify(inventory).isInStock(anyString(), eq(5));
        verify(payment).charge(eq("ITEM-XYZ"),anyDouble());
    }

    @Test
    void testPlaceOrder_whenPaymentFails() throws PaymentException {
        when(inventory.isInStock("A100",1)).thenReturn(true);
        doThrow(new PaymentException("failed")).when(payment).charge("A100", 50);

        boolean result = service.placeOrder("A100",1,50);
        assertFalse(result);

        verify(payment, times(1)).charge("A100",50);
    }

    @Test
    void testPlaceOrder_outOfStockThenInStock() throws PaymentException {
        when(inventory.isInStock("B200", 2)).thenReturn(false).thenReturn(true);

        assertFalse(service.placeOrder("B200",2,20));
        verify(payment, never()).charge(anyString(), anyDouble());

        assertTrue(service.placeOrder("B200",2,20));
        verify(payment,times(1)).charge("B200",20);
    }

    @Test
    void testPlaceOrder_argumentCaptor() throws PaymentException {
        when(inventory.isInStock("C300", 3)).thenReturn(true);
        ArgumentCaptor<String> itemCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> amountCapture = ArgumentCaptor.forClass(Double.class);

        boolean result = service.placeOrder("C300", 3, 75.5);
        assertTrue(result);

        verify(payment).charge(itemCapture.capture(), amountCapture.capture());
        assertEquals("C300", itemCapture.getValue());
        assertEquals(75.5, amountCapture.getValue());
    }
}
