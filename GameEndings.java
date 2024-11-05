/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author hi
 */
public class GameEndings {
   
    // Nested static class for Game Endings

        // Flags for conditions
        private boolean hasArtifact = false;
        private boolean usedArtifact = false;
        private boolean defeatedKhaimon = false;
        private boolean sacrifice = false;
        private boolean spareKhaimon = false;

        // Constructor to initialize choices made during the game
        
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

        
        public String determineEnding() {
            if (hasArtifact) {
                if (usedArtifact) {
                    return "Ending 1.b: Bad Ending";
                } else {
                    return evaluateEnding3();
                }
            } else { 
                if (defeatedKhaimon) {
                    return evaluateEnding3();
                } else {
                    return evaluateEnding2();
                }
            }
        }

        private String evaluateEnding2() {
            if (sacrifice) {
                return "Ending 2.a: Sacrifice Ending";
            } else {
                return "Ending 1.b: Bad Ending";
            }
        }

        private String evaluateEnding3() {
            if (defeatedKhaimon) {
                if (spareKhaimon) {
                    return "Ending 3.b.1: Good Ending";
                } else {
                    return "Ending 3.a.2: Goodbye Friend Ending";
                }
            } else {
                return "Ending 3.a.1: Lost Friend Ending";
            }
        }

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



