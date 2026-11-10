
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Workshop workshop = new Workshop();

        ArrayList<PlayerScore> playerScores = new ArrayList<>();

        int choice = 0;
        int roundScore = 0;

        while (choice != 4) {

            System.out.println("\n");
            System.out.println("       *        .        *");
            System.out.println("              /\\");
            System.out.println("             /  \\");
            System.out.println("            /____\\");
            System.out.println("           /      \\");
            System.out.println("          /________\\");
            System.out.println("              ||");
            System.out.println("              ||");

            System.out.println("\n======================================");
            System.out.println("       SANTA'S WORKSHOP RUSH");
            System.out.println("          CHRISTMAS EDITION");
            System.out.println("======================================");
            System.out.println("       HO HO HO! WELCOME!");
            System.out.println("   Help Santa deliver Christmas gifts!");
            System.out.println("======================================");
            System.out.println("1. Start Game");
            System.out.println("2. Leaderboard");
            System.out.println("3. How to Play");
            System.out.println("4. Exit");

            System.out.print("\nEnter your choice: ");
            try {
    choice = Integer.parseInt(scanner.nextLine());
} catch (NumberFormatException e) {
    System.out.println("Invalid input! Please enter a number from 1 to 8.");
    continue;
}

            switch (choice) {

               case 1:

    workshop.generateRandomOrders();

    workshop.showMemorizationList();

    // 15-SECOND MEMORIZATION COUNTDOWN
    for (int seconds = 15; seconds >= 1; seconds--) {

        System.out.println("Time remaining: " + seconds + " seconds");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            break;
        }
    }

    // CLEAR THE SCREEN AFTER MEMORIZATION
    System.out.print("\033[H\033[2J");
    System.out.flush();

    System.out.println("MEMORIZATION TIME IS OVER!");
    System.out.println("Good luck remembering the Christmas wishes!");

    int gameChoice = 0;

                    while (gameChoice != 8) {

                        System.out.println("\n===== WORKSHOP MENU =====");
                        System.out.println("1. View Gift Order Status");
                        System.out.println("2. Buddy Assembles Next Gift");
                        System.out.println("3. Santa Delivers Ready Gifts");
                        System.out.println("4. View Inventory");
                        System.out.println("5. Find Gift Order");
                        System.out.println("6. View Workshop Workers");
                        System.out.println("7. Skip Next Gift Order");
                        System.out.println("8. Back to Main Menu");

                        System.out.print("\nChoose an action: ");
                        try {
    gameChoice = Integer.parseInt(scanner.nextLine());
} catch (NumberFormatException e) {
    System.out.println("Invalid input! Please enter a number from 1 to 8.");
    continue;
}

                        switch (gameChoice) {

                            case 1:

                                workshop.viewOrderStatus();
                                break;

                            case 2:

                                int oldScore = workshop.getScore();

                                workshop.assembleNextGift(scanner);

                                int assemblyPoints =
                                    workshop.getScore() - oldScore;

                                roundScore += assemblyPoints;
                                break;

                            case 3:

                                if (workshop.getPendingOrderCount() == 0) {
                                    System.out.println("\nNo pending gifts!");
                                    break;
                                }

                                int deliveredCount = 0;
                                int maxDeliveries = 5;
                                int previousScore = workshop.getScore();

                                System.out.println("\n======================================");
                                System.out.println("       SANTA'S DELIVERY RUN");
                                System.out.println("======================================");
                                System.out.println("Santa will deliver up to 5 ready gifts!");

                                while (deliveredCount < maxDeliveries &&
                                       workshop.getPendingOrderCount() > 0) {

                                    int scoreBeforeDelivery =
                                        workshop.getScore();

                                    workshop.deliverNextGift();

                                    if (workshop.getScore() >
                                        scoreBeforeDelivery) {

                                        deliveredCount++;

                                        showSleighAnimation();

                                    } else {

                                        System.out.println(
                                            "\nNo more assembled gifts ready for delivery!"
                                        );

                                        break;
                                    }
                                }

                                int deliveryPoints =
                                    workshop.getScore() - previousScore;

                                roundScore += deliveryPoints;

                                // BONUS FOR DELIVERING ALL FIVE GIFTS
                                if (deliveredCount == 5) {

                                    workshop.addBonusPoints(25);
                                    roundScore += 25;

                                    System.out.println("\nFIVE GIFTS DELIVERED!");
                                    System.out.println("+25 COMPLETION BONUS!");
                                }

                                System.out.println("\n======================================");
                                System.out.println("       DELIVERY RUN FINISHED");
                                System.out.println("======================================");
                                System.out.println("Gifts delivered: " + deliveredCount);
                                System.out.println("Round score: " + roundScore);
                                System.out.println(
                                    "Total score: " + workshop.getScore()
                                );

                                if (deliveredCount > 0) {

                                    System.out.print(
                                        "\nEnter player name for leaderboard: "
                                    );

                                    String playerName = scanner.nextLine();

                                    playerScores.add(
    new PlayerScore(playerName, roundScore)
);

System.out.println(
    "Score saved to leaderboard!"
);

// Reset workshop for the next player
workshop.resetForNextPlayer();

roundScore = 0;

                                } else {

                                    System.out.println(
                                        "No gifts delivered. No leaderboard entry added."
                                    );
                                }

                                break;

                            case 4:

                                workshop.viewInventory();
                                break;

                            case 5:

                                System.out.print("Enter Order ID to search: ");
                                int searchId = scanner.nextInt();
                                scanner.nextLine();

                                workshop.findOrder(searchId);
                                break;

                            case 6:

                                workshop.showWorkers();
                                break;

                            case 7:

                                workshop.skipNextOrder();
                                break;

                            case 8:

                                System.out.println("Returning to main menu...");
                                break;

                            default:

                                System.out.println(
                                    "Invalid choice. Please try again."
                                );
                        }
                    }
                    break;

                case 2:

                    displayLeaderboard(playerScores);
                    break;

                case 3:

                    System.out.println("\n===== HOW TO PLAY =====");
System.out.println("1. Memorize the five children's names and their assigned gifts.");
System.out.println("2. Check the order IDs and gift statuses.");
System.out.println("3. Help Buddy remember and assemble the correct gift.");
System.out.println("4. Help Santa deliver assembled gifts in FIFO order.");
System.out.println("5. Check the remaining workshop inventory.");
System.out.println("6. Search for a pending gift order using its ID.");
System.out.println("7. View Santa and Buddy's workshop roles.");
System.out.println("8. Skip an order if you cannot complete it.");
System.out.println("9. Return to the main menu.");
System.out.println("\nCorrect assembly earns 5 points.");
System.out.println("Successful delivery earns 10 points.");
System.out.println("Incorrect answers and skipped orders deduct 5 points.");
System.out.println("Complete all five deliveries to earn a 25-point bonus!");
                    break;

                case 4:

                    System.out.println("\n======================================");
                    System.out.println("       THANK YOU FOR PLAYING!");
                    System.out.println("          MERRY CHRISTMAS!");
                    System.out.println("======================================");
                    System.out.println("          HO HO HO! BYE!");
                    break;

                default:

                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // DISPLAY AND SORT LEADERBOARD
    public static void displayLeaderboard(ArrayList<PlayerScore> scores) {

        if (scores.isEmpty()) {
            System.out.println("\n===== CHRISTMAS LEADERBOARD =====");
            System.out.println("No scores available yet.");
            return;
        }

        // BUBBLE SORT: Highest score first
        for (int i = 0; i < scores.size() - 1; i++) {

            for (int j = 0; j < scores.size() - 1 - i; j++) {

                if (scores.get(j).getScore() <
                    scores.get(j + 1).getScore()) {

                    PlayerScore temp = scores.get(j);
                    scores.set(j, scores.get(j + 1));
                    scores.set(j + 1, temp);
                }
            }
        }

        System.out.println("\n======================================");
        System.out.println("          CHRISTMAS LEADERBOARD");
        System.out.println("======================================");

        for (int i = 0; i < scores.size(); i++) {

            PlayerScore player = scores.get(i);

            System.out.println(
                (i + 1) + ". " +
                player.getPlayerName() +
                " - " + player.getScore() + " points"
            );
        }

        System.out.println("======================================");
    }

    // SANTA'S MOVING SLEIGH ANIMATION
    public static void showSleighAnimation() {

        String[] runningFrame = {
            "   \\|/          \\|/          \\|/",
            "  (o.o)--------(o.o)--------(o.o)",
            "   /|\\           /|\\           /|\\",
            "  / | \\         / | \\         / | \\",
            "   / \\           / \\           / \\",
            "",
            "                              __________",
            "                             /  SANTA  /|",
            "                            /________/ |",
            "                            |   o/   |/",
            "                            O--------O"
        };

        String[] runningFrameTwo = {
            "   \\|/          \\|/          \\|/",
            "  (o.o)--------(o.o)--------(o.o)",
            "   /|\\           /|\\           /|\\",
            "  / | \\         / | \\         / | \\",
            "   \\ /           \\ /           \\ /",
            "",
            "                              __________",
            "                             /  SANTA  /|",
            "                            /________/ |",
            "                            |   o_   |/",
            "                            O--------O"
        };

        for (int position = 0; position < 25; position++) {

            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("       *       .        *       .       *");
            System.out.println("   .       *        .       *        .");
            System.out.println();
            System.out.println("          SANTA IS DELIVERING GIFTS!");
            System.out.println();

            String spaces = " ".repeat(position);

            String[] currentFrame;

            if (position % 2 == 0) {
                currentFrame = runningFrame;
            } else {
                currentFrame = runningFrameTwo;
            }

            for (String line : currentFrame) {
                System.out.println(spaces + line);
            }

            System.out.println();
            System.out.println("          MERRY CHRISTMAS!");

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println("\n          SANTA HAS DELIVERED THE GIFT!");
    }
}
