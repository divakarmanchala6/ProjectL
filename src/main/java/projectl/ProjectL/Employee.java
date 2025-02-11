package projectl.ProjectL;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Employee(int id, String firstName, String lastName, int age, double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

}
