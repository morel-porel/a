/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
import java.util.Random;
/**
 *
 * @author Kaka
 */
public class MonsterShadowMaw extends Monster{
     private int HP;
    
    
    public MonsterShadowMaw (int HP){
        this.HP = HP;       
    }
    @Override
    public String displayName(){
        return "Shadow Maw";
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
        int skillOneDamage = ran.nextInt(6) + 5;
        System.out.println("Shadow Maw used Dark shriek! Dealt "+skillOneDamage +" damage!");
        return skillOneDamage;
    }
    @Override
    public int skillTwo(){
       Random ran = new Random();
       int skillTwoDamage = ran.nextInt(36) + 15;
       System.out.println("Shadow Maw used Shadow wave! Dealth "+skillTwoDamage +" damage!");
       return skillTwoDamage;
       
    }
}
