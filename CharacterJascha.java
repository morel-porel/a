/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
/**
 *
 * @author User
 */
import java.util.Random;
public class CharacterJascha extends Character{
    private int HP;
    private int maxHP;
    private int MP;
    private int maxMP;
    public CharacterJascha (int HP, int MP){
        this.HP = HP;
        this.maxHP = HP;
        
        this.MP = MP;
        this.maxMP = MP;        
    }
    @Override
    public String displayName(){
        return "Jascha";
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
        if(MP>=10){
            int skillOneDamage = ran.nextInt(15-5+1) + 5;
            MP-=10;
            System.out.println("Jascha used Flashstrike Thunderclap! Dealt "+skillOneDamage +" damage!");
            return skillOneDamage;
        } else {
            System.out.println("Not enough MP.");
            return 0;
        }
    }
    @Override
    public int skillTwo(){
        Random ran = new Random();
        if(MP>=15){
            int skillTwoDamage = ran.nextInt(20-5+1) + 5;
            MP-=15;
            System.out.println("Jascha used Moonlight Swords! Dealt "+skillTwoDamage +" damage!");
            return skillTwoDamage;
        } else {
            System.out.println("Not enough MP.");
            return 0;
        }
    }
    @Override
    public int skillThree(){
        Random ran = new Random();
        if(MP>=35){
            int skillThreeDamage = ran.nextInt(50-15+1) + 15;
            MP-=35;
            System.out.println("Jascha used Ancient Kick! Dealt "+skillThreeDamage +" damage!");
            return skillThreeDamage;
        } else {
            System.out.println("Not enough MP.");
            return 0;
        }
    }
    @Override
    public String getSkillOne() {
        return "Flashstrike Thunderclap";
    }

    @Override
    public String getSkillTwo() {
        return "Moonlight Swords";
    }

    @Override
    public String getSkillThree() {
        return "Ancient Kick";
    }
    
    @Override
    public int skillOneMP() {
        return 10;
    }

    @Override
    public int skillTwoMP() {
        return 15;
    }

    @Override
    public int skillThreeMP() {
        return 35;
    }
 
}
