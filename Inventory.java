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
    
    public Inventory() {
        this.healthPotions = 10;
        this.manaPotions = 10;
        this.gold = 0;
        this.xp = 0;
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

    public void addXp(int amount) {
        this.xp += amount;
    }

    // Display Inventory status
    public void displayInventory() {
        System.out.println();
        System.out.println("Inventory:");
        System.out.println("XP: " + xp);
        System.out.println("Gold: " + gold);
        System.out.println("Health Potions: " + healthPotions);
        System.out.println("Mana Potions: " + manaPotions);
        System.out.println();
    }

}
