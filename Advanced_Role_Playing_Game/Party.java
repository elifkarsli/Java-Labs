import java.util.*;
public class Party {
    private String partyName;
    private ArrayList<Character> members;
    private int powerBalance;
    private int reputation;

    public Party(){
        this.partyName = partyName;
        this.members = new ArrayList<>();
        this.powerBalance = powerBalance;
        this.reputation = reputation;
    }

    public Party(String partyName){
        this.partyName = partyName;
        this.members = new ArrayList<>();
        this.powerBalance = 0;
        this.reputation = 0;
    }

    public void setPartyName(String partyName){ this.partyName = partyName;}
    public String getPartyName(){ return partyName;}

    public void setMembers(ArrayList<Character> members){ this.members = members;}
    public ArrayList<Character> getMembers(){ return members;}

    public void setPowerBalance(int powerBalance){this.powerBalance = powerBalance;}
    public int getPowerBalance(){return powerBalance;}

    public void setReputation(int reputation){this.reputation = reputation;}
    public int getReputation(){return reputation;}

    public void addMember(Character character){
        if(members.size() < 10){
             members.add(character);
             System.out.println(character.getName() + "joined the party.");
        }else{
            System.out.println("Party is full!");
        }
    }

    public void removeMember(Character character){
        if(members.remove(character)){
            System.out.println(character.getName() + " removed the party.");
        }else{
            System.out.println("Character not found in the party.");
        }
    }

    public void calculatePowerBalance(){
        double powerBalance = 0;
        for(Character character : members){
            powerBalance =+ character.calculateDamage();
        }
    }

    public void calculateReputation(){
        int reputation = 0;
        for(Character character : members){
            reputation =+ character.getLevel();
        }
    }

    public void printPartyInfo(){
        System.out.println("Party Name: " + partyName + "Power Balance : " + powerBalance + "Reputation : " + reputation);
        System.out.println("Members : ");
        for(Character character : members){
            character.printInfo();
        }
    }
}
