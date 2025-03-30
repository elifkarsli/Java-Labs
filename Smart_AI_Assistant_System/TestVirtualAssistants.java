public class TestVirtualAssistants {
    public static void main(String[] args) {
        // Creating assistants
        HomeAssistant homeAssistant = new HomeAssistant("Home AI", "1.0", false);
        PersonalFinanceAssistant financeAssistant = new PersonalFinanceAssistant("Finance AI", "2.0", 0);
        LanguageTranslatorAssistant translatorAssistant = new LanguageTranslatorAssistant("Translator AI", "1.5", null);

        // Creating assistant manager
        AssistantManager manager = new AssistantManager();
        
        // Adding assistants to manager
        manager.addAssistant(homeAssistant);
        manager.addAssistant(financeAssistant);
        manager.addAssistant(translatorAssistant);

        // Interacting with assistants
        System.out.println("--- Testing Assistants ---");
        manager.interactWithAll("turn on lights");
        manager.interactWithAll("show balance");
        manager.interactWithAll("deposit money 200");
        manager.interactWithAll("withdraw 100");
        manager.interactWithAll("translate hello to Spanish");
        manager.interactWithAll("translate thank you to French");
        manager.interactWithAll("turn off lights");
    }
}
