/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author hi
 */

import java.util.*;
public class StoryLine {
    static Scanner scan = new Scanner(System.in);
    // Background story for the game
    private static final String BACKGROUND_STORY =
            "In the heart of the world stands the majestic World Tree, a beacon of life and magic. \n" +
            "Here, the benevolent Fairy Queen Tia rules, and her court thrives in a world of laughter and light. \n" +
            "However, a shadow lurks in the Dark Forest, spreading its corrupting influence through the World Tree \n" +
            "and threatening the balance of life. The darkness comes in the form of the Shadowmaw, a once-noble guardian \n" +
            "of the World Tree, now twisted by a malevolent force. The fairies, once full of joy and vitality, \n" +
            "are falling to despair are falling to despair as the Shadowmaw's corruption spreads, weakening their ruler, \n" +
            "Queen Tia. Amidst the chaos, a young fairy named Elara embarks on a quest to find a hero who will save\n" +
            "the World Tree.\n";

    // Character Backstories
    public static final String JASCHA_BACKSTORY = """
            Jascha, the mysterious prophesied hero, is the reincarnation of a legendary warrior from 5,000 years ago.  
            His past life was one of great valor and strength, and now, as Jascha, he is destined to defeat the Shadowmaw. 
            His power is immense, harnessing ancient techniques like Flashstrike Thunderclap and Moonlight Swords to 
            cut down enemies, along with the Ancient Kick that can shake the very earth itself.
                                                  """;

    public static final String ELARA_BACKSTORY = """
            Elara Teya Mabangis, the Fairy Support, was born under the protective boughs of the World Tree, with wings as bright 
            as the morning sun. Her laughter once echoed through the sun-dappled glades. Though her spirit is pure and adventurous, 
            she's drawn to the encroaching darkness of the Dark Forest. Elara discovered the devastation caused by the Shadowmaw, 
            which filled her with a fierce determination. Her powers include Healing, Buffing, and providing Shields to protect 
            her allies during the quest
                                                 """;

    public static final String FINN_BACKSTORY = """
            Finn, the mischievous imp from Wonderland, thrives in chaos. His world is a land of talking animals, bizarre creatures, 
            and constant mischief. He fears nothing, even the Shadowmaw, and sees the dark forest as nothing more than a playground 
            for pranks. His skills include setting Traps, using Bombs, and deploying the ultimate move: 'I am Atomic,' a powerful 
            explosive attack that wreaks havoc on all enemies in range
                                                """;
            

    public static final String KHAIMON_BACKSTORY = """
            Khaimon was once a revered fairy professor at the World Tree, teaching the rare art of object-oriented java magic. 
            However, after a dark encounter in the Dark Forest, Khaimon became corrupted. The once-wise guide is now a twisted version 
            of himself, torn between his old wisdom and the darkness consuming him. He stands as both an enigmatic NPC guide and a 
            looming threat in the game.
                                                   """;

    public static final String FAIRY_QUEEN_TIA_BACKSTORY = """
            Fairy Queen Tia, graceful and radiant, has ruled the World Tree for centuries, nurturing her people with powerful healing 
            magic and protective shields. She has always been a beacon of hope and vitality for the fairies. However, with the 
            corruption spreading, her powers have begun to wane, forcing her to rely on Elara and other heroes to restore balance. 
                                                           """;

    // Levels in the game
    public enum World {
        WORLD_TREE,
        WONDERLAND,
        DARK_FOREST
    }

    // Each world level description
    private static final String WORLD_TREE_DESCRIPTION =
            "Level 1: The World Tree\n" +
                    "The heart of the world, vibrant and full of life. Fairy Queen Tia rules from here, surrounded by a beautiful forest " +
                    "where fairies sing, dance, and live in harmony. However, this tranquility is threatened as the Shadowmaw's dark influence " +
                    "begins to seep through the roots of the World Tree.";

    private static final String WONDERLAND_DESCRIPTION =
            "Level 2: Wonderland\n" +
                    "A whimsical and chaotic realm, filled with strange creatures and unpredictable magic. Elara, the young fairy, " +
                    "travels to this land in search of the hero foretold in the prophecy. Here, she meets Finn, a mischievous imp who " +
                    "claims to be the chosen one, despite his unorthodox methods. Wonderland is filled with peril, but also strange allies.";

    private static final String DARK_FOREST_DESCRIPTION =
            "Level 3: The Dark Forest\n" +
                    "A twisted and corrupted forest where the Shadowmaw once resided. Now, the true enemy is revealed: Khaimon, " +
                    "the fallen guardian of the World Tree, consumed entirely by the malevolent darkness. Khaimon was once a noble " +
                    "protector of the World Tree, but the corruption transformed him into something far darker. His power is immense, " +
                    "and he seeks to bring all life to ruin, ensuring the World Tree withers and dies, leaving only desolation in his wake.";

    // Method to print the background story
    public static void printBackgroundStory() {
        System.out.println(BACKGROUND_STORY);
    }

    // Method to print character backstories
    public static void printCharacterBackstory() {
        boolean isValid = false;
        while(!isValid){
            System.out.print("Type the name of the character: ");
            String characterName = scan.nextLine();
            switch (characterName.toLowerCase()) {
                case "jascha":
                    System.out.println(JASCHA_BACKSTORY);
                    isValid = true;
                    break;
                case "elara":
                    System.out.println(ELARA_BACKSTORY);
                    isValid = true;
                    break;
                case "finn":
                    System.out.println(FINN_BACKSTORY);
                    isValid = true;
                    break;
                case "khaimon":
                    System.out.println(KHAIMON_BACKSTORY);
                    isValid = true;
                    break;
                case "tia":
                    System.out.println(FAIRY_QUEEN_TIA_BACKSTORY);
                    isValid = true;
                    break;
                default:
                    System.out.println("Character not found.");
            }
        }
    }

    // Method to print the description of a world
    public static void printWorldDescription(World world) {
        switch (world) {
            case WORLD_TREE:
                System.out.println(WORLD_TREE_DESCRIPTION);
                break;
            case WONDERLAND:
                System.out.println(WONDERLAND_DESCRIPTION);
                break;
            case DARK_FOREST:
                System.out.println(DARK_FOREST_DESCRIPTION);
                break;
            default:
                System.out.println("Unknown world.");
        }
    }

    // Method to provide the narrative flow between the worlds
    public void progressThroughStory(World world) {
        System.out.println("\n--- The Story Progresses ---");
        switch (world) {
            case WORLD_TREE:
                System.out.println("The journey begins at the base of the World Tree. Elara must act quickly to stop the " +
                        "\nShadowmaw's corruption from spreading further.");
                break;
            case WONDERLAND:
                System.out.println("Elara enters Wonderland, where she meets Finn the imp. They encounter bizarre landscapes " +
                        "\nand face trials that test their abilities, as they seek the true hero who can defeat the Shadowmaw.");
                break;
            case DARK_FOREST:
                System.out.println("The journey leads into the heart of the Dark Forest. There, the heroes encounter Khaimon, the " +
                        "\nfallen guardian of the World Tree. Khaimon, consumed by darkness, believes that only through destruction " +
                        "\ncan he achieve peace. Elara and her companions must stop him and purify his heart to restore balance.");
                break;
            default:
                System.out.println("Unknown stage in the journey.");
        }
    }
    
    

    // Main method for testing
    /*
    public static void main(String[] args) {
        StoryLine story = new StoryLine();

        // Print the background story
        story.printBackgroundStory();

        // Print the descriptions of each world
        System.out.println("\n--- Worlds ---");
        story.printWorldDescription(World.WORLD_TREE);
        story.printWorldDescription(World.WONDERLAND);
        story.printWorldDescription(World.DARK_FOREST);

        // Print character backstories
        System.out.println("\n--- Character Backstories ---");
        story.printCharacterBackstory("jascha");
        story.printCharacterBackstory("elara");
        story.printCharacterBackstory("finn");
        story.printCharacterBackstory("khaimon");
        story.printCharacterBackstory("tia");

        // Progress through the story levels
        story.progressThroughStory(World.WORLD_TREE);
        story.progressThroughStory(World.WONDERLAND);
        story.progressThroughStory(World.DARK_FOREST);
    }
    */
    
}


