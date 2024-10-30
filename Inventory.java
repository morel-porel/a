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
            System.out.println(target.displayName()+" used a health potion, restored 50HP.");
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
            System.out.println(target.displayName()+" used a mana potion, restored 50MP.");
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
            System.out.println("You spent " + amount + " gold.");
        } else {
            System.out.println("Not enough gold!");
        }
    }

    // Getter and Setter for XP
    public int getXp() {
        return xp;
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
        System.out.println("XP: " + xp + " / "+ xpReq);
        System.out.println("Gold: " + gold);
        System.out.println("Health Potions: " + healthPotions);
        System.out.println("Mana Potions: " + manaPotions);
        System.out.println();
    }

}
