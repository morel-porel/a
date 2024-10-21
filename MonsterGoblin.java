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
public class MonsterGoblin extends Monster{
     private int HP;
    
    public MonsterGoblin (int HP){
        this.HP = HP;       
    }
    @Override
    public String displayName(){
        return "Goblin";
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
        System.out.println("Goblin used Club smush! Dealt "+skillOneDamage +" damage!");
        return skillOneDamage;
    }
}

