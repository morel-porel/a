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
        /*
        System.out.println(" _____     _                              __   _     _       _     _   ");
        System.out.println("|  ___|   | |                            / _| | |   (_)     | |   | |  ");
        System.out.println("| |__  ___| |__   ___   ___  ___    ___ | |_  | |    _  __ _| |__ | |_ ");
        System.out.println("|  __|/ __| '_ \\ / _ \\ / _ \\/ __|  / _ \\|  _| | |   | |/ _` | '_ \\| __|");
        System.out.println("| |__| (__| | | | (_) |  __/\\__ \\ | (_) | |   | |___| | (_| | | | | |_ ");
        System.out.println("\\____/\\___|_| |_|\\___/ \\___||___/  \\___/|_|   \\_____/_|\\__, |_| |_|\\__|");
        System.out.println("                                                        __/ |          ");
        System.out.println("                                                       |___/           ");
        */
        CharacterJascha Jascha = new CharacterJascha(150, 50);
        CharacterElara Elara = new CharacterElara(150, 50);
        CharacterFinn Finn = new CharacterFinn(150, 50);
        Character[] party = { Jascha, Elara, Finn };
        MonsterSlime slime = new MonsterSlime(200);
        GameLogic.battle(slime, party);

    }
    
}
