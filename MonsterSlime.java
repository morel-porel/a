package game;

import java.util.Random;

/**
 *
 * @author User
 */
public class MonsterSlime extends Monster {
    private int HP;

    public MonsterSlime(int HP) {
        this.HP = HP;
        System.out.println("A wild Slime has appeared! Starting HP: " + this.HP);
    }

    @Override
    public String displayName() {
        return "Slime";
    }

    @Override
    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public int getHP() {
        return HP;
    }

    @Override
    public int skillOne() {
        Random ran = new Random();
        int skillOneDamage = ran.nextInt(6);
        System.out.println("Slime used slime ball! Dealt " + skillOneDamage + " damage!");
        return skillOneDamage;
    }

    public void displayCurrentHP() {
        System.out.println("Slime's current HP: " + this.HP);
    }
}
