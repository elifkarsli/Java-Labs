package LAB3;

import java.io.*;
import java.util.*;

public class EMS {
   private String companyName;
   private Manager manager;
   Set<Employee> employees;
   Map<Employee,Double> salaryMap;

    public EMS(String companyName, Manager manager){
     this.companyName = companyName;
     this.manager = manager;
     this.employees = new HashSet<>();
     this.salaryMap = new HashMap<>();
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public String getCompanyName(){
        return companyName;
    }

    public void setManager(Manager manager){
        this.manager = manager;
    }
    public Manager getManager(){
        return manager;
    }

    public void registerEmployee(Employee employee){
       if(!employees.contains(employee)){
        employees.add(employee);
        salaryMap.put(employee, 0.0);
        System.out.println(employee.getName() + " has recorded system.");
       }else{
        System.out.println(employee.getName() + " is already in system.");
       }
    }

    public void removeEmployee(Employee employee){
        if (employees.contains(employee)) {
            employees.remove(employee);
            salaryMap.remove(employee);
            System.out.println(employee.getName() + " remove from system.");
        } else {
            System.out.println(employee.getName() + " cannot found in the system.");
        }
    }

    public void calculateSalaries(String filePath){
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                String name = data[0].trim();
                double dailySalary = Double.parseDouble(data[1].trim());

                double monthlySalary = dailySalary * 20;

                Employee existingEmployee = null;
                for (Employee emp : employees) {
                    if (emp.getName().equals(name)) {
                        existingEmployee = emp;
                        break;
                    }
                }

                if (existingEmployee == null) {
                    existingEmployee = new Employee(employees.size() + 1, name);
                    registerEmployee(existingEmployee);
                }

                existingEmployee.setMonthlySalary(monthlySalary);
                salaryMap.put(existingEmployee, monthlySalary);
                System.out.println("Salary calculated for: " + name);
             }
        }catch(IOException e){
            e.getMessage();
        }
    }

    public void askForSalaryLimit(){
        double limit = manager.getSalaryLimit();
        if(limit == 0){
            System.out.println("Set a salary limit.");
        }

        List<Employee> toRemove = new ArrayList<>();
        for (Map.Entry<Employee, Double> entry : salaryMap.entrySet()) {
            if (entry.getValue() > limit) {
                toRemove.add(entry.getKey());
            }
        }

        for (Employee emp : toRemove) {
            removeEmployee(emp);
        }

        System.out.println("Salary limit exceeded, employees dismissed.");
    }

    public void printEmployees() {
        System.out.println("Employees : ");
        if (employees.isEmpty()) {
            System.out.println("No employees remain in the company.");
        } else {
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }

    }

}
