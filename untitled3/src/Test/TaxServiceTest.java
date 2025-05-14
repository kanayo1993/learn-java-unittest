
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaxServiceTest {
    @Mock TaxRepository repo;
    @Mock NotificationClient notifier;
    @InjectMocks TaxService service;

    @Test
    void contextLoads(){
        assertNotNull(service);
    }

    @Test
    void testGetRecordExists(){
        TaxRecord rec = new TaxRecord(1,"TAXPAYER01", LocalDate.now().minusDays(1), 1000);
        when(repo.findById(1)).thenReturn(Optional.of(rec));

        TaxRecord found = service.getRecord(1);
        assertEquals("TAXPAYER01", found.getTaxpayerId());
    }

    @Test
    void testGetRecordNotFound(){
        when(repo.findById(999)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.getRecord(999));
    }

    @ParameterizedTest
    @ValueSource(ints ={0, -5})
    void testGetRecordInvalidId(int invalidId){
        assertThrows(IllegalArgumentException.class,
                () -> service.getRecord(invalidId));
    }

    @Test
    void testMarkAsPaidSuccess(){
        TaxRecord rec = new TaxRecord(2,"TAXPAYER02", LocalDate.now().minusDays(2), 2000);
        when(repo.findById(2)).thenReturn(Optional.of(rec));
        service.markAsPaid(2);
        assertTrue(rec.isPaid());
    }

    @Test
    void testMarkAsPaidFail(){
        TaxRecord rec = new TaxRecord(3, "TAXPAYER03", LocalDate.now().minusDays(3), 3000);
        when(repo.findById(3)).thenReturn(Optional.of(rec));
        service.markAsPaid(3);
        assertThrows(IllegalStateException.class,
                () -> service.markAsPaid(3));
    }

    @Test
    void testProcessOverdueTrue(){
        TaxRecord rec = new TaxRecord(4, "TAXPAYER04", LocalDate.now().minusDays(4), 4000);
        when(repo.findById(4)).thenReturn(Optional.of(rec));
        boolean result = service.processOverdue(4, "test@test.com");
        assertTrue(result);
    }

    @Test
    void testProcessOverduePaidFalse(){
        TaxRecord rec = new TaxRecord(5, "TAXPAYER05", LocalDate.now().minusDays(5), 5000);
        rec.markPaid();
        when(repo.findById(5)).thenReturn(Optional.of(rec));
        boolean result = service.processOverdue(5, "test@test.com");
        assertFalse(result);
    }

    @Test
    void testProcessOverdueNotYet(){
        TaxRecord rec = new TaxRecord(6, "TAXPAYER06", LocalDate.now(), 6000);
        when(repo.findById(6)).thenReturn(Optional.of(rec));
        boolean result = service.processOverdue(6, "aaa@aaa.com");
        assertFalse(result);
    }

    @Test
    void testProcessOverdueException() throws NotificationException {
        TaxRecord rec = new TaxRecord(7, "TAXPAYER07", LocalDate.now().minusDays(7), 7000);
        when(repo.findById(7)).thenReturn(Optional.of(rec));
        doThrow(new NotificationException("Test Exception")).when(notifier).sendOverdueNotice(7,"test7@test7.com");
        boolean result = service.processOverdue(7,"test7@test7.com");
        assertFalse(result);
    }

}
