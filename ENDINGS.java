package game;

import java.util.Scanner;

public class ENDINGS {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameEndings gameEndings = new GameEndings();

        System.out.println("Welcome to the game! Let's determine your ending.");

        // Step 1: Did the player obtain the artifact?
        boolean hasArtifact = getYesOrNoInput(scanner, "Did you take the artifact? (yes/no): ");
        gameEndings.setHasArtifact(hasArtifact);

        if (hasArtifact) {
            // Step 2: Did the player use the artifact?
            boolean usedArtifact = getYesOrNoInput(scanner, "Did you use the artifact? (yes/no): ");
            gameEndings.setUsedArtifact(usedArtifact);

            if (usedArtifact) {
                // Automatically set defeatedKhaimon to true if the artifact is used
                gameEndings.setDefeatedKhaimon(true);
                System.out.println("\n" + gameEndings.determineEnding());
            } else {
                // If the artifact is not used, ask if the player defeated Khaimon
                boolean defeatedKhaimon = getYesOrNoInput(scanner, "Did you defeat Khaimon? (yes/no): ");
                gameEndings.setDefeatedKhaimon(defeatedKhaimon);

                if (defeatedKhaimon) {
                    // Ask if the player spared Khaimon
                    boolean spareKhaimon = getYesOrNoInput(scanner, "Did you spare Khaimon? (yes/no): ");
                    gameEndings.setSpareKhaimon(spareKhaimon);
                    System.out.println("\n" + gameEndings.determineEnding());
                } else {
                    // Directly handle bad ending if not defeated and no artifact usage
                    System.out.println("\n" + gameEndings.determineEnding());
                }
            }
        } else {
            // If no artifact was taken, follow the no-artifact paths
            boolean defeatedKhaimon = getYesOrNoInput(scanner, "Did you defeat Khaimon? (yes/no): ");
            gameEndings.setDefeatedKhaimon(defeatedKhaimon);

            if (defeatedKhaimon) {
                boolean spareKhaimon = getYesOrNoInput(scanner, "Did you spare Khaimon? (yes/no): ");
                gameEndings.setSpareKhaimon(spareKhaimon);
                System.out.println("\n" + gameEndings.determineEnding());
            } else {
                // If Khaimon is not defeated, ask for a sacrifice
                boolean makeSacrifice = getYesOrNoInput(scanner, "Do you wish to sacrifice yourself to stop Khaimon? (yes/no): ");
                gameEndings.setSacrifice(makeSacrifice);
                System.out.println("\n" + gameEndings.determineEnding());
            }
        }

        scanner.close();
    }

    /**
     * Helper method to repeatedly ask for a valid "yes" or "no" input.
     */
    private static boolean getYesOrNoInput(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("yes")) {
                    return true;
                } else if (input.equals("no")) {
                    return false;
                } else {
                    throw new IllegalArgumentException("Invalid input. Please enter 'yes' or 'no'.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
