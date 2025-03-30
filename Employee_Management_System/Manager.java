package LAB3;

public class Manager {
    private int id;
    private String name;
    private double salaryLimit;

    public Manager(int id, String name){
        this.id = id;
        this.name = name;
        this.salaryLimit = 0.0;
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

    public void setSalaryLimit(double salaryLimit){
        this.salaryLimit = salaryLimit;
    }
    public double getSalaryLimit(){
        return salaryLimit;
    }
}
