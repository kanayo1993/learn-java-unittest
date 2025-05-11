import java.util.Optional;

public interface EmployeeRepository {
    Optional<Employee> findById(int id);

    void updateName(int id, String NewName);
}
