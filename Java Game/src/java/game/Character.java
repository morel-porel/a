/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.game;

/**
 *
 * @author User
 */
public abstract class Character {
    
    public abstract void displayName();
    public abstract void setHP(int HP);
    public abstract int getHP();
    public abstract int skillOne();
    public abstract int skillTwo();
    public abstract int skillThree();
    
    public void show(){
        System.out.println("You chose: ");
    }
}
