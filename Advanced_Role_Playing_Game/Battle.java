import java.util.*;
public class Battle {
    private Party party1;
    private Party party2;
    private ArrayList<Character> team1;
    private ArrayList<Character> team2;

    public Battle(Party party1, Party party2){
        this.party1 = party1;
        this.party2 = party2;
        this.team1 = new ArrayList<>();
        this.team2 = new ArrayList<>();
    }

    public void formTeams(){
        ArrayList<Character> members1 = new ArrayList<>(party1.getMembers());
        ArrayList<Character> members2 = new ArrayList<>(party2.getMembers());
        
        Collections.shuffle(members1);
        Collections.shuffle(members2);
        
        for (int i = 0; i < 3 && i < members1.size(); i++) {
            team1.add(members1.get(i));
        }
        for (int i = 0; i < 3 && i < members2.size(); i++) {
            team2.add(members2.get(i));
        }
        
        System.out.println("Teams have been formed!");
    }

    public void startBattle() {
        System.out.println("Battle begins!");
        
        Random random = new Random();
        while (!team1.isEmpty() && !team2.isEmpty()) {
            boolean team1Attacks = random.nextBoolean();
            Character attacker, defender;
            
            if (team1Attacks) {
                attacker = team1.get(random.nextInt(team1.size()));
                defender = team2.get(random.nextInt(team2.size()));
            } else {
                attacker = team2.get(random.nextInt(team2.size()));
                defender = team1.get(random.nextInt(team1.size()));
            }
            
            attack(attacker, defender);
            
            // Eğer savunan karakterin canı biterse, listeden çıkar
            if (defender.getHitPoint() <= 0) {
                System.out.println(defender.getName() + " has fallen!");
                if (team1.contains(defender)) {
                    team1.remove(defender);
                } else {
                    team2.remove(defender);
                }
            }
        }
        
        declareWinner();
    }

    private void attack(Character attacker, Character defender) {
        double damage = attacker.calculateDamage();
        System.out.println(attacker.getName() + " attacks " + defender.getName() + " for " + damage + " damage!");
        defender.setHitPoint(defender.getHitPoint() - damage);
    }

    // Kazananı belirleme metodu
    private void declareWinner() {
        if (team1.isEmpty()) {
            System.out.println("Party " + party2.getPartyName() + " wins!");
        } else {
            System.out.println("Party " + party1.getPartyName() + " wins!");
        }
    }
}
