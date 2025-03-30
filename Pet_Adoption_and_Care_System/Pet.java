package LAB2;

public class Pet{
    String name;
    int hungerLevel;
    int happinessLevel;
    String owner;

    public Pet(String name, int hungerLevel, int happinessLevel, String owner){
        this.name = name;
        this.hungerLevel = hungerLevel;
        this.happinessLevel = happinessLevel;
        this.owner = null;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setHungerLevel(int hungerLevel){
        this.hungerLevel = Math.max(0, Math.min(hungerLevel, 100));
    }
    public int getHungerLevel(){
        return hungerLevel;
    }

    public void setHappinessLevel(int happinessLevel){
        this.happinessLevel = Math.max(0, Math.min(happinessLevel, 100));
    }
    public int getHappinessLevel(){
        return happinessLevel;
    }

    public void setOwner(String owner){
        this.owner = owner;
    }
    public String getOwner(){
        return owner;
    }

    public int eat(){
        if(hungerLevel <= 10){ return 0;}
        else { return hungerLevel - 10;}
    }

    public int play(){
        if(happinessLevel > 95){return 100;}
        else{return happinessLevel + 5;}
    }

    public void checkStatus(){
        System.out.println("Hunger level : " + hungerLevel + "Happiness level : " + happinessLevel);
    }
}