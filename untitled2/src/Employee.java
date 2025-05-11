public class Employee {
    private final int id;
    private final String name;

    public Employee (int id, String name){
        if (id <= 0) throw new IllegalArgumentException("ID must be positive");
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}