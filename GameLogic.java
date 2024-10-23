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
    
    public int readInt(String prompt, int userChoices){
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
    
    public void battle(Monster monster, Character[] party){
        
        monster.show();
        System.out.println(monster.displayName()+" is ready to strike!\n"); 
        boolean enemyStunned = false;
        
        while (anyPlayerAlive(party) && monster.isAlive()) {
            // Player selects a character
            for (int i = 0; i < party.length; i++) {
                if (party[i].isAlive()) {
                    System.out.println((i + 1) + ". " + party[i].displayName() + " (HP: " + party[i].getHP() + "/"+party[i].getMaxHP()+" | MP: "+party[i].getMP()+"/"+party[i].getMaxMP()+")");
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
            System.out.println("\n" + activePlayer.displayName() + "'s turn.");
            System.out.println("1. Use "+activePlayer.getSkillOne()+" (MP: "+activePlayer.skillOneMP()+")");
            System.out.println("2. Use "+activePlayer.getSkillTwo()+" (MP: "+activePlayer.skillTwoMP()+")");
            System.out.println("3. Use "+activePlayer.getSkillThree()+" (MP: "+activePlayer.skillThreeMP()+")");
            int action = readInt("\nChoose a skill: ", 3); 
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

                default:
                    System.out.println("Invalid choice!");
                    continue;
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
            
            System.out.println("\n" + monster.displayName() + " HP: " + monster.getHP());   
            // Check if the slime is dead
            if (!monster.isAlive()) {
                System.out.println("You defeated the " + monster.displayName() + "!");
                break;
            }

            // If the enemy is stunned, skip their turn
            if (enemyStunned) {
                System.out.println(monster.displayName() + " is stunned and skips their turn.");
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
            }

            // Check if all players are dead
            if (!anyPlayerAlive(party)) {
                System.out.println("All your characters have been defeated...");
                break;
            }
        }

        scan.close();
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
}
