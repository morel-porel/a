/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.game;

/**
 *
 * @author User
 */
public abstract class Monster {
    
    public abstract String displayName();
    public abstract void setHP(int HP);
    public abstract int getHP();
    public abstract int skillOne();
    public int skillTwo(){
        return 0;
    }    
    public boolean isAlive() {
        return getHP() > 0;
    }
    public void show(){
        System.out.println("A monster appeared: ");
    }
}
