public class HomeAssistant extends VirtualAssistant{
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
