/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.game;

/**
 *
 * @author User
 */
import java.util.Random;
public class CharacterJascha extends Character{
    private int HP;
    @Override
    public void displayName(){
        System.out.println("Jascha");
    }
    @Override
    public void setHP(int HP){
        this.HP = HP;
    }
    @Override
    public int getHP(){
        return HP;
    }
    @Override
    public int skillOne(){
        Random ran = new Random();
        int skillOneDamage = ran.nextInt(16);
        System.out.println("Jascha used Flashstrike Thunderclap! Dealt "+skillOneDamage +" damage!");
        return skillOneDamage;
    }
    @Override
    public int skillTwo(){
        Random ran = new Random();
        int skillTwoDamage = ran.nextInt(21);
        System.out.println("Jascha used Moonlight Swords! Dealt "+skillTwoDamage +" damage!");
        return skillTwoDamage;
    }
    @Override
    public int skillThree(){
        Random ran = new Random();
        int skillThreeDamage = ran.nextInt(51);
        System.out.println("Jascha used Ancient Kick! Dealt "+skillThreeDamage +" damage!");
        return skillThreeDamage;
    }
 
}
