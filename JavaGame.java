/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package game;

/**
 *
 * @author User
 */
public class JavaGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Define characters
        CharacterJascha Jascha = new CharacterJascha(150, 50);
        CharacterElara Elara = new CharacterElara(150, 50);
        CharacterFinn Finn = new CharacterFinn(150, 50);
        Character[] party = { Jascha, Elara, Finn };
        MonsterSlime slime = new MonsterSlime(200);
        GameLogic.battle(slime, party);

    }
    
}
