import java.util.ArrayList;
import java.util.List;

public abstract class VirtualAssistant{
    private String assistantName;
    private String version;

    public VirtualAssistant(String assistantName, String version){
        this.assistantName = assistantName;
        this.version = version;
    }

    public VirtualAssistant(){
        this.assistantName = "null";
        this.version = "null";
    }

    public void setAssistantName(String assistantName){this.assistantName = assistantName;}
    public String getAssistantName(){return assistantName;}

    public void setVersion(String version){this.version = version;}
    public String getVersion(){return version;}

    protected abstract String greetUser();

    protected abstract String performTask(String task);

   
}

class HomeAssistant extends VirtualAssistant{
    private boolean isLightOn;

    public HomeAssistant(String assistantName, String version, boolean isLightOn){
        super(assistantName, version);
        this.isLightOn = isLightOn;
    }

    public HomeAssistant(){
        this.isLightOn = false;
    }

    public void setIsLightOn(boolean isLightOn){this.isLightOn = isLightOn;}
    public boolean getIsLightOn(){return isLightOn;}

    public String greetUser(){
        return "Hello! I’m your Home Assistant. How can I help to control your home today?";
    }

    public String performTask(String task) {
        if (task.equalsIgnoreCase("turn on lights")) {
            if (isLightOn) {
                return "The lights are already turned on.";
            } else {
                isLightOn = true;
                return "Turning on the lights!";
            }
        } else if (task.equalsIgnoreCase("turn off lights")) {
            if (!isLightOn) {
                return "The lights are already turned off.";
            } else {
                isLightOn = false;
                return "Turning off the lights!";
            }
        } else {
            return "Sorry, I can't do that.";
        }
    }
}

class AssistantManager {
    private List<VirtualAssistant> assistants;

    // Constructor
    public AssistantManager() {
        this.assistants = new ArrayList<>();
    }

    // Getter for assistants
    public List<VirtualAssistant> getAssistants() {
        return assistants;
    }

    // Setter for assistants
    public void setAssistants(List<VirtualAssistant> assistants) {
        this.assistants = assistants;
    }

    // Method to add an assistant
    public void addAssistant(VirtualAssistant assistant) {
        assistants.add(assistant);
    }

    // Method to remove an assistant
    public void removeAssistant(VirtualAssistant assistant) {
        assistants.remove(assistant);
    }

    // Method to interact with all assistants
    public List<String> interactWithAll(String task) {
        List<String> responses = new ArrayList<>();
        for (VirtualAssistant assistant : assistants) {
            responses.add(assistant.greetUser());
            responses.add(assistant.performTask(task));
        }
        // Print responses
        for (String response : responses) {
            System.out.println(response);
        }
        return responses;
    }
}

class LanguageTranslatorAssistant extends VirtualAssistant{
    private String lastTranslatedWord;

    public LanguageTranslatorAssistant(String assistantName, String version ,String lastTranslatedWord){
        super(assistantName, version);
        this.lastTranslatedWord = lastTranslatedWord;
    }

    public LanguageTranslatorAssistant(){
        this.lastTranslatedWord = "None";
    }

    public void setLastTranslatedWord(String lastTranslatedWord){this.lastTranslatedWord = lastTranslatedWord;}
    public String getLastTranslatedWord(){return lastTranslatedWord;}

    public String greetUser() {
        return "Bonjour! Hola! Hello! I’m your Language Translator AI!";
    }

    public String performTask(String task) {
        if (task.equalsIgnoreCase("translate hello to Spanish")) {
            lastTranslatedWord = "Hola";
            return "Hello in Spanish is Hola.";
        } else if (task.equalsIgnoreCase("translate thank you to French")) {
            lastTranslatedWord = "Merci";
            return "Thank you in French is Merci.";
        } else {
            return "I don’t know that language yet.";
        }
    }
}


class PersonalFinanceAssistant extends VirtualAssistant{
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



