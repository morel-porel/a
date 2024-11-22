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
    static GameEndings gameEndings = new GameEndings();
    
    static CharacterJascha Jascha = new CharacterJascha(150, 200);
    static CharacterElara Elara = new CharacterElara(150, 200);
    static CharacterFinn Finn = new CharacterFinn(350, 200);
    static Character[] party = {Elara, Jascha};
    
    //random encounters
   
    public static String[] encounters = {"Battle","Battle","Battle","Battle","Shop"};
    public static Monster[] enemies = {
        new MonsterSlime(25),
        new MonsterSlime(25),
        new MonsterSlime(25),
        new MonsterSlime(25),
        new MonsterSlime(25)
    };
    
    //game variables
    public static boolean isRunning; //===============
    public static boolean miniBossDefeated = false;
    public static boolean decoyDefeated = false;
    public static int place = 0, act = 1;
    public static String[] places = {"World Tree", "Wonderland", "Dark Forest"};
    //Color codes, newly added.
    
    public static final String RESET = "\u001B[0m";  // Reset to default
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    
    public static final String RED_BACKGROUND = "\033[41m";
    public static final String BLUE_BACKGROUND = "\033[44m";
    
    private static <X> X[] addCharacter(X[] party, X member) {
        X[] newParty = Arrays.copyOf(party, party.length + 1);
        newParty[party.length] = member;
        return newParty;
    }
    
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
    
    public static void anythingToContinue(){
        System.out.print("\nEnter anything to continue...");
        scan.next();
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
    
    public static void displayDialogue(String[] dialogueLines) {
        Scanner scan = new Scanner(System.in);
        boolean skipped = false; 
        System.out.println("Dialogue starting... (Press ENTER to skip)");
        for (String line : dialogueLines) {
            System.out.println(line);
            
            if (scan.hasNextLine()) {
                String input = scan.nextLine();
                if (input.isEmpty()) {
                    skipped = true;  
                    break;  
                }
            }
        }
       
        if (skipped) {
            System.out.println("DIALOGUE SKIPPED.");
        }
    }
    
    public static void clearConsole(){
        for(int i=0;i<100;i++){
            System.out.println();
        }
    }
    
    public static void printSeparator(int n){
        for(int i = 0 ; i < n ; i++){
            System.out.print("-");
        }
        System.out.println();
    }
    
    public static void printHeading(String title){
        printSeparator(50);
        System.out.println(title);
        //insert print chuchu title here
        
        //System.out.println("LVL " + inv.getLvl() + ": EXP " + inv.getXp() + "/" + inv.getReqXp() +"\t\t\tGold: " + inv.getGold());
        printSeparator(50);
    }
        
    public static void startGame(){
        clearConsole();
        //StoryLine.printBackgroundStory();
        displayDialogue(StoryDialogue.intro);
        clearConsole();
        printSeparator(80);
        printSeparator(30);
        System.out.println(YELLOW + " _____     _                              __   _     _       _     _   " + RESET);
        System.out.println(YELLOW+ "|  ___|   | |                            / _| | |   (_)     | |   | |  "+ RESET);
        System.out.println(YELLOW+ "| |__  ___| |__   ___   ___  ___    ___ | |_  | |    _  __ _| |__ | |_ "+ RESET);
        System.out.println(YELLOW+ "|  __|/ __| '_ \\ / _ \\ / _ \\/ __|  / _ \\|  _| | |   | |/ _` | '_ \\| __|"+RESET);
        System.out.println(GREEN+"| |__| (__| | | | (_) |  __/\\__ \\ | (_) | |   | |___| | (_| | | | | |_ "+RESET);
        System.out.println(GREEN+"\\____/\\___|_| |_|\\___/ \\___||___/  \\___/|_|   \\_____/_|\\__, |_| |_|\\__|"+RESET);
        System.out.println(GREEN+"                                                        __/ |          "+RESET);
        System.out.println(GREEN+"                                                       |___/           "+RESET);
        System.out.println("TEXT RPG BY GROUP KATEYKI");
        printSeparator(30);
        printSeparator(80);
        
        
        displayDialogue(StoryDialogue.act1Intro);
        battle(new MonsterSlime(25), party);
        //set to true for game loop
        isRunning = true;
        //start main game
        gameLoop();
    }
    
    //method that changes the games values based 
    public static void checkAct(){
        //change act
        if(inv.getLvl() >= 10 && act ==1){
            act = 2;
            place = 1;
            displayDialogue(StoryDialogue.act1Outro);
            displayDialogue(StoryDialogue.act2Intro);
            party=addCharacter(party, Finn);
            enemies[0] = new MonsterGoblin(50);
            enemies[1] = new MonsterSlime(25);
            enemies[2] = new MonsterGoblin(50);
            enemies[3] = new MonsterSlime(25);
            enemies[4] = new MonsterGoblin(50);
            
            
            for(Character partyMember : party){
                partyMember.setHP(partyMember.getMaxHP());
            }
        } else if (inv.getLvl() >= 14 && act ==2){
            act = 3;
            place = 2;
            displayDialogue(StoryDialogue.act2Outro);
            displayDialogue(StoryDialogue.act3Intro);
            enemies[0] = new MonsterGoblin(50);
            enemies[1] = new MonsterGoblin(25);
            enemies[2] = new MonsterGoblin(50);
            enemies[3] = new MonsterOgre(75);
            enemies[4] = new MonsterGoblin(50);
        
            if(!decoyDefeated){
                battle(new MonsterShadowMaw(100), party);
                displayDialogue(StoryDialogue.act3AfterShadowmaw);
                encounterArtifact();
                decoyDefeated = true;
                
            }
        }
    }
    
    public static void encounterArtifact(){
        System.out.println("You discovered an artifact!");
        boolean response = yesOrNo("Take Artifact?");
        if(response){
            gameEndings.setHasArtifact(true);
        }
        
    }
    
    public static void randomEncounter(){
        int encounter = ran.nextInt(encounters.length);
        if (encounters[encounter].equals("Battle")) {
            Monster randomEnemy;
            do {
                randomEnemy = enemies[ran.nextInt(enemies.length)];
            } while (!randomEnemy.isAlive());
            battle(randomEnemy, party);
        } else {
            shopMenu(inv);
        }
            
    }
    
    public static void partyDied(){
        clearConsole();
        printHeading(RED + "All your characters have been defeated...");
    }
    
    //method to continue journey
    public static void continueJourney(){
        checkAct();
        if(act==2 && inv.getLvl()>=12 && !miniBossDefeated){            
            displayDialogue(StoryDialogue.act2MiniBoss);
            battle(new MonsterOgre(75), party);
            miniBossDefeated = true;
        }
            
        if(decoyDefeated && inv.getLvl()>=15){
            int khaimonDefeated = FinalBattle.finalBattle(party,gameEndings);
            gameEndings.setDefeatedKhaimon(khaimonDefeated == 1);            
            System.out.println(gameEndings.determineEnding());
            isRunning = false;
        }
        
        if(act != 3) //check if game isnt last act
            randomEncounter();
    }
    
    public static void characterInfo(Character[] party){
        clearConsole();
        printHeading("CHARACTER INFO");
        System.out.println("LVL " + inv.getLvl() + ": EXP " + inv.getXp() + "/" + inv.getReqXp() +"\t\t\tGold: " + inv.getGold());
        for (int i = 0; i < party.length; i++) {
            System.out.println(RESET + party[i].displayName() + RED + "(HP: " + party[i].getHP() + "/"+ RED +party[i].getMaxHP()+ BLUE +" | MP: "+party[i].getMP()+"/"+ BLUE +party[i].getMaxMP()+")"+RESET);
        }        
        
        boolean response = yesOrNo("Would you like to know more about the characters?");
        if(response){
            StoryLine.printCharacterBackstory();
            anythingToContinue();
        }
    }
    
    public static void printMenu(){
        clearConsole();
        printHeading(places[place]);
        System.out.println("Choose an action: ");
        printSeparator(20);
        System.out.println("1. Continue on your journey");
        System.out.println("2. Character Info");
        System.out.println("3. Exit Game");
        
    }
    
    //main game loop
    public static void gameLoop(){
        while(isRunning){
            printMenu();
            int input = readInt("-> ", 3);
            if(input == 1) {
                System.out.println();
            
                continueJourney();
            } else if (input == 2) {
                characterInfo(party);
            } else {
                isRunning = false;
            }
        }
    }
    //===========
    public static void battle(Monster monster, Character[] party){
        
        monster.show();
        System.out.println(monster.displayName()+" is ready to strike!"); 
        System.out.println("\n" + RED + monster.displayName() + RED + " HP: " + monster.getHP()+"\n");
        boolean enemyStunned = false;
        
        while (anyPlayerAlive(party) && monster.isAlive()) {
            printHeading("Your turn!");
            // Player selects a character
            for (int i = 0; i < party.length; i++) {
                if (party[i].isAlive()) {
                    System.out.println(RESET + (i + 1) + ". " + RESET + party[i].displayName() + RED + "(HP: " + party[i].getHP() + "/"+ RED +party[i].getMaxHP()+ BLUE +" | MP: "+party[i].getMP()+"/"+ BLUE +party[i].getMaxMP()+")"+ RESET);
                }
            }
            
            int choice;
            Character activePlayer;
            do{
                choice = readInt("\nChoose a character: ", party.length)-1;
                activePlayer = party[choice];
                if(!activePlayer.isAlive()){
                    scan.nextLine();
                    choice = -1;
                    System.out.println(activePlayer.displayName()+" is down!");
                }                
            } while(choice == -1);

            // Player chooses an action
            System.out.println("\n" + RESET + activePlayer.displayName() + "'s" +RESET +" turn." + RESET + RED + " (HP: "+ activePlayer.getHP() + "/"+activePlayer.getMaxHP()+ BLUE +" | MP: "+activePlayer.getMP()+"/"+activePlayer.getMaxMP()+")"+RESET);
            System.out.println("1. Use "+activePlayer.getSkillOne()+BLUE +" (MP: "+activePlayer.skillOneMP()+ " | "+RED+activePlayer.getDMG1()+")"+RESET);
            System.out.println("2. Use "+activePlayer.getSkillTwo()+BLUE +" (MP: "+activePlayer.skillTwoMP()+ " | "+RED+activePlayer.getDMG2()+")"+RESET);
            System.out.println("3. Use "+activePlayer.getSkillThree()+BLUE +" (MP: "+activePlayer.skillThreeMP()+ " | "+RED+activePlayer.getDMG3()+")"+RESET);
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
                        System.out.println(partyMember.displayName() + RESET +"'s buff has expired.");
                    }
                }
            }
            //===========
            if(!monster.isAlive()){
                System.out.println("\n" + RED + monster.displayName() + RED + " HP: 0");   
            } else {
                System.out.println("\n" + RED + monster.displayName() + RED + " HP: " + monster.getHP());   
            }
            
            // Check if the slime is dead end sequence
            if (!monster.isAlive()) {
                System.out.println("You defeated the " + monster.displayName() + "!");
                //recover downed members
                for(Character partyMember : party){
                    if(!partyMember.isAlive()){
                        partyMember.revive();
                    }
                }
                int addHP = ran.nextInt(10-5+1) + 5, addMP = ran.nextInt(10-5+1) + 5, addXP = 1000;
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
                System.out.println(RESET);
//                if(inv.getGold() >= 10){
//                    boolean response = yesOrNo("Would you like to visit the shop?");
//                    if(response){
//                        shopMenu(inv);
//                    }
//                }
                System.out.println("Prepare for next battle...");
                anythingToContinue();
                continue;
            }
            
            //printHeading(monster.displayName() + "'s turn!");
            // If the enemy is stunned, skip their turn
            if (enemyStunned) {
                System.out.println(RED + monster.displayName() + RED +" is stunned and skips their turn.\n");
                enemyStunned = false; // Reset stun status after skipping turn
            } else {
                // Enemy attacks a random player
                Character target;
                do {
                    target = party[(int) (Math.random() * party.length)];  // Select a random party member
                } while (!target.isAlive());  // Repeat if the selected character is not alive
                System.out.println("\n" + monster.displayName() + "'s" + RESET +" turn and targeted "+target.displayName()+"!");
                damage = 0;
                boolean partyStunned = false;
                if(monster instanceof MonsterShadowMaw){
                    int move = new Random().nextInt(2) + 1;
                    
                    switch(move){
                        case 1:
                            damage = monster.skillOne();
                            partyStunned = true;
                            target.setHP(target.getHP() - damage);
                            System.out.println("The party is stunned and skip their next turn.");
                            break;
                        case 2:
                            damage = monster.skillTwo();
                            target.setHP(target.getHP() - damage);
                            break;
                    }
                } else {
                    damage = monster.skillOne();                    
                    System.out.println();
                    target.setHP(target.getHP() - damage);
                }
            }
            
            
            // Check if all players are dead
            if (!anyPlayerAlive(party)) {
                partyDied();                
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
    
    public static void shopMenu(Inventory playerInventory) {
    boolean shopping = true;
    System.out.println();
    System.out.println("( ; 7,_>7) Hoho, you found me! Welcome to the Secret Shop!");
        while (shopping) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Buy Health Potion (10 gold each)");
            System.out.println("2. Buy Mana Potion (10 gold each)");
            System.out.println("3. Exit Shop");
            int choice = readInt("\nEnter your choice (1-3): "+RESET, 3 );
            switch (choice) {
                case 1:
                    System.out.print("How many Health Potions would you like to buy? ");
                    int healthPotionQuantity = readInt("\nQuantity: ", 100);
                    int healthPotionCost = healthPotionQuantity * 10;
                    if (playerInventory.getGold() >= healthPotionCost) {
                        playerInventory.addHealthPotion(healthPotionQuantity);
                        playerInventory.spendGold(healthPotionCost);
                        System.out.println("You bought " +RED+ healthPotionQuantity + RED+ " Health Potions!"+RESET);
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
                        System.out.println("You bought " +BLUE+ manaPotionQuantity +BLUE+ " Mana Potions!"+RESET);
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
