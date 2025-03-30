package LAB3;

public class Test {
    public static void main(String[]args){
        // 1. Manager oluşturma
        Manager manager = new Manager(1, "Alice");
        manager.setSalaryLimit(3000.0); // Maaş sınırı belirleme

        // 2. EMS sistemi oluşturma
        EMS ems = new EMS("TechCorp", manager);

        // 3. Çalışan ekleme testi
        Employee emp1 = new Employee(101, "John Doe");
        Employee emp2 = new Employee(102, "Jane Doe");
        
        ems.registerEmployee(emp1);
        ems.registerEmployee(emp2);
        ems.registerEmployee(emp1); // Aynı çalışan tekrar eklenmemeli

        // 4. Çalışan maaşı güncelleme testi
        emp1.setMonthlySalary(3500.0);
        emp2.setMonthlySalary(2500.0);
        
        // 5. Maaş sınırı kontrolü
        ems.askForSalaryLimit(); // 3500 TL üzerindeki çalışanlar çıkarılacak
        
        // 6. Çalışan listesini yazdırma testi
        ems.printEmployees(); // Sadece Jane Doe kalmalı
    }
}
