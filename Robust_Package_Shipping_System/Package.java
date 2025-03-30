import java.util.ArrayList;

public abstract class Package implements Refundable {
    private String senderName;
    private String recipientName;
    private double weight;
    private boolean isDelivered;
    private String destinationCity;
    private String destinationCountry;
    private String packageIdentifier;
    private boolean refundRequested;
    private double refundAmount;

    public Package() {
        this.senderName = "";
        this.recipientName = "";
        this.weight = 0.0;
        this.isDelivered = false;
        this.destinationCity = "";
        this.destinationCountry = "";
        this.packageIdentifier = "";
        this.refundRequested = false;
        this.refundAmount = 0.0;
    }

    public Package(String senderName, String recipientName, double weight, String destinationCity, String destinationCountry, String packageIdentifier) {
        this.senderName = senderName;
        this.recipientName = recipientName;
        this.weight = weight;
        this.isDelivered = false;
        this.destinationCity = destinationCity;
        this.destinationCountry = destinationCountry;
        this.packageIdentifier = packageIdentifier;
        this.refundRequested = false;
        this.refundAmount = 0.0;
    }

    public abstract double calculateShippingCost();

    public void markDelivered() {
        this.isDelivered = true;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void printInfo() {
        System.out.println("Sender: " + senderName);
        System.out.println("Recipient: " + recipientName);
        System.out.println("Weight: " + weight + " kg");
        System.out.println("Destination: " + destinationCity + ", " + destinationCountry);
        System.out.println("Delivery Status: " + (isDelivered ? "Delivered" : "In Transit"));
    }

    public double getWeight() {
        return weight;
    }

    public String getPackageIdentifier() {
        return packageIdentifier;
    }

    @Override
    public boolean requestRefund(String reason) {
        if (!isDelivered) {
            this.refundRequested = true;
            System.out.println("Refund requested for package: " + packageIdentifier + ". Reason: " + reason);
            return true;
        } else {
            System.out.println("Refund request denied. Package already delivered.");
            return false;
        }
    }

    @Override
    public double getRefundAmount() {
        if (refundRequested) {
            refundAmount = calculateShippingCost() * 0.75;
            return refundAmount;
        }
        return 0.0;
    }

    @Override
    public void logRefundRequest(String packageIdentifier) {
        System.out.println("Refund requested for package: " + packageIdentifier);
    }
    public interface Refundable {
        boolean requestRefund(String reason);
        double getRefundAmount();
        
        void logRefundRequest(String packageIdentifier);
    }
 
}

 interface Refundable {
    boolean requestRefund(String reason);
    double getRefundAmount();
    
    void logRefundRequest(String packageIdentifier);
}

interface Trackable {
    String getTrackingInfo();
    void updateLocation(String newLocation);
    void setEstimatedDeliveryTime(String dateTime);
    String getEstimatedDeliveryTime();
}

interface Insurable {
    void insurePackage(double insuredValue);
    double getInsuredValue();
    boolean claimInsurance(String claimReason);

    default void logInsuranceClaim(String packageIdentifier, String reason) {
        System.out.println("Insurance claim requested for package: " + packageIdentifier + ". Reason: " + reason);
    }
}

class StandardPackage extends Package implements Trackable {
    private String shippingType;
    private String currentLocation;
    private String estimatedDeliveryTime;

    public StandardPackage(String senderName, String recipientName, double weight, 
                           String destinationCity, String destinationCountry, String packageIdentifier) {
        super(senderName, recipientName, weight, destinationCity, destinationCountry, packageIdentifier);
        this.shippingType = "Ground";
        this.currentLocation = "Warehouse";
        this.estimatedDeliveryTime = "Not Set";
    }

    @Override
    public double calculateShippingCost() {
        return getWeight() * 2.0;
    }

    @Override
    public String getTrackingInfo() {
        return "Current Location: " + currentLocation + ", Estimated Delivery: " + estimatedDeliveryTime;
    }

    @Override
    public void updateLocation(String newLocation) {
        this.currentLocation = newLocation;
    }

    @Override
    public void setEstimatedDeliveryTime(String dateTime) {
        this.estimatedDeliveryTime = dateTime;
    }

    @Override
    public String getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Shipping Type: " + shippingType);
        System.out.println(getTrackingInfo());
    }
}

class ExpressPackage extends Package implements Trackable, Insurable {
    private int priorityLevel;
    private String currentLocation;
    private String estimatedDeliveryTime;
    private double insuredValue;

    public ExpressPackage(String senderName, String recipientName, double weight, 
                          String destinationCity, String destinationCountry, 
                          String packageIdentifier, int priorityLevel) {
        super(senderName, recipientName, weight, destinationCity, destinationCountry, packageIdentifier);
        this.priorityLevel = priorityLevel;
        this.currentLocation = "Sorting Facility";
        this.estimatedDeliveryTime = "Not Set";
        this.insuredValue = 0.0;
    }

    @Override
    public double calculateShippingCost() {
        return (getWeight() * 5.0) + 10.0;
    }

    @Override
    public String getTrackingInfo() {
        return "Priority: " + priorityLevel + ", Location: " + currentLocation + 
               ", Estimated Delivery: " + estimatedDeliveryTime;
    }

    @Override
    public void updateLocation(String newLocation) {
        this.currentLocation = newLocation;
    }

