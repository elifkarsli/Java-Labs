public class Character{
    private String name;
    private double hitPoint;
    private String gender;
    int level;
    int experience;

    public Character(){
        this.name = name;
        this.hitPoint = hitPoint;
        this.gender = gender;
        this.level = 1;
        this.experience = 0;
    }

    public Character(String name, double hitPoint, String gender, int level, int experience){
        this.name = name;
        this.hitPoint = hitPoint;
        this.gender = gender;
        this.level = 1;
        this.experience = 0;
    }

    public void setName(String name){ this.name = name; }
    public String getName(){ return name; }

    public void setHitPoint(double hitPoint){ this.hitPoint = hitPoint; }
    public double getHitPoint(){ return hitPoint; }

    public void setGender(String gender){ this.gender = gender; }
    public String getGender(){ return gender ;}

    public void setLevel(int level){ this.level = level; }
    public int getLevel(){ return level; }

    public void setExperience(int experience){ this.experience = experience; }
    public int getExperience(){ return experience;}

    public double calculateDamage(){
        return hitPoint;
    }

    public void attack(){
        System.out.println("Attacking... Damage is : " + calculateDamage());
        int xp =+ 20;
    }

    public void regeneratePower(){
        System.out.println("Regenerating power...");
    }

    public void gainExperience(int xp){
        experience =+ xp;
        if(experience >= 100){ levelUp();}
    }

    public void levelUp(){
        level++;
        experience = 0;
        System.out.println( name + " leveled up to level : " + level);
    }

    public void printInfo(){
        System.out.println("Name : " + name + "Gender : " + gender + "Hit point : " + hitPoint
        + "Level : " + level + "Experience : " + experience);
    }
}