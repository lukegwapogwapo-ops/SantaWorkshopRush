
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Workshop workshop = new Workshop();

        ArrayList<PlayerScore> playerScores = new ArrayList<>();

        int choice = 0;

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
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    int gameChoice = 0;

                    while (gameChoice != 9) {

                        System.out.println("\n===== WORKSHOP MENU =====");
                        System.out.println("1. Add Children's Wish");
                        System.out.println("2. View Gift Orders");
                        System.out.println("3. Buddy Assembles Next Gift");
                        System.out.println("4. Santa Delivers Next Gift");
                        System.out.println("5. View Inventory");
                        System.out.println("6. Find Gift Order");
                        System.out.println("7. View Workshop Workers");
                        System.out.println("8. Start Delivery Challenge");
                        System.out.println("9. Back to Main Menu");

                        System.out.print("\nChoose an action: ");
                        gameChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (gameChoice) {

                            case 1:
                                System.out.print("Enter child's name: ");
                                String childName = scanner.nextLine();

                                System.out.print("Enter requested gift: ");
                                String giftName = scanner.nextLine();

                                workshop.addOrder(childName, giftName);
                                break;

                            case 2:
                                workshop.viewOrders();
                                break;

                            case 3:
                                workshop.assembleNextGift();
                                break;

                            case 4:
                                workshop.deliverNextGift();
                                break;

                            case 5:
                                workshop.viewInventory();
                                break;

                            case 6:
                                System.out.print("Enter Order ID to search: ");
                                int searchId = scanner.nextInt();
                                scanner.nextLine();

                                workshop.findOrder(searchId);
                                break;

                            case 7:
                                workshop.showWorkers();
                                break;

                            case 8:

                                if (workshop.getPendingOrderCount() == 0) {
                                    System.out.println("\nNo pending orders!");
                                    System.out.println("Add some gift orders first.");
                                    break;
                                }

                                int startingScore = workshop.getScore();
                                int turnsLeft = 5;

                                System.out.println("\n======================================");
                                System.out.println("       CHRISTMAS DELIVERY CHALLENGE");
                                System.out.println("======================================");
                                System.out.println("You have 5 turns to deliver gifts!");
                                System.out.println("Each delivered gift earns 10 points.");

                                while (turnsLeft > 0 &&
                                       workshop.getPendingOrderCount() > 0) {

                                    System.out.println("\nTurns remaining: " + turnsLeft);

                                    workshop.assembleNextGift();

                                    workshop.deliverNextGift();

                                    turnsLeft--;
                                }

                                int challengeScore =
                                    workshop.getScore() - startingScore;

                                System.out.println("\n======================================");
                                System.out.println("       CHRISTMAS CHALLENGE ENDED");
                                System.out.println("======================================");
                                System.out.println("Points earned: " + challengeScore);
                                System.out.println("Total score: " + workshop.getScore());

                                if (workshop.getPendingOrderCount() == 0) {
                                    System.out.println("\nCONGRATULATIONS!");
                                    System.out.println("All gifts were delivered!");
                                    System.out.println("Santa saved Christmas!");
                                } else {
                                    System.out.println("\nTIME'S UP!");
                                    System.out.println("Some gifts are still waiting.");
                                }

                                System.out.print("\nEnter player name for leaderboard: ");
                                String playerName = scanner.nextLine();

                                playerScores.add(
                                    new PlayerScore(playerName, challengeScore)
                                );

                                System.out.println("Score saved to leaderboard!");
                                break;

                            case 9:
                                System.out.println("Returning to main menu...");
                                break;

                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;

                case 2:
                    displayLeaderboard(playerScores);
                    break;

                case 3:
                    System.out.println("\n===== HOW TO PLAY =====");
                    System.out.println("1. Add children's gift wishes.");
                    System.out.println("2. Buddy assembles the next gift.");
                    System.out.println("3. Santa delivers the assembled gift.");
                    System.out.println("4. Check the workshop inventory.");
                    System.out.println("5. Search for an order using its ID.");
                    System.out.println("6. View Santa and Buddy's roles.");
                    System.out.println("7. Complete the 5-turn delivery challenge.");
                    System.out.println("Each delivered gift earns 10 points.");
                    System.out.println("Orders are processed in FIFO order!");
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

    public static void displayLeaderboard(ArrayList<PlayerScore> scores) {

        if (scores.isEmpty()) {
            System.out.println("\n===== CHRISTMAS LEADERBOARD =====");
            System.out.println("No scores available yet.");
            return;
        }

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
}
