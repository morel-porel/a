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
public class MonsterOgre extends Monster {
    private int HP;
    
    public MonsterOgre (int HP){
        this.HP = HP;       
    }
    @Override
    public String displayName(){
        return "Ogre";
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
        int skillOneDamage = ran.nextInt(6) + 10;
        System.out.println("Ogre used Tree trunk! Dealt "+skillOneDamage +" damage!");
        return skillOneDamage;
    }
}
