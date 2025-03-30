public class PersonalFinanceAssistant extends VirtualAssistant{
    private double currentBalance;

    public PersonalFinanceAssistant(String assistantName, String version, double currentBalance){
        super(assistantName, version);
        this.currentBalance = currentBalance;
    }

    public PersonalFinanceAssistant(){
        this.currentBalance = 500.0;
    }

    public void setCurrentBalance(double currentBalance){this.currentBalance = currentBalance;}
    public double getCurrentBalance(){return currentBalance;}

    public String greetUser() {
        return "Hi! I’m your Finance Assistant. Let’s manage your money wisely!";
    }

    public String performTask(String task) {
        if (task.equalsIgnoreCase("show balance")) {
            return "Your current balance: " + currentBalance + " dollars";
        } else if (task.startsWith("deposit money ")) {
            try {
                double amount = Double.parseDouble(task.split(" ")[2]);
                currentBalance += amount;
                return amount + " dollars is deposited into your account. Your current balance: " + currentBalance + " dollars";
            } catch (NumberFormatException e) {
                return "Invalid deposit amount.";
            }
        } else if (task.startsWith("withdraw ")) {
            try {
                double amount = Double.parseDouble(task.split(" ")[1]);
                if (currentBalance >= amount) {
                    currentBalance -= amount;
                    return amount + " dollars is withdrawn from your account. Your current balance: " + currentBalance + " dollars";
                } else {
                    return "Sorry, insufficient balance!";
                }
            } catch (NumberFormatException e) {
                return "Invalid withdrawal amount.";
            }
        } else {
            return "I don’t know how to do that.";
        }
    }
}
