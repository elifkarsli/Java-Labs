import java.util.ArrayList;
import java.util.List;

public class AssistantManager {
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
