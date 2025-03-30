import java.util.*;
public class Mage extends Character {
    private int mana;
    private double criticalChance;
    
    public Mage(){
        super();
        this.mana = 10;
        this.criticalChance = 0.1;
    }
    
    public Mage(String name, double hitPoint, String gender, int level, int experience){
        super(name, hitPoint, gender, level, experience);
        this.mana = 10;
        this.criticalChance = 0.1;
    }

    private void drinkPotion(){
        mana =+ 10;
        System.out.println("The updated mana is : " + mana);
    }

    @Override
    public double calculateDamage(){
        return getHitPoint() * 0.8;
    }

    @Override
    public void attack(){
        if(mana < 5){
            System.out.println("Not enough mana. Drink potion...");
        }else{
            mana =- 5;
            double damage = calculateDamage();

            if (new Random().nextDouble() < criticalChance) {
                damage *= 2;
                System.out.println("Critical Hit!");
            }
            
            System.out.println("Attacking... Damage is: " + damage);
            gainExperience(20);
            System.out.println("Remaining mana: " + mana);
        }
    }

    @Override
    public void regeneratePower(){
        drinkPotion();
    }

    @Override
    public void printInfo(){
        super.printInfo();
        System.out.println("Mana : " + mana + "Criticial chance : " + (criticalChance * 100) + "%");
    }
}