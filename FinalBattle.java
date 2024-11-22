/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author User
 */

public class FinalBattle extends GameLogic{
//    public static GameEndings gameEndings = new GameEndings();
    
    public static int finalBattle(Character[] party, GameEndings gameEndings){
        CharacterKhaimon khaimon = new CharacterKhaimon();
        boolean enemyStunned = false;
        boolean partyStunned = false;
        boolean isDefeated = false;        
        boolean askOnce = false; //only ask to use artifact once
        
        displayDialogue(StoryDialogue.finalBattleDialogue);
        displayDialogue(StoryDialogue.finalBattleIntro);
        System.out.println(khaimon.displayName()+" is ready to strike!"); 
        System.out.println("\n" + RED + khaimon.displayName() + RED + " HP: " + khaimon.getHP()+"\n");
        
        while(anyPlayerAlive(party) && khaimon.isAlive()){
            if (partyStunned) {
                System.out.println("Party is stunned, your turn is skipped.\n");
                partyStunned = false; // Reset stun status after skipping turn
                
            } else {
                System.out.println();
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
                        khaimon.setHP(khaimon.getHP() - damage);
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
                            khaimon.setHP(khaimon.getHP()-damage);
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
                            khaimon.setHP(khaimon.getHP() - damage);
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

                if(khaimon.getHP()<=0){
                    System.out.println("\n" + RED + khaimon.displayName() + RED + " HP: 0");                    
                } else {
                    System.out.println("\n" + RED + khaimon.displayName() + RED + " HP: " + khaimon.getHP());
                }
                
            }

            //use artifact if present, to seal khaimon
            if(gameEndings.getHasArtifact()&&khaimon.getHP()<=(khaimon.getMaxHP()/2)&&khaimon.isAlive()&&!askOnce){
                System.out.println("Corrupted Khaimon is at half health!");
                boolean response = yesOrNo("Use artifact to seal Khaimon?");
                if(response){
                    gameEndings.setUsedArtifact(true);
                    khaimon.setHP(0);
                }      
                askOnce=true;
            }
            
            // Check if the slime is dead end sequence
            if (!khaimon.isAlive()) {
                System.out.println(RED_BACKGROUND +"Corrupted Khaimon has been defeated!\n"+ RESET);
                displayDialogue(StoryDialogue.finalBattleVictory);
                //recover downed members
                for(Character partyMember : party){
                    if(!partyMember.isAlive()){
                        partyMember.revive();
                    }
                }
                int addHP = ran.nextInt(100) + 5, addMP = ran.nextInt(100) + 5, addXP = 10000;
                inv.addXp(addXP, party);
                inv.addHealthPotion(addHP);
                inv.addManaPotion(addMP);
                inv.addGold(10000);
                System.out.println();
                System.out.println("Rewards: ");
                System.out.println(GREEN + "+"+addXP+" XP");
                System.out.println(GREEN +"+100 gold");
                System.out.println(GREEN +"+"+addHP+" health potions");
                System.out.println(GREEN +"+"+addMP+" mana potions");
                System.out.println();
                
                if(!gameEndings.getUsedArtifact()){
                    boolean response = yesOrNo("Spare Khaimon?");
                    if(response){
                        gameEndings.setSpareKhaimon(true);
                    }
                }

                isDefeated=true;
                continue;
            }

            // If the enemy is stunned, skip their turn
            int khaiDamage=0;
            if (enemyStunned) {
                System.out.println(RED + khaimon.displayName() + " is stunned and skips their turn.\n");
                enemyStunned = false; // Reset stun status after skipping turn
            } else {
                // Enemy attacks a random player
                Character target;
                do {
                    target = party[(int) (Math.random() * party.length)];  // Select a random party member
                } while (!target.isAlive());  // Repeat if the selected character is not alive
                System.out.println("\n" + khaimon.displayName() + "'s turn and targets "+target.displayName()+"!");
                
                int move = ran.nextInt(3)+1;
                switch(move){
                    case 1:
                        khaiDamage = khaimon.skillOne(party, target);
                        if (khaiDamage==-99){
                            System.out.println("\n" + RED + khaimon.displayName() + RED + " HP: " + khaimon.getHP());  
                            break;
                        }
                        if (khaiDamage < 0) { // If damage is negative, it includes stun
                            partyStunned = true;
                            khaiDamage = Math.abs(khaiDamage + 2); // Extract actual damage
                            System.out.println("The party is stunned and will skip their next turn.");
                        }
                        System.out.println();
                        target.setHP(target.getHP() - khaiDamage);
                        break;
                    case 2:
                        khaiDamage = khaimon.skillTwo();
                        System.out.println();
                        target.setHP(target.getHP() - khaiDamage);
                        break;
                    case 3:
                        khaiDamage = khaimon.skillThree();
                        System.out.println();
                        target.setHP(target.getHP() - khaiDamage);
                        break;
                }
                
            }
            //buff deactivation for khaimon========
           if (khaimon.isBuffActive()) {
                    khaimon.setBuffTurnsRemaining(khaimon.getBuffTurnsRemaining() - 1); // Decrement buff turns
                    if (khaimon.getBuffTurnsRemaining() <= 0) {
                        khaimon.setBuffActive(false); // Deactivate buff
                        khaimon.setBuffPercentage(0); // Reset buff percentage
                        System.out.println(khaimon.displayName() + "'s buff has expired.");
                    }                
            }
            // Check if all players are dead
            if (!anyPlayerAlive(party)) {
                GameLogic.partyDied();
                if(!gameEndings.getHasArtifact()){
                    boolean response = yesOrNo("Sacrifice Jascha to save World Tree?");
                    if(response){
                        gameEndings.setSacrifice(true);
                    }
                }
                break;
            }
        }
        return (isDefeated) ? 1 : 0;
    }
}
