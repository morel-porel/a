/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.*;
/**
 *
 * @author User
 */
public class GameLogic {    
    static Scanner scan = new Scanner(System.in);
    static Inventory inv = new Inventory();
    static Random ran = new Random();
    
    //Color codes, newly added.
    
    public static final String RESET = "\u001B[0m";  // Reset to default
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String RED_BACKGROUND = "\033[41m";
    
    public static int readInt(String prompt, int userChoices){
        int input=0;
        do{
            System.out.print(prompt);
            try{
                input = scan.nextInt();
                if(input>userChoices||input < 1 ){
                    throw new ArithmeticException();
                }
            } catch(ArithmeticException e){
                scan.nextLine();
                System.out.print("Invalid Input!");
            } catch(Exception e){
                scan.nextLine();
                input = -1;
                System.out.print("Please enter an integer!");
            }
        }while(input < 1 || input > userChoices);
        return input;
    }

    public static boolean yesOrNo(String prompt){        
        scan.nextLine();
        while(true){
            try{
                System.out.print(prompt + " (YES/NO): ");
                String input = scan.nextLine();
                if(input.equalsIgnoreCase("yes")){
                    return true;
                } else if (input.equalsIgnoreCase("no")){
                    return false;
                } else {
                    throw new InputMismatchException("Invalid input. Please type 'yes' or 'no'");
                }
            } catch (InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static void anythingToContinue(){
        System.out.println("\nEnter anything to continue...");
        scan.next();
    }

    public static void clearConsole(){
        for(int i=0;i<100;i++){
            System.out.println();
        }
    }
    
    public static void printSeparator(int n){
        for(int i = 0 ; i < n ; i++){ //n is number of dashes
            System.out.print("-");
        }
        System.out.println();
    }
    
    public static void printHeading(String title){
        printSeparator(50);
        
        //insert print chuchu title here
        
        //System.out.println("LVL " + inv.getLvl() + ": EXP " + inv.getXp() + "/" + inv.getReqXp() +"\t\t\tGold: " + inv.getGold());
        printSeparator(50);
    }
    
    public static void battle(Monster monster, Character[] party){
        
        monster.show();
        System.out.println(monster.displayName()+" is ready to strike!\n"); 
        boolean enemyStunned = false;
        
        while (anyPlayerAlive(party) && monster.isAlive()) {
            // Player selects a character
            for (int i = 0; i < party.length; i++) {
                if (party[i].isAlive()) {
                    System.out.println(RESET + (i + 1) + ". " + RESET + party[i].displayName() + RED + "(HP: " + party[i].getHP() + "/"+ RED +party[i].getMaxHP()+ BLUE +" | MP: "+party[i].getMP()+"/"+ BLUE +party[i].getMaxMP()+")");
                }
            }
            
            int choice;
            Character activePlayer;
            do{
                choice = readInt("\nChoose a character: ", 3)-1;
                activePlayer = party[choice];
                if(!activePlayer.isAlive()){
                    scan.nextLine();
                    choice = -1;
                    System.out.println(activePlayer.displayName()+" is down!");
                }                
            } while(choice == -1);

            // Player chooses an action
            System.out.println("\n" + RESET + activePlayer.displayName() + "'s turn." + RESET + RED + " (HP: " + activePlayer.getHP() + "/"+ RED +activePlayer.getMaxHP()+ BLUE +" | MP: "+activePlayer.getMP()+"/"+ BLUE +activePlayer.getMaxMP()+")");
            System.out.println("1. Use "+activePlayer.getSkillOne()+" (MP: "+activePlayer.skillOneMP()+ " | "+activePlayer.getDMG1()+")");
            System.out.println("2. Use "+activePlayer.getSkillTwo()+" (MP: "+activePlayer.skillTwoMP()+ " | "+activePlayer.getDMG2()+")");
            System.out.println("3. Use "+activePlayer.getSkillThree()+" (MP: "+activePlayer.skillThreeMP()+ " | "+activePlayer.getDMG3()+")");
            System.out.println("4. Open inventory");
            int action = readInt(RESET + "\nChoose a skill: ", 4); 
            System.out.println();
            
            int damage;
            switch (action) {
                case 1:
                    damage = activePlayer.skillOne();
                    if (damage < 0) { // If damage is negative, it includes stun
                        enemyStunned = true;
                        damage = Math.abs(damage + 2); // Extract actual damage
                        System.out.println("The enemy is stunned and will skip their next turn.");
                    }
                    monster.setHP(monster.getHP() - damage);
                    break;

                // Elara's game logic
                case 2:
                    if(activePlayer instanceof CharacterElara){
                        // Check if Elara is healing herself or another party member
                        System.out.println("Heal Options: ");
                        System.out.println("1. Heal herself");
                        System.out.println("2. Heal another party member");
                        int healChoice = readInt("\nChoose an option: ", 2);
                        if(healChoice == 1){
                            activePlayer.skillTwo(); // Heal herself
                        } else {
                            ((CharacterElara)activePlayer).skillTwo(party); // Heal another party member
                        }
                    } else {
                        damage = activePlayer.skillTwo();
                        monster.setHP(monster.getHP()-damage);
                    }
                    break;

                case 3:
                    if(activePlayer instanceof CharacterElara){
                        int buffPercent = activePlayer.skillThree();
                        for (Character party1 : party) { // Loop through party array to set buff percentage
                            party1.setBuffPercentage(buffPercent);
                            party1.setBuffActive(true);
                            party1.setBuffTurnsRemaining(2);
                        }
                    } else {
                        damage = activePlayer.skillThree();
                        monster.setHP(monster.getHP() - damage);
                    }
                    break;
                
                case 4:
                    boolean inInventory = true;

                while (inInventory) {
                    inv.displayInventory();
                    System.out.println("1. Use health potion");
                    System.out.println("2. Use mana potion");
                    System.out.println("3. RETURN"); // Option to exit inventory

                    action = readInt("\nChoose an action: ", 3); // Update to allow choice 1-3

                    switch (action) {
                        case 1:
                            inv.useHealthPotion(activePlayer);
                            break;
                        case 2:
                            inv.useManaPotion(activePlayer);
                            break;
                        case 3:
                            inInventory = false; // Exit inventory and return to battle menu
                            break;
                        default:
                            System.out.println("Invalid Choice!");
                    }
                }
                break;
            }
            //buff deactivation
            for (Character partyMember : party) {
                if (partyMember.isBuffActive()) {
                    partyMember.setBuffTurnsRemaining(partyMember.getBuffTurnsRemaining() - 1); // Decrement buff turns
                    if (partyMember.getBuffTurnsRemaining() <= 0) {
                        partyMember.setBuffActive(false); // Deactivate buff
                        partyMember.setBuffPercentage(0); // Reset buff percentage
                        System.out.println(partyMember.displayName() + "'s buff has expired.");
                    }
                }
            }
            
            System.out.println("\n" + RED + monster.displayName() + RED + " HP: " + monster.getHP());   
            // Check if the slime is dead
            if (!monster.isAlive()) {
                System.out.println("You defeated the " + monster.displayName() + "!");
                //recover downed members
                for(Character partyMember : party){
                    if(!partyMember.isAlive()){
                        partyMember.revive();
                    }
                }
                int addHP = ran.nextInt(10-5+1) + 5, addMP = ran.nextInt(10-5+1) + 5, addXP = ran.nextInt(15-5+1) + 5;
                inv.addXp(addXP, party);
                inv.addHealthPotion(addHP);
                inv.addManaPotion(addMP);
                inv.addGold(100);
                System.out.println();
                System.out.println("Rewards: ");
                System.out.println(GREEN + "+"+addXP+" XP");
                System.out.println(GREEN +"+100 gold");
                System.out.println(GREEN +"+"+addHP+" health potions");
                System.out.println(GREEN +"+"+addMP+" mana potions");
                System.out.println();

                // NEW BLOCK LINE 180 to 189 << PROMPT FOR SHOP AFTER PATAY OG MONSTER
                if(inv.getGold() >= 10){
                    boolean response = yesOrNo("Would you like to visit the shop?");
                    if(response){
                        shopMenu(inv);
                    }
                }
                System.out.println("Prepare for next battle...");
                continue;
            }
            

            // If the enemy is stunned, skip their turn
            if (enemyStunned) {
                System.out.println(RED + monster.displayName() + " is stunned and skips their turn.\n");
                enemyStunned = false; // Reset stun status after skipping turn
            } else {
                // Enemy attacks a random player
                Character target;
                do {
                    target = party[(int) (Math.random() * party.length)];  // Select a random party member
                } while (!target.isAlive());  // Repeat if the selected character is not alive
                System.out.println("\n" + monster.displayName() + "'s turn and targeted "+target.displayName()+"!");
                damage = monster.skillOne();
                System.out.println();
                target.setHP(target.getHP() - damage);
                if(!target.isAlive())
                    System.out.println(RED_BACKGROUND + target.displayName() +" fainted!\n");
            }

            // Check if all players are dead
            if (!anyPlayerAlive(party)) {
                    System.out.println(RED + "All your characters have been defeated...");
                break;
            }
        }
    }

    // Utility method to check if any player in the party is still alive
    public static boolean anyPlayerAlive(Character[] party) {
        for (Character c : party) {
            if (c.isAlive()) {
                return true;
            }
        }
        return false;
    }


    // KANI BAG.o NI FOR SHOPMENU
    public static void shopMenu(Inventory playerInventory) {
    boolean shopping = true;
    System.out.println();
    System.out.println("( ; 7,_>7) Hoho, you found me! Welcome to the Secret Shop!");

    while (shopping) {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Buy Health Potion (10 gold each)");
        System.out.println("2. Buy Mana Potion (10 gold each)");
        System.out.println("3. Exit Shop");

        int choice = readInt("\nEnter your choice (1-3): ", 3);

        switch (choice) {
            case 1:
                System.out.print("How many Health Potions would you like to buy? ");
                int healthPotionQuantity = readInt("\nQuantity: ", 100);
                int healthPotionCost = healthPotionQuantity * 10;

                if (playerInventory.getGold() >= healthPotionCost) {
                    playerInventory.addHealthPotion(healthPotionQuantity);
                    playerInventory.spendGold(healthPotionCost);
                    System.out.println("You bought " + healthPotionQuantity + " Health Potions!");
                } else {
                    System.out.println("Not enough gold!");
                }
                break;
            case 2:
                System.out.print("How many Mana Potions would you like to buy? ");
                int manaPotionQuantity = readInt("\nQuantity: ", 100);
                int manaPotionCost = manaPotionQuantity * 10;

                if (playerInventory.getGold() >= manaPotionCost) {
                    playerInventory.addManaPotion(manaPotionQuantity);
                    playerInventory.spendGold(manaPotionCost);
                    System.out.println("You bought " + manaPotionQuantity + " Mana Potions!");
                } else {
                    System.out.println("Not enough gold!");
                }
                break;
            case 3:
                shopping = false;
                System.out.println("Thanks for visiting the shop!");
                break;
            default:
                System.out.println("Invalid choice! Please select again.");
                
        }
    }
}
    
}