    @Override
    public void setEstimatedDeliveryTime(String dateTime) {
        this.estimatedDeliveryTime = dateTime;
    }

    @Override
    public String getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    @Override
    public void insurePackage(double insuredValue) {
        this.insuredValue = insuredValue;
    }

    @Override
    public double getInsuredValue() {
        return insuredValue;
    }

    @Override
    public boolean claimInsurance(String claimReason) {
        if (claimReason.equalsIgnoreCase("Lost") || claimReason.equalsIgnoreCase("Damaged")) {
            System.out.println("Insurance claim approved for package: " + getPackageIdentifier());
            return true;
        }
        System.out.println("Insurance claim denied for package: " + getPackageIdentifier());
        return false;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Priority Level: " + priorityLevel);
        System.out.println("Insurance Value: $" + insuredValue);
        System.out.println(getTrackingInfo());
    }
}

class FragilePackage extends Package implements Trackable, Insurable, Refundable {
    private boolean requiresReinforcedBox;
    private boolean requiresTemperatureControl;
    private String currentLocation;
    private String estimatedDeliveryTime;
    private double insuredValue;
    private double refundAmount;

    public FragilePackage(String senderName, String recipientName, double weight, 
                          String destinationCity, String destinationCountry, 
                          String packageIdentifier, boolean requiresReinforcedBox, 
                          boolean requiresTemperatureControl) {
        super(senderName, recipientName, weight, destinationCity, destinationCountry, packageIdentifier);
        this.requiresReinforcedBox = requiresReinforcedBox;
        this.requiresTemperatureControl = requiresTemperatureControl;
        this.currentLocation = "Handling Facility";
        this.estimatedDeliveryTime = "Not Set";
        this.insuredValue = 0.0;
        this.refundAmount = 0.0;
    }

    @Override
    public double calculateShippingCost() {
        return (getWeight() * 2.0) + 8.0;
    }

    @Override
    public String getTrackingInfo() {
        return "Current Location: " + currentLocation + ", Estimated Delivery: " + estimatedDeliveryTime;
    }

    @Override
    public void updateLocation(String newLocation) {
        this.currentLocation = newLocation;
    }

    @Override
    public void setEstimatedDeliveryTime(String dateTime) {
        this.estimatedDeliveryTime = dateTime;
    }

    @Override
    public String getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    @Override
    public void insurePackage(double insuredValue) {
        this.insuredValue = insuredValue;
    }

    @Override
    public double getInsuredValue() {
        return insuredValue;
    }

    @Override
    public boolean claimInsurance(String claimReason) {
        if (claimReason.equalsIgnoreCase("Lost") || claimReason.equalsIgnoreCase("Damaged")) {
            System.out.println("Insurance claim approved for fragile package: " + getPackageIdentifier());
            return true;
        }
        System.out.println("Insurance claim denied for fragile package: " + getPackageIdentifier());
        return false;
    }

    @Override
    public boolean requestRefund(String reason) {
        if (isDelivered() && (reason.equalsIgnoreCase("Damaged") || reason.equalsIgnoreCase("Lost"))) {
            refundAmount = calculateShippingCost() * 0.5;
            System.out.println("Refund approved for fragile package: " + getPackageIdentifier());
            return true;
        }
        System.out.println("Refund request denied for fragile package: " + getPackageIdentifier());
        return false;
    }

    @Override
    public double getRefundAmount() {
        return refundAmount;
    }

    @Override
    public void markDelivered() {
        super.markDelivered();
        System.out.println("Handle with care – Fragile item delivered!");
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Requires Reinforced Box: " + requiresReinforcedBox);
        System.out.println("Requires Temperature Control: " + requiresTemperatureControl);
        System.out.println("Insurance Value: $" + insuredValue);
        System.out.println(getTrackingInfo());
    }
}


class ShippingSystem {
    private ArrayList<Package> packageList;

    public ShippingSystem() {
        this.packageList = new ArrayList<>();
    }

    public void addPackage(Package pkg) {
        for (Package p : packageList) {
            if (p.getPackageIdentifier().equals(pkg.getPackageIdentifier())) {
                System.out.println("Package already exists in the system.");
                return;
            }
        }
        packageList.add(pkg);
    }

    public void removePackage(String packageIdentifier) {
        packageList.removeIf(pkg -> pkg.getPackageIdentifier().equals(packageIdentifier));
    }

    public void printAllPackages() {
        for (Package pkg : packageList) {
            pkg.printInfo();
            System.out.println("------------------------");
        }
    }

    public void generateReport() {
        int totalPackages = packageList.size();
        int deliveredCount = 0;
        double totalShippingCost = 0.0;

        for (Package pkg : packageList) {
            if (pkg.isDelivered()) {
                deliveredCount++;
            }
            totalShippingCost += pkg.calculateShippingCost();
        }

        int inTransitCount = totalPackages - deliveredCount;
        double averageShippingCost = totalPackages > 0 ? totalShippingCost / totalPackages : 0.0;

        System.out.println("Shipping System Report:");
        System.out.println("Total Packages: " + totalPackages);
        System.out.println("Delivered Packages: " + deliveredCount);
        System.out.println("In Transit Packages: " + inTransitCount);
        System.out.printf("Average Shipping Cost: $%.2f%n", averageShippingCost);
    }
}



