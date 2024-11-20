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

    public boolean getHasArtifact() {
        return hasArtifact;
    }

    public boolean getUsedArtifact() {
        return usedArtifact;
    }

    public boolean getDefeatedKhaimon() {
        return defeatedKhaimon;
    }

    public boolean getSacrifice() {
        return sacrifice;
    }

    public boolean getSpareKhaimon() {
        return spareKhaimon;
    }

    // Method to determine the ending
public String determineEnding() {
        if (hasArtifact) {
            if (usedArtifact) {
                // If the artifact is used, Jascha is automatically corrupted
                return "\nBittersweet Ending - Jascha is Corrupted\n"
                        + "\nIn the climactic battle, the party defeats Khaimon, but Jascha is overtaken by corrupted energy.\n"
                        + "\nConsumed by darkness, Jascha is sealed in the artifact.\n"
                        + "\nThe party leaves with the World Tree restored, but Jascha's sacrifice is a bittersweet memory of their victory.\n";
            } else {
                // If the artifact is not used, handle based on Khaimon's fate
                if (!defeatedKhaimon) {
                    return "\nBad Ending - The hero fails to defeat or stop Khaimon.\n"
                            + "\nDespite their courage, the party is overwhelmed by Khaimon's dark magic and falls one by one.\n"
                            + "\nWith no one left to stop him, Khaimon corrupts the World Tree, solidifying his rule.\n"
                            + "\nThe land becomes a shadowy wasteland, with corrupted creatures prowling the woods\n"
                            + "and the twisted World Tree spreading darkness.\n"
                            + "\nThe hero and their party are remembered and mourned as the last hope that fell to darkness.\n";
                } else {
                    return evaluateEnding3();
                }
            }
        } else {
            if (defeatedKhaimon) {
                return evaluateEnding3();
            } else {
                if (sacrifice) {
                    return "\nSacrifice Ending - Jascha sacrifices themselves to try to stop Khaimon.\n"
                            + "\nAs the battle peaks, Khaimon's corruption overwhelms. The hero Jascha decides to make the ultimate sacrifice.\n"
                            + "\nWhile the party fights shadows, Jascha channels their life force to banish the darkness.\n"
                            + "\nWith a final strike, they seal Khaimon and the corruption within themselves.\n"
                            + "\nThe darkness vanishes, leaving peace.\n"
                            + "\nThe forest flourishes, the World Tree renews, and Khaimon's reign ends.\n"
                            + "\nThe party returns, mourning their hero whose light now eternally brightens the forest.";
                } else {
                    return "\nBad Ending - The hero fails to defeat or stop Khaimon.\n"
                            + "\nDespite their courage, the party is overwhelmed by Khaimon's dark magic and falls one by one.\n"
                            + "\nWith no one left to stop him, Khaimon corrupts the World Tree, solidifying his rule.\n"
                            + "\nThe land becomes a shadowy wasteland, with corrupted creatures prowling the woods\n"
                            + "and the twisted World Tree spreading darkness.\n"
                            + "\nThe hero and their party are remembered and mourned as the last hope that fell to darkness.\n";
                }
            }
        }
    }

    private String evaluateEnding3() {
        // Handling Khaimon's fate based on whether he is spared or not
        if (spareKhaimon) {
            if (hasArtifact) {
                return "\nLost Friend Ending - Khaimon is spared but not convinced due to artifact.\n"
                        + "\nAfter a fierce battle, the party weakens Khaimon but can't fully save him.\n"
                        + "\nDespite their appeals, the corrupted artifact holds his mind.\n"
                        + "\nThey spare him, but his eyes remain filled with distrust and malice.\n"
                        + "\nKhaimon retreats into the shadows, clutching the artifact.\n"
                        + "\nThe party leaves with a sense of loss, knowing Khaimon is still out there, burdened by darkness.\n"
                        + "\nThe World Tree is safe for now, but peace remains fragile, with the threat of Khaimon's return lingering.\n";
            } else {
                return "\nGood Ending - Khaimon is defeated, spared, and convinced to stop.\n"
                        + "\nThe party shatters the dark artifact, clearing Khaimon's mind.\n"
                        + "\nMemories of his true purpose return, and he vows to undo the damage.\n"
                        + "\nTogether, they purify the World Tree. Khaimon, now scarred, rededicates himself to its protection.\n"
                        + "\nThe forest flourishes, and the party leaves in celebration, having restored balance and regained an old friend.\n";
            }
        } else {
            return "\nGoodbye Friend Ending - Khaimon is defeated but not spared.\n"
                    + "\nIn the final battle, the party struggles against Khaimon's dark magic.\n"
                    + "\nDespite their efforts, it's clear the darkness has consumed him.\n"
                    + "\nWith heavy hearts, they realize Khaimon is beyond redemption.\n"
                    + "\nJascha delivers the final blow, ending Khaimon's life and dark influence over the forest.\n"
                    + "\nThe forest brightens, free from his darkness, but the victory feels hollow.\n"
                    + "\nThe party leaves in silence, forever changed by the sacrifice.\n"
                    + "\nThe forest is safe, but they've lost a friend and mentor.\n";
        }
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
    
