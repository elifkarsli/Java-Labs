public class LanguageTranslatorAssistant extends VirtualAssistant{
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
