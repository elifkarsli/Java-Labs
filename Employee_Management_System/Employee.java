package LAB3;

import java.util.Objects;

public class Employee{
    private int id;
    private String name;
    private double monthlySalary;

    public Employee(int id, String name){
        this.id = id;
        this.name = name;
        this.monthlySalary = 0.0;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setMonthlySalary(double monthlySalary){
        this.monthlySalary = monthlySalary;
    }
    public double getMonthlySalary(){
        return monthlySalary;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return id == employee.id && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
    
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Monthly Salary: " + monthlySalary;
    }

}