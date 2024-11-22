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

        GameLogic.startGame();
        GameLogic.credits();
//        CharacterJascha Jascha = new CharacterJascha(150, 1000);
//        CharacterElara Elara = new CharacterElara(150, 1000);
//        CharacterFinn Finn = new CharacterFinn(150, 1000);
//        Character[] party = { Jascha, Elara, Finn };
        
        //GameLogic.printHeading("Battle!");
        
        MonsterSlime slime = new MonsterSlime(100);        
        MonsterOgre ogre = new MonsterOgre(1);
        //GameLogic.battle(slime, party);
        //GameLogic.battle(ogre, party);
        
        
        
        
        //FinalBattle.finalBattle(party);
        
    }
    
}
