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

    String[] intro = {
            "Long ago, beneath the branches of the ancient World Tree, a great prophecy was whispered: "
          + "\na hero, reborn every 5000 years, would rise to save the world from darkness. Now, that time has come.",
            "In a world teetering on the edge of ruin, the once-vibrant World Tree fades as the Shadowmaw--a monstrous"
          + "\nspirit once its guardian--spreads corruption through the land. The skies darken, the fairies weaken, and hope grows thin.",
            "A young fairy named Elara, brave and hopeful, discovers the prophecy and sets out to find the reincarnated hero. "
          + "\nAlong her journey, she encounters Jascha, a warrior with a mysterious light, and together they venture through realms of wonder and peril.",
            "In the whimsical land of Wonderland, they meet Finn, a clever imp with a mischievous grin, and their party is complete.",
            "Together, Elara, Jascha, and Finn must face these shadows and decide the fate of their world. "
          + "\nWill they restore the World Tree's light or succumb to the darkness?",
            "The journey begins now..."
        };
      
        GameLogic.displayDialogue(intro);
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
