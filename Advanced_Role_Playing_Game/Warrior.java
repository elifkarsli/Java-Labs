public class Warrior extends Character{
    private int energy;
    private int defense;

    public Warrior(){
        super();
        this.energy = 20;
        this.defense = 5;
    }

    public Warrior(String name, double hitPoint, String gender, int level, int experience) {
        super(name, hitPoint, gender, level, experience);
        this.energy = 20;
        this.defense = 5;
    }
    
    private void rest(){
        energy =+ 20;
        System.out.println("Resting... New energy : " + energy);
    }

    @Override
    public double calculateDamage(){
        return getHitPoint() * 1.2;
    }

    @Override
    public void attack(){
        if(energy < 10){
            System.out.println("Not enough energy. Get rest...");
        }else{
            energy =+ 10;
            super.attack();
            System.out.println("Remaining energy : " + energy);
        }
    }

    @Override
    public void regeneratePower(){
        rest();
    }

    @Override
    public void printInfo(){
        super.printInfo();
        System.out.println("Energy : " + energy + "Defense : " + defense);
    }
}