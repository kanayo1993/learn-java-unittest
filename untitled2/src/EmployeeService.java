import java.util.NoSuchElementException;

public class EmployeeService {
    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo){
        this.repo = repo;
    }


    public Employee getEmployeeDetails(int id){
        if(id <= 0){
            throw new IllegalArgumentException("Invalid employee ID: "+ id);
        }
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Employee not found: " + id));
    }

    public void updateEmployeeName(int id, String newName){
        if(id <= 0 || newName == null || newName.isBlank()){
            throw new IllegalArgumentException("Invalid input for update");
        }
        Employee emp = getEmployeeDetails(id);
        repo.updateName(id,newName);
    }

}
