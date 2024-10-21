/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author User
 */
public abstract class Character {
    //dbjahsbdjbjahsdbjh
    // hello Im here!
    // hello !!!!!!!!!!!!
    // i was here
    public abstract String displayName();
    public abstract void setHP(int HP);
    public abstract int getHP();
    public abstract int getMaxHP();
    public abstract void setMP(int MP);
    public abstract int getMP();
    public abstract int getMaxMP();
    public abstract int skillOne();
    public abstract int skillTwo();
    public abstract int skillThree();    
    public abstract String getSkillOne(); 
    public abstract String getSkillTwo(); 
    public abstract String getSkillThree(); 
    public abstract int skillOneMP(); 
    public abstract int skillTwoMP(); 
    public abstract int skillThreeMP();
    
    public boolean isAlive() {
        return getHP() > 0;
    }
    public void show(){
        System.out.println("You chose: ");
    }
    public abstract int applyBuff(int baseDamage);
    public abstract void setBuffPercentage(int buffPercentage);
    public abstract void setBuffActive(boolean buffActive);
    public abstract void setBuffTurnsRemaining(int buffTurnsRemaining);
    
}
