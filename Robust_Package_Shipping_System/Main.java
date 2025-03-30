public class Main {
    public static void main(String[] args) {
        ShippingSystem system = new ShippingSystem();

        // StandardPackage nesneleri
        StandardPackage sp1 = new StandardPackage("Alice", "Bob", 3.0, "New York", "USA", "SP001");
        StandardPackage sp2 = new StandardPackage("Charlie", "David", 5.5, "Los Angeles", "USA", "SP002");

        // ExpressPackage nesneleri
        ExpressPackage ep1 = new ExpressPackage("Eve", "Frank", 2.2, "London", "UK", "EP001", 1);
        ExpressPackage ep2 = new ExpressPackage("Grace", "Hank", 4.8, "Paris", "France", "EP002", 2);

        // FragilePackage nesnesi
        FragilePackage fp1 = new FragilePackage("Ivy", "Jack", 1.5, "Berlin", "Germany", "FP001", true, true);

        // Konum güncelleme ve tahmini teslimat süresi ayarlama (Trackable)
        sp1.updateLocation("New York Distribution Center");
        sp1.setEstimatedDeliveryTime("2025-03-25");

        ep1.updateLocation("London Hub");
        ep1.setEstimatedDeliveryTime("2025-03-22");

        ep2.updateLocation("Paris Hub");
        ep2.setEstimatedDeliveryTime("2025-03-23");

        fp1.updateLocation("Berlin Fragile Handling");
        fp1.setEstimatedDeliveryTime("2025-03-26");

        // Sigorta işlemleri (Insurable)
        ep1.insurePackage(200.0);
        ep2.insurePackage(300.0);
        fp1.insurePackage(500.0);

        // Hasar talebi oluşturma
        ep1.claimInsurance("Lost");
        ep2.claimInsurance("Damaged");
        fp1.claimInsurance("Not Covered");

        // İade talebi (Refundable)
        fp1.requestRefund("Damaged");
        System.out.println("Refund Amount for FP001: $" + fp1.getRefundAmount());

        // Paketleri sisteme ekleme
        system.addPackage(sp1);
        system.addPackage(sp2);
        system.addPackage(ep1);
        system.addPackage(ep2);
        system.addPackage(fp1);

        // Tüm paketleri yazdırma
        System.out.println("\n--- All Packages ---");
        system.printAllPackages();

        // Rapor oluşturma
        System.out.println("\n--- Initial Report ---");
        system.generateReport();

        // Bazı paketleri teslim edildi olarak işaretleme
        sp1.markDelivered();
        ep1.markDelivered();
        fp1.markDelivered();

        // Son durumu yazdırma
        System.out.println("\n--- Final Report After Deliveries ---");
        system.generateReport();
    }
}
