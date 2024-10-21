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
    private int HP;
    private int maxHP;
    private int MP;
    private int maxMP;
    public CharacterElara (int HP, int MP){
        this.HP = HP;
        this.maxHP = HP;
        
        this.MP = MP;
        this.maxMP = MP;        
    }
    @Override
    public String displayName(){
        return "Elara";
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
        int skillOneDamage = ran.nextInt(11);
        System.out.println("Elara used Staff Attack! Dealt "+skillOneDamage +" damage!");
        return skillOneDamage;
    }
    @Override    
    public int skillTwo(){
        Random ran = new Random();
        if(MP>=20){
            MP-=20;
            int healAmount = ran.nextInt(30-5+1) + 5; 
            setHP(getHP() + healAmount); 
            
            System.out.println("Elara healed herself for "+healAmount+" HP!");
            return healAmount;
        } else {
            System.out.println("Not enough MP.");
            return -1;
        }
    }
    
    public int skillTwo(Character[] party){
        Scanner scan = new Scanner(System.in);
        Random ran = new Random();
        if(MP>=20){
            MP-=20;
            System.out.println("Elara used Heal! Choose a party member to heal:");
            for (int i = 0; i < party.length; i++) {
                if (party[i].isAlive()) {
                    System.out.println((i + 1) + ". " + party[i].displayName() + " (HP: " + party[i].getHP() + ")");
                }
            }
            int targetIndex = scan.nextInt() - 1;
            Character target = party[targetIndex];
            int healAmount = ran.nextInt(30-5+1) + 5; 
            target.setHP(target.getHP() + healAmount); 
            
            System.out.println("Elara healed "+target.displayName() +" for "+healAmount+" HP!");
            return healAmount;
        } else {
            System.out.println("Not enough MP.");
            return -1;
        }
    }
    //need fix implement pls
    @Override
    public int skillThree(){
        Random ran = new Random();
        if(MP>=50){
            int buffPercentage = ran.nextInt(21);
            MP-=50;
            System.out.println("Elara used Buff! Attacks now deal "+buffPercentage +"% more damage!");
            return buffPercentage;
        } else {
            System.out.println("Not enough MP.");
            return -1;
        }
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
 
}
