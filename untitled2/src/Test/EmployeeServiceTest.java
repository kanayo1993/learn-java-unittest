import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository repo;

    @InjectMocks
    EmployeeService service;

    @Test
    void contextLoads(){
        assertNotNull(service);
    }

    @Test
    void testGetEmployeeExists(){
        when(repo.findById(1)).thenReturn(Optional.of(new Employee(1,"Yamada")));
        assertEquals("Yamada", service.getEmployeeDetails(1).getName());
    }

    @Test
    void testGetEmployeeNotFound(){
        when(repo.findById(999)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class,
                () -> service.getEmployeeDetails(999));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void testGetEmployeeInvalidId(int id){
        assertThrows(IllegalArgumentException.class, () -> service.getEmployeeDetails(id));
    }

    @Test
    void testMultipleAssertsAndVerify(){
        when(repo.findById(2)).thenReturn(Optional.of(new Employee(2, "Sato")));
        assertAll(
                () -> assertEquals(service.getEmployeeDetails(2).getName(), "Sato"),
                () -> assertEquals(service.getEmployeeDetails(2).getId(), 2)
        );
        verify(repo, times(2)).findById(2);
    }

}
