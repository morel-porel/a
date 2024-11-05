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

        String [] dialogue2 = {
            "Level 2: Wonderland\n" +
                    "A whimsical and chaotic realm, filled with strange creatures and unpredictable magic. Elara, the young fairy, " +
                    "\ntravels to this land in search of the hero foretold in the prophecy. Here, she meets Finn, a mischievous imp who " +
                    "\nclaims to be the chosen one, despite his unorthodox methods. Wonderland is filled with peril, but also strange allies.",
                    "[Elara and Jascha come across a mischievous imp, Finn, in a chaotic scuffle with a lone goblin.]",
                    "Finn: (laughing as he dodges) Come on, slowpoke! Can't you catch me?",
                    "Goblin: (growls) Enough! I'm gonna squash you, imp!",
                    "[Elara and Jascha exchange a look, amused but also concerned.]",
                    "Elara: He seems... enthusiastic?",
                    "Jascha: Or reckless.",
                    "[The goblin lunges, and Finn tumbles aside, finally noticing Elara and Jascha.]",
                    "Finn: Oh, a rescue party! Just what I needed!",
                    "Elara: Are you serious? Do you even want our help?",
                    "Finn: Help? It was all part of the plan! But, fin--let's make this goblin history."
        };
        GameLogic.displayDialogue(dialogue2);
        MonsterSlime slime = new MonsterSlime(200);
        GameLogic.battle(slime, party);

        String [] dialogue3 = {
            "They continued their journey and at the edge of Wonderland, an enormous ogre blocks their path, wielding a heavy club.]",
            "Ogre: Turn back, or face my wrath!",
            "Finn: Big words for a big brute. I think he's compensating for something.",
            "Jascha: Stay focused, Finn. This one's no joke.",
            "[The ogre swings his club, nearly hitting them as they dodge and prepare their counterattacks.]",
            "Elara: Jascha, aim for his legs! Finn, keep him distracted!",
            "Finn: Got it. Hey, big guy! Over here!"
        };
        
        GameLogic.displayDialogue(dialogue3);
         
        MonsterOgre ogre = new MonsterOgre(300); 
        GameLogic.battle(ogre, party);
        
        
        String [] dialogue4 = {
            "Level 3: The Dark Forest\n" +
                    "A twisted and corrupted forest where the Shadowmaw once resided. Now, the true enemy is revealed: Khaimon, " +
                    "\nthe fallen guardian of the World Tree, consumed entirely by the malevolent darkness. Khaimon was once a noble " +
                    "\nprotector of the World Tree, but the corruption transformed him into something far darker. His power is immense, " +
                    "\nand he seeks to bring all life to ruin, ensuring the World Tree withers and dies, leaving only desolation in his wake.",
                    "[In the depths of the Dark Forest, they face Shadowmaw, a towering, corrupted spirit with piercing red eyes.]",
                    "Shadowmaw: Fools! You think you can defy me? I am the darkness itself!",
                    "Elara: You were once a guardian, Shadowmaw. You don't have to be a monster!",
                    "Shadowmaw: Guardian? That was another life... The darkness is all that's left.",
                    "[The three heroes prepare for the hardest battle yet, fighting Shadowmaw with all their skills combined.]",
                    "Jascha: Elara, we can save him! I feel it!",
                    "Elara: Then let's give it everything we've got!"
        };
        
        GameLogic.displayDialogue(dialogue4);
        MonsterShadowMaw shadowmaw = new MonsterShadowMaw(500);
        GameLogic.battle(shadowmaw, party);
        
        String[] afterShadowmaw = {
            "[After a fierce battle, Shadowmaw finally succumbs, his dark form fading as he regains a glimmer of his old self.]",
            "Shadowmaw: (weakly) Thank you... You've freed me from the darkness...",
            "[As he fades away, a corrupted medallion drops, pulsing with dark energy.]",
            "Finn: Uh... that doesn't look friendly.",
            "Jascha: Should we take it or leave it?",
            "Elara: I don't know. But whatever we do, we have to be careful.",
            "[They decide together what to do with the medallion before heading further into the forest.]"
        };
        
        GameLogic.displayDialogue(afterShadowmaw);
        
        String[] finalBattleDia ={
          "Final Battle: Dark Khaimon, the Corrupted Dragon\n" +
          "[Scene: At the heart of the forest, the dark energy from the medallion has fused with Khaimon, " +
          "\nrevealing his transformation into Dark Khaimon, a powerful dark dragon.]",
          "Dark Khaimon: So, you've come to confront me... but I am no longer the guide you once knew. I am beyond saving!",
          "Elara: (shocked) Khaimon? It was you all along... Shadowmaw corrupted you!",
          "Jascha: Then let us free you from the darkness, once and for all.",
          "Finn: It's teacher versus students, huh? Alright, let's finish this!",
          
        };
        GameLogic.displayDialogue(finalBattleDia);
        FinalBattle.finalBattle(party);
        
        String[] finalBattleDia2 = {
         "Elara: Khaimon, you taught me to never give up. Don’t make me do this!",
         "Dark Khaimon: (struggling) There... is... no... other... way...",
         "[With a final, combined strike, they bring him down. As he falls, the darkness leaves him, and he returns to his former self.]",
         "Khaimon: (weakly) Thank you, Elara... Jascha... Finn. You were stronger than I ever imagined...",
         "[Khaimon fades, leaving behind a faint glow of the World Tree's restored energy. The forest breathes once more, vibrant and alive.]",
         "Elara: (tearfully) We've done it. We saved the forest... and him.",
         "Jascha: The darkness is finally gone.",
         "Finn: Guess we are heroes after all."
        };
        

    }

        //uncomment to try final battle vvv
        //FinalBattle.finalBattle(party); 
    }
    
}
