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

    String[] dialogue = {
            "Level 1: The World Tree\n" +
                    "The heart of the world, vibrant and full of life. Fairy Queen Tia rules from here, surrounded by a beautiful forest " +
                    "\nwhere fairies sing, dance, and live in harmony. However, this tranquility is threatened as the Shadowmaw's dark influence " +
                    "\nbegins to seep through the roots of the World Tree.",
                    "Khaimon: Elara, the path you're about to take is dangerous. The darkness in the forest isn’t something even magic can easily quell.",
                    "Elara: Master Khaimon… you've always taught us to protect our world. I must go--I feel this is my calling.",
                    "Khaimon: (pauses, seeming torn) Yes… But remember, light can be corrupted, even the brightest. " +
                    "\nSometimes the heart must make choices beyond what we understand.",
                    "[As Elara ventures forward, she encounters a faint glow. A figure appears—Jascha, who radiates with a faint light.]",
                    "Elara: (gasps) It's you… The hero reborn! Just like the prophecy said.",
                    "Jascha: (confused) Hero? I’m just… me. I don’t know anything about prophecies.",
                    "Elara: Then let me explain later. For now, we need to face this together!",
                    "[A single slime, corrupted and oozing with darkness, appears and slithers toward them.]",
                    "Jascha: Alright, I'm with you. That thing looks like it could use some purification.",
                    "Elara: Stick close. This may be small, but it’s stronger than it looks."
        };

        // Call the dialogue method
        GameLogic.displayDialogue(dialogue);
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

        //uncomment to try final battle vvv
        //FinalBattle.finalBattle(party); 
    }
    
}
