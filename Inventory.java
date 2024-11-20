/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author User
 */
public class Inventory {
    
    public static final String RESET = "\u001B[0m";  // Reset to default
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String ORANGE = "\033[38;5;214m";
    public static final String RED_BACKGROUND = "\033[41m"; 
    public static final String BLUE_BACKGROUND = "\033[44m";
    
    private int healthPotions;
    private int manaPotions;
    private int gold;
    private int xp;
    private int xpReq;
    private int lvl;
    
    public Inventory() {
        healthPotions = 10;
        manaPotions = 10;
        gold = 0;
        xp = 0;
        xpReq = 100;
        lvl=1;
    }
    
    public int getHealthPotions() {
        return healthPotions;
    }

    public void addHealthPotion(int amount) {
        this.healthPotions += amount;
    }

    public void useHealthPotion(Character target) {
        if (healthPotions > 0) {
            healthPotions--;
            int healAmount = 50;
            target.setHP(target.getHP() + healAmount);  
            System.out.println(target.displayName()+ RESET +" used a health potion, restored "+ RED_BACKGROUND + "50HP."+RESET);
        } else {
            System.out.println("No health potions left!");
        }
    }

    // Getter and Setter for mana potions
    public int getManaPotions() {
        return manaPotions;
    }

    public void addManaPotion(int amount) {
        this.manaPotions += amount;
    }

    public void useManaPotion(Character target) {
        if (manaPotions > 0) {
            manaPotions--;
            int healAmount = 50;
            target.setMP(target.getMP() + healAmount);  
            System.out.println(target.displayName()+RESET+" used a mana potion, restored " + BLUE_BACKGROUND + "50MP." + RESET);
        } else {
            System.out.println("No mana potions left!");
        }
    }

    // Getter and Setter for gold
    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
        this.gold += amount;
    }

    public void spendGold(int amount) {
        if (gold >= amount) {
            gold -= amount;
            System.out.println("You spent "+ YELLOW + amount + " gold.");
        } else {
            System.out.println("Not enough gold!");
        }
    }

    // Getter and Setter for XP
    public int getXp() {
        return xp;
    }

    public int getReqXp(){
        return xpReq;
    }

    public void addXp(int amount, Character[] party) {
        xp += amount;
        if(xp>=xpReq){
            lvlUp(party);            
        }
    }
    
    public void lvlUp(Character[] party){
        while(xp>=xpReq){
            lvl++;
            xp-=xpReq;
            xpReq+=100;
            for (Character character : party) {
                if (character.isAlive()) {
                    character.lvlUpCharacter();
                }
            }
            System.out.println("Party has leveled up!");    
        }
        System.out.println("HP and MP have been restored!");
    }
    
    // Display Inventory status
    public void displayInventory() {
        System.out.println();
        System.out.println("Inventory:");
        System.out.println(GREEN +"XP: " + xp + " / "+ xpReq);
        System.out.println(YELLOW +"Gold: " + gold);
        System.out.println("Health Potions: " + healthPotions);
        System.out.println("Mana Potions: " + manaPotions);
        System.out.println();
    }

}
