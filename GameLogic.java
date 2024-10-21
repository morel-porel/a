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
    @SuppressWarnings("ConvertToTryWithResources")
    public void battle(Monster monster, Character[] party){
        Scanner scan = new Scanner(System.in);
        monster.show();
        monster.displayName();
        boolean enemyStunned = false;
        
        while (anyPlayerAlive(party) && monster.isAlive()) {
            // Player selects a character
            System.out.println("\nChoose a character:");
            for (int i = 0; i < party.length; i++) {
                if (party[i].isAlive()) {
                    System.out.println((i + 1) + ". " + party[i].displayName() + " (HP: " + party[i].getHP() + ")");
                }
            }
            int choice = scan.nextInt() - 1;
            Character activePlayer = party[choice];

            // Player chooses an action
            System.out.println("\n" + activePlayer.displayName() + "'s turn.");
            System.out.println("1. Use Skill One");
            System.out.println("2. Use Skill Two");
            System.out.println("3. Use Skill Three");
            int action = scan.nextInt();
            
            
            switch (action) {
                case 1://changes
                    int damage = activePlayer.skillOne();
                    if (activePlayer.displayName().equalsIgnoreCase("Finn")) {
                        monster.setHP(monster.getHP() - Math.abs(damage));  
                        if (damage == -2) { 
                            enemyStunned = true;
                            System.out.println("The enemy is stunned and will skip their next turn.");
                        }
                    } else {
                        monster.setHP(monster.getHP() - damage);  
                    }
                    break;
                case 2:
                    damage = activePlayer.skillTwo();
                    monster.setHP(monster.getHP()-damage);
                    break;
                case 3:
                    damage = activePlayer.skillThree();
                    monster.setHP(monster.getHP()-damage);
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

            // If the enemy is stunned, skip their turn
            if (enemyStunned) {
                System.out.println(monster.displayName() + " is stunned and skips their turn.");
                enemyStunned = false; // Reset stun status after skipping turn
            } else {
                // Enemy attacks a random player
                System.out.println("\n" + monster.displayName() + "'s turn.");
                Character target = party[(int) (Math.random() * party.length)];
                if (target.isAlive()) {
                    int damage = monster.skillOne();
                    target.setHP(target.getHP() - damage);
                }
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
