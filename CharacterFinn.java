/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.Random;

/**
 *
 * @author User
 */
public class CharacterFinn extends Character{
    private int HP;
    private int maxHP;
    private int MP;
    private int maxMP;

        //buff related variables
    private boolean buffActive = false;
    private int buffPercentage = 0;
    private int buffDamage = 0;
    private int buffTurnsRemaining;
    
    public CharacterFinn (int HP, int MP){
        this.HP = HP;
        this.maxHP = HP;
        
        this.MP = MP;
        this.maxMP = MP;        
    }
    @Override
    public String displayName(){
        return "Finn";
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

    //CHANGES
   @Override
    public int skillOne() {
        Random ran = new Random();
        if (MP >= 30) {
            int skillOneDamage = applyBuff(ran.nextInt(11) + 5); // 5 to 15 damage range
            MP -= 30;
            MP -= 30;
            if (ran.nextInt(100) < 50) { // % chance to stun
                if(isBuffActive()){
                    System.out.println("Finn used Traps! Dealt " + (skillOneDamage-buffDamage) +" + "+buffDamage+" damage and stunned the enemy!");
                } else {
                    System.out.println("Finn used Traps! Dealt " + skillOneDamage + " damage and stunned the enemy!");
                }
                
                return -(skillOneDamage + 2); // Use negative damage to indicate stun
            }
            if(isBuffActive()){
                System.out.println("Finn used Traps! Dealt " + (skillOneDamage-buffDamage) +" + "+buffDamage+" damage!");
            } else {
                System.out.println("Finn used Traps! Dealt " + skillOneDamage + " damage!");
            }            
            return skillOneDamage; // Normal damage
        } else {
            System.out.println("Not enough MP.");
            return 0; // No damage, not enough MP
        }
    }
    //basta
    @Override
    public int skillTwo(){
        Random ran = new Random();
        if(MP>=20){
            int skillTwoDamage = applyBuff(ran.nextInt(25-10+1) + 10);
            MP-=20;
            if(isBuffActive()){
                System.out.println("Finn used Bombs! Dealt "+(skillTwoDamage-buffDamage) +" + "+buffDamage+" damage!");
            } else {
                System.out.println("Finn used Bombs! Dealt "+skillTwoDamage +" damage!");
            }            
            return skillTwoDamage;
        } else {
            System.out.println("Not enough MP.");
            return 0;
        }
    }
    @Override
    public int skillThree(){
        Random ran = new Random();
        if(MP>=5){
            if(HP<=50){
                System.out.println("Not enough HP! 50 HP Needed.");
                return 0;
            }
            int skillThreeDamage = applyBuff(ran.nextInt(100-75+1) + 75);
            MP-=5;
            HP-=50;
            if(isBuffActive()){
                System.out.println("Finn used 'I am Atomic'! Dealt "+(skillThreeDamage-buffDamage) +" + "+buffDamage+" damage!");
            } else {
                System.out.println("Finn used 'I am Atomic'! Dealt "+skillThreeDamage +" damage!");
            }            
            return skillThreeDamage;
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
        return "Traps (Can stun for one turn)";
    }

    @Override
    public String getSkillTwo() {
        return "Bombs";
    }

    @Override
    public String getSkillThree() {
        return "'I am Atomic' (Depletes Finn's Health)";
    }    
    
    @Override
    public int skillOneMP() {
        return 30;
    }

    @Override
    public int skillTwoMP() {
        return 20;
    }

    @Override
    public int skillThreeMP() {
        return 5;
    }
 
}
