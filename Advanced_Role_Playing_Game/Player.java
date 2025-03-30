import java.util.*;
public class Player {
    private String name;
    private String password;
    private ArrayList<Character> characters;

    public Player(){
        this.name = name;
        this.password = password;
        this.characters = new ArrayList<>();
    }

    public Player(String name, String password){
        this.name = name;
        this.password = password;
        this.characters = new ArrayList<>();
    }

    public void setName(String name){ this.name = name;}
    public String getName(){return name;}

    public void setPassword(String password){ this.password = password;}
    public String getPassword(){return password;}

    public void setCharacters(ArrayList<Character> characters){this.characters = characters;}
    public ArrayList<Character> getCharacters(){return characters;}

    public void printPlayerInfo(){
        System.out.println("Player name : " + name);
        System.out.println("Characters are : ");
        for(Character character : characters){
            character.printInfo();
        }
    }

    public double totalDamage(){
        double totalDamage = 0;
        for(Character character : characters){
            totalDamage =+ character.calculateDamage();
        }
        return totalDamage;
    }

    public class Achievement{
        private List <String> unlockedAchievemnts; 

        public Achievement(){
            this.unlockedAchievemnts = new ArrayList<>();
        }

        public void addAchievement(String achievement){
            unlockedAchievemnts.add(achievement);
        }
    }
}