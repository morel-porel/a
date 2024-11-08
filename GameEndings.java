package game;

public class GameEndings {

    // Flags for conditions
    private boolean hasArtifact = false;
    private boolean usedArtifact = false;
    private boolean defeatedKhaimon = false;
    private boolean sacrifice = false;
    private boolean spareKhaimon = false;

    // Setter methods
    public void setHasArtifact(boolean hasArtifact) {
        this.hasArtifact = hasArtifact;
    }

    public void setUsedArtifact(boolean usedArtifact) {
        this.usedArtifact = usedArtifact;
    }

    public void setDefeatedKhaimon(boolean defeatedKhaimon) {
        this.defeatedKhaimon = defeatedKhaimon;
    }

    public void setSacrifice(boolean sacrifice) {
        this.sacrifice = sacrifice;
    }

    public void setSpareKhaimon(boolean spareKhaimon) {
        this.spareKhaimon = spareKhaimon;
    }

    // Method to determine the ending
    public String determineEnding() {
        if (hasArtifact) {
            if (usedArtifact && defeatedKhaimon) {
                return "Bittersweet Ending - Jascha is Corrupted\n"
                        + "In the climactic battle, the party defeats Khaimon, but Jascha is overtaken by corrupted energy. \n"
                        + "Consumed by darkness, Jascha is sealed in the artifact. \n"
                        + "The party leaves with the World Tree restored, but Jascha's sacrifice a bittersweet memory of their victory.\n";
            } else {
                return evaluateEnding3(); // Check paths leading to Ending 3
            }
        } else { 
            if (defeatedKhaimon) {
                return evaluateEnding3();
            } else {
                return evaluateEnding2(); // Check paths leading to Ending 2
            }
        }
    }

    // Ending 2 evaluation
    private String evaluateEnding2() {
        if (sacrifice) {
            return "Sacrifice Ending - The Jascha sacrifices themselves to try to stop Khaimon.\n"
                    + "As the battle peaks, Khaimon’s corruption overwhelms. The hero Jascha decides to make the ultimate sacrifice. \n"
                    + "While the party fights shadows, the Jascha channels their life force to banish the darkness. \n"
                    + "With a final strike, they seal Khaimon and the corruption within themselves. \n"
                    + "The darkness vanishes, leaving peace.\n" 
                    + "\n" 
                    + "The forest flourishes, the World Tree renews, and Khaimon’s reign ends. \n"
                    + "The party returns, mourning their hero whose light now eternally brightens the forest.";
        } else {
            return "Bad Ending - The hero fails to defeat or stop Khaimon.\n"
                    + "despite their courage, the party is overwhelmed by Khaimon's dark magic and falls one by one. \n"
                    + "With no one left to stop him, Khaimon corrupts the World Tree, solidifying his rule.\n" 
                    +"\n" 
                    + "The land becomes a shadowy wasteland, with corrupted creatures prowling the woods \n"
                    + "and the twisted World Tree spreading darkness. \n"
                    + "The hero and their party are remembered and mourned as the last hope that fell to darkness.\n" 
                    + "\n" 
                    +"The Dark Forest becomes a place of despair, \n"
                    + "a stark reminder of the lurking dangers when even heroes cannot prevail.\n";
        }
    }

    // Ending 3 evaluation with refined conditions
    private String evaluateEnding3() {
        if (spareKhaimon) {
            if (hasArtifact) {
                return "Lost Friend Ending - Khaimon is spared but not convinced due to artifact.\n"
                        + "After a fierce battle, the party weakens Khaimon but can't fully save him. \n"
                        + "Despite their appeals, the corrupted artifact holds his mind. \n"
                        + "They spare him, but his eyes remain filled with distrust and malice.\n" 
                        + "\n" 
                        + "Khaimon retreats into the shadows, clutching the artifact. \n"
                        + "The party leaves with a sense of loss, knowing Khaimon is still out there, burdened by darkness. \n"
                        + "The World Tree is safe for now, but peace remains fragile, with the threat of Khaimon's return lingering.\n";
            } else {
                return "Good Ending - Khaimon is defeated, spared, and convinced to stop.\n"
                        + "The party shatters the dark artifact, clearing Khaimon’s mind. \n"
                        + "Memories of his true purpose return, and he vows to undo the damage. \n"
                        + "Together, they purify the World Tree. Khaimon, now scarred, rededicates himself to its protection.\n"
                        + " The forest flourishes, and the party leaves in celebration, having restored balance and regained an old friend.\n";
            }
        } else {
            return "Goodbye Friend Ending - Khaimon is defeated but not spared.\n"
                    + "In the final battle, the party struggles against Khaimon’s dark magic. \n"
                    + "Despite their efforts, it's clear the darkness has consumed him. \n"
                    + "With heavy hearts, they realize Khaimon is beyond redemption. \n"
                    + "Jascha delivers the final blow, ending Khaimon’s life and dark influence over the forest.\n" 
                    + "\n" 
                    + "As Khaimon fades, a glimmer of his old self appears, giving the party a faint smile of gratitude. \n"
                    + "The forest brightens, free from his darkness, but the victory feels hollow. \n"
                    + "The party leaves in silence, forever changed by the sacrifice. \n"
                    + "The forest is safe, but they’ve lost a friend and mentor.\n";
        }
    }

    // Testing the endings
    /*public static void main(String[] args) {
        GameEndings ending1b = new GameEndings();
        ending1b.setHasArtifact(true);
        ending1b.setUsedArtifact(true);
        ending1b.setDefeatedKhaimon(true);
        System.out.println(ending1b.determineEnding());

        GameEndings ending2a = new GameEndings();
        ending2a.setSacrifice(true);
        System.out.println(ending2a.determineEnding());

        GameEndings ending3b1 = new GameEndings();
        ending3b1.setDefeatedKhaimon(true);
        ending3b1.setSpareKhaimon(true);
        System.out.println(ending3b1.determineEnding());
    }*/
    
}
