/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
import java.util.Scanner;
/**
 *
 * @author User
 */
import java.util.Random;
public class CharacterElara extends Character{
    public static final String RESET = "\u001B[0m";  // Reset to default
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String RED_BACKGROUND = "\033[41m";
    public static final String YELLOW_BACKGROUND = "\033[43m";
    public static final String GREEN_BACKGROUND = "\033[42m";
    private int HP;
    private int maxHP;
    private int MP;
    private int maxMP;

    //buff related variables
    private boolean buffActive = false;
    private int buffPercentage = 0;
    private int buffDamage = 0;
    private int buffTurnsRemaining;

    //range
    private String range1;
    private String range2;
    private String range3;
    
    public CharacterElara (int HP, int MP){
        this.HP = HP;
        this.maxHP = HP;
        
        this.MP = MP;
        this.maxMP = MP;        
    }
    @Override
    public String displayName(){
        return MAGENTA +"Elara";
    }
    @Override
    public void setHP(int HP){
        this.HP = Math.min(HP, maxHP);
    }
    @Override
    public int getHP(){
        return HP;
    }
    @Override
    public int getMaxHP() {
        return maxHP;
    }
    @Override
    public void setMP(int MP){
        this.MP = Math.min(MP, maxMP);
    }
    @Override
    public int getMP(){
        return MP;
    }
    @Override
    public int getMaxMP() {
        return maxMP;
    }
    @Override
    public int skillOne(){
        Random ran = new Random();
        int skillOneDamage = applyBuff(ran.nextInt(11));
        if(isBuffActive()){
            System.out.println("Elara used Staff Attack! Dealt "+(skillOneDamage-buffDamage) +" + "+buffDamage+" damage!");
        } else {
            System.out.println("Elara used Staff Attack! Dealt "+skillOneDamage +" damage!");            
        }
        return skillOneDamage;
    }
    @Override    
    public int skillTwo(){
        Random ran = new Random();
        if(MP>=20){
            MP-=20;
            int healAmount = ran.nextInt(30-5+1) + 5; 
            setHP(getHP() + healAmount); 
            
            System.out.println(RESET+ "Elara healed herself for "+ RED_BACKGROUND +healAmount+" HP!" +RESET);
            return healAmount;
        } else {
            System.out.println("Not enough MP.");
            return 0;
        }
    }
    
    public int skillTwo(Character[] party){
        Scanner scan = new Scanner(System.in);
        Random ran = new Random();
        if(MP>=20){
            MP-=20;
            System.out.println("\nElara used Heal!");
            for (int i = 0; i < party.length; i++) {
                if (party[i].isAlive()) {
                    System.out.println((i + 1) + ". " + party[i].displayName() + RESET +" (HP: " + party[i].getHP() + ")" + RESET);
                }
            }
            int targetIndex;
            Character target;
            do{
                targetIndex = GameLogic.readInt("\nChoose a party member to heal: ", 3)-1;
                target = party[targetIndex];
                if(!target.isAlive()){
                    scan.nextLine();
                    targetIndex = -1;
                    System.out.println(target.displayName()+" is down!");
                }                
            } while(targetIndex == -1);
            
            if(target instanceof CharacterElara){
                skillTwo();
                return 0;
            }
            
            int healAmount = ran.nextInt(30-5+1) + 5; 
            target.setHP(target.getHP() + healAmount);             
            System.out.println(RESET +"Elara healed "+target.displayName() +RESET+" for "+ GREEN +healAmount+" HP!");
            return healAmount;
        } else {
            System.out.println("Not enough MP.");
            return 0;
        }
    }
    
    //final battle ==================
    public int skillTwo(String name){
        Random ran = new Random();
        if(MP>=20){
            MP-=20;
            int healAmount = ran.nextInt(30-5+1) + 5;         
            System.out.println("Elara healed "+name +" for " +healAmount+" HP!");
            return healAmount;
        } else {
            System.out.println("Not enough MP.");
            return 0;
        }
    }
    
    //to be fixed skillThree    
    @Override 
    public int skillThree(){
        Random ran = new Random();
        if(MP>=50){
            int buffPercentage = ran.nextInt(50-10+1) + 10;
            MP-=50;
            System.out.println("Elara used Buff! Party attacks now deal "+ YELLOW +buffPercentage +"%" + RESET +" more damage in the next turn!");
            return buffPercentage;
        } else {
            System.out.println("Not enough MP.");
            return 0;
        }
    }
    //for final battle ============
    public int skillThree(String name){
        Random ran = new Random();
        if(MP>=50){
            int buffPercentage = ran.nextInt(50-10+1) + 10;
            MP-=50;
            System.out.println("Elara used Buff on "+name+"! His attacks now deal "+buffPercentage +"% more damage in the next turn!");
            return buffPercentage;
        } else {
            System.out.println("Not enough MP.");
            return 0;
        }
    }    
    @Override
    public int applyBuff(int baseDamage){
        if(buffActive){
            buffDamage = baseDamage * buffPercentage / 100;
            int modifiedDamage = baseDamage + buffDamage;
            return modifiedDamage;//increase damage by 20%
        }
        return baseDamage;// No buff applied
    }
    @Override
    public void setBuffPercentage(int buffPercentage){
        this.buffPercentage = buffPercentage;
    }
    @Override    
    public void setBuffActive(boolean buffActive){
        this.buffActive = buffActive;
    }
    @Override    
    public void setBuffTurnsRemaining(int buffTurnsRemaining){
        this.buffTurnsRemaining = buffTurnsRemaining;
    }
    @Override    
    public boolean isBuffActive(){
        return buffActive;
    }
    @Override    
    public int getBuffTurnsRemaining(){
        return buffTurnsRemaining;
    }
    @Override
    public String getSkillOne() {
        return "Staff Attack";
    }

    @Override
    public String getSkillTwo() {
        return "Heal";
    }

    @Override
    public String getSkillThree() {
        return "Buff";
    }
    @Override
    public int skillOneMP() {
        return 0;
    }

    @Override
    public int skillTwoMP() {
        return 20;
    }

    @Override
    public int skillThreeMP() {
        return 50;
    }
    
    @Override
    public void lvlUpCharacter(){
        maxHP += 10;
        maxMP += 50;
        HP=maxHP;
        MP=maxMP;
              
    }

    // range1
    @Override
    public void setDMG1(String range1){
        this.range1 = range1;
    }
    @Override 
    public String getDMG1(){
        return "DMG: 1";
    }
    // range 2
    @Override
    public void setDMG2(String range2){
        this.range2 = range2;
    }
    @Override 
    public String getDMG2(){
        return GREEN + "HEAL: 5HP - 30HP";
    }
    // range 3
    @Override
    public void setDMG3(String range3){
        this.range3 = range3;
    }
    @Override 
    public String getDMG3(){
        return YELLOW + "BUFF: 10DMG - 50DMG";
    }
}
