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
    
    public static int readInt(String prompt, int userChoices){
        int input;
        do{
            System.out.println(prompt);
            try{
                input = scan.nextInt();
            }catch(Exception e){
                scan.nextLine();
                input = -1;
                System.out.println("Please enter an integer!");
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
            System.out.println("1. Use "+activePlayer.getSkillOne()+" (MP: "+activePlayer.skillOneMP()+")");//
            System.out.println("2. Use "+activePlayer.getSkillTwo()+" (MP: "+activePlayer.skillTwoMP()+")");//
            System.out.println("3. Use "+activePlayer.getSkillThree()+" (MP: "+activePlayer.skillThreeMP()+")");//
            int action = readInt("\nChoose a skill:", 3); 
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
                    //temp fix this pa 74-112
                    System.out.println("Original Damage: "+damage); 
                    damage = activePlayer.applyBuff(damage);
                    System.out.println("Damage after applying buff: "+ damage);
                    monster.setHP(monster.getHP() - damage);
                    break;
                case 2:
                    damage = activePlayer.skillTwo();
                    System.out.println("Original Damage: "+damage);
                    damage = activePlayer.applyBuff(damage);
                    System.out.println("Damage after applying buff: "+ damage);
                    monster.setHP(monster.getHP()-damage);
                    break;
                case 3:
                    //damage = activePlayer.skillThree();
                    //monster.setHP(monster.getHP()-damage);
                    
                    
                    
                    
                    if(activePlayer instanceof CharacterElara){
                //ga check ni if ang chosen hero kay si Elara
                        int buffPercent = activePlayer.skillThree();
                        for (Character party1 : party) { //loop through party array then set buffpercentage
                            party1.setBuffPercentage(buffPercent);
                            party1.setBuffActive(true);
                            party1.setBuffTurnsRemaining(2);
                        }
                        
                        
                        
                    } else {
                        damage = activePlayer.skillThree();
                        System.out.println("Original Damage: "+damage);
                        damage = activePlayer.applyBuff(damage);
                        System.out.println("Damage after applying buff: "+ damage);
                        monster.setHP(monster.getHP() - (damage));
                    }
                    
                    break;
                default:
                    System.out.println("Invalid choice!");
                    continue;
            }       
            
            System.out.println("\n" + monster.displayName() + " HP: " + monster.getHP());          
            
            

            // Check if the slime is dead
            if (!monster.isAlive()) {
                System.out.println("You defeated the " + monster.displayName() + "!");
                break;
            }

            // If the enemy is stunned, skip their turn (MAJOR CHANGES)
            if (enemyStunned) {
                System.out.println(monster.displayName() + " is stunned and skips their turn.");
                enemyStunned = false; // Reset stun status after skipping turn
            } else {
                // Enemy attacks a random player
                System.out.println("\n" + monster.displayName() + "'s turn.");
                Character target;
                do {
                    target = party[(int) (Math.random() * party.length)];  // Select a random party member
                } while (!target.isAlive());  // Repeat if the selected character is not alive
                System.out.println("\n" + target.displayName() + " targeted.");
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
