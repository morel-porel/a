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
    public static void battle(Monster monster, Character[] party){
        Scanner scan = new Scanner(System.in);
        monster.show();
        monster.displayName();
        
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
                case 1:
                    activePlayer.skillOne();
                    break;
                case 2:
                    activePlayer.skillTwo();
                    break;
                case 3:
                    activePlayer.skillThree();
                    break;
                default:
                    System.out.println("Invalid choice!");
                    continue;
            }            
            

            // Check if the slime is dead
            if (!monster.isAlive()) {
                System.out.println("You defeated the " + monster.displayName() + "!");
                break;
            }

            // Enemy attacks a random player
            System.out.println("\n" + slime.getName() + "'s turn.");
            Character target = party[(int) (Math.random() * party.length)];
            if (target.isAlive()) {
                slime.attack(target);
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
