/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.game;
import java.util.Random;
/**
 *
 * @author User
 */
public class MonsterSlime extends Monster{
    private int HP;
    
    @Override
    public String displayName(){
        return "Slime";
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
        int skillOneDamage = ran.nextInt(6);
        System.out.println("Slime used slime ball! Dealt "+skillOneDamage +" damage!");
        return skillOneDamage;
    }
}
