import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class Workshop {

    private Queue<GiftOrder> giftOrders;
    private Inventory inventory;
    private int nextOrderId;
    private int score;

    private Elf elf;
    private Santa santa;

    public Workshop() {
        giftOrders = new LinkedList<>();
        inventory = new Inventory();
        nextOrderId = 1;
        score = 0;

        elf = new Elf("Buddy");
        santa = new Santa("Santa");
    }

    // GENERATE FIVE RANDOM CHRISTMAS WISHES
    public void generateRandomOrders() {

        giftOrders.clear();
        inventory = new Inventory();

        nextOrderId = 1;
        score = 0;

        ArrayList<String> children = new ArrayList<>();

Collections.addAll(children,
    "Irish", "Von", "Glends", "Joey",
    "Jerwin", "Zyron", "Yushi", "Joshua"
);

Collections.shuffle(children);

        String[] gifts = {
    "Cellphone",
    "Toy Car",
    "1kyaw",
    "Barbie Doll"
};

        Random random = new Random();

        System.out.println("\n======================================");
        System.out.println("     GENERATING CHRISTMAS WISHES");
        System.out.println("======================================");

        for (int i = 0; i < 5; i++) {

            String childName = children.get(i);
            String giftName = gifts[random.nextInt(gifts.length)];

            GiftOrder order = new GiftOrder(
                nextOrderId, childName, giftName
            );

            giftOrders.add(order);
            nextOrderId++;

            System.out.println("Christmas wish generated!");
        }

        System.out.println("\nFive gift orders are ready!");
    }

    // DISPLAY ORDER STATUS WITHOUT REVEALING ANSWERS
    public void viewOrderStatus() {

        if (giftOrders.isEmpty()) {
            System.out.println("\nNo pending gift orders.");
            return;
        }

        System.out.println("\n===== GIFT ORDER STATUS =====");

        for (GiftOrder order : giftOrders) {

            String status;

            if (order.isAssembled()) {
                status = "Assembled - Waiting for Santa";
            } else {
                status = "Pending";
            }

            System.out.println(
                "Order #" + order.getOrderId() + " - " + status
            );
        }
    }

    // BUDDY ASSEMBLES THE NEXT UNASSEMBLED GIFT
    public void assembleNextGift(Scanner scanner) {

        if (giftOrders.isEmpty()) {
            System.out.println("No gifts to assemble!");
            return;
        }

        GiftOrder orderToAssemble = null;

        // Find the first unassembled gift
        for (GiftOrder order : giftOrders) {
            if (!order.isAssembled()) {
                orderToAssemble = order;
                break;
            }
        }

        if (orderToAssemble == null) {
            System.out.println("All remaining gifts are already assembled!");
            return;
        }

        System.out.println("\n===== GIFT MEMORY CHALLENGE =====");

        System.out.print("Enter the child's name: ");
        String enteredChild = scanner.nextLine().trim();

        System.out.print("Enter the assigned gift: ");
        String enteredGift = scanner.nextLine().trim();

        boolean childCorrect = enteredChild.equalsIgnoreCase(
            orderToAssemble.getChildName()
        );

        boolean giftCorrect = enteredGift.equalsIgnoreCase(
            orderToAssemble.getGiftName()
        );

        if (!childCorrect || !giftCorrect) {

            System.out.println("\nINCORRECT ANSWER!");
            System.out.println("Buddy could not remember this gift order.");

            giftOrders.remove(orderToAssemble);
            score -= 5;

            System.out.println("Order skipped and removed from the queue.");
            System.out.println("-5 points penalty!");
            System.out.println("Current Score: " + score);

            return;
        }

        if (!inventory.hasStock(orderToAssemble.getGiftName())) {

            System.out.println("\nThis gift is out of stock!");

            giftOrders.remove(orderToAssemble);
            score -= 5;

            System.out.println("Order skipped and removed from the queue.");
            System.out.println("-5 points penalty!");
            System.out.println("Current Score: " + score);

            return;
        }

        inventory.useStock(orderToAssemble.getGiftName());
        orderToAssemble.assembleGift();

        elf.performTask();

        score += 5;

        System.out.println("\nCORRECT! Buddy assembled the gift!");
        System.out.println("+5 assembly points!");
        System.out.println("Current Score: " + score);
    }

    // SANTA DELIVERS THE FIRST ORDER IF ASSEMBLED
    public void deliverNextGift() {

        if (giftOrders.isEmpty()) {
            System.out.println("No gifts waiting for delivery!");
            return;
        }

        GiftOrder order = giftOrders.peek();

        if (!order.isAssembled()) {
            System.out.println("Buddy needs to assemble this gift first!");
            return;
        }

        giftOrders.poll();
        order.completeOrder();

        santa.performTask();

        score += 10;

        System.out.println("Gift delivered successfully!");
        System.out.println("+10 delivery points!");
        System.out.println("Current Score: " + score);
    }

    // DISPLAY INVENTORY
    public void viewInventory() {
        inventory.displayInventory();
    }

    // LINEAR SEARCH FOR AN ORDER
    public void findOrder(int orderId) {

        for (GiftOrder order : giftOrders) {

            if (order.getOrderId() == orderId) {

                String status;

                if (order.isAssembled()) {
                    status = "Assembled - Waiting for Santa";
                } else {
                    status = "Pending";
                }

                System.out.println("\nGift order found!");
                System.out.println("Order ID: " + order.getOrderId());
                System.out.println("Status: " + status);

                return;
            }
        }

        System.out.println("Order not found in pending orders.");
    }

    // SKIP THE FIRST ORDER IN THE QUEUE
    public void skipNextOrder() {

        if (giftOrders.isEmpty()) {
            System.out.println("There are no orders to skip!");
            return;
        }

        GiftOrder skippedOrder = giftOrders.poll();

        score -= 5;

        System.out.println("\n===== ORDER SKIPPED =====");
        System.out.println("Order ID: " + skippedOrder.getOrderId());
        System.out.println("The order has been removed from the queue.");
        System.out.println("-5 points penalty!");
        System.out.println("Current Score: " + score);
    }

    // ADD COMPLETION BONUS
    public void addBonusPoints(int points) {
        score += points;
    }

    public int getScore() {
        return score;
    }

    public int getPendingOrderCount() {
        return giftOrders.size();
    }

    // DISPLAY WORKSHOP WORKERS
    public void showWorkers() {

        WorkshopWorker[] workers = { santa, elf };

        System.out.println("\n===== WORKSHOP WORKERS =====");

        for (WorkshopWorker worker : workers) {
            worker.performTask();
        }
    }

    // RESET WORKSHOP FOR THE NEXT PLAYER
    public void resetForNextPlayer() {

        giftOrders.clear();
        inventory = new Inventory();

        nextOrderId = 1;
        score = 0;

        System.out.println("\nWorkshop has been reset!");
        System.out.println("Ready for the next player!");
    }

    // DISPLAY THE FIVE RANDOM ASSIGNMENTS
    public void showMemorizationList() {

        System.out.println("\n======================================");
        System.out.println("       🎄 MEMORIZE THE WISHES! 🎄");
        System.out.println("======================================");
        System.out.println("Remember each child's name and gift!");
        System.out.println();

        for (GiftOrder order : giftOrders) {

            System.out.println(
                order.getChildName() + "  -->  " + order.getGiftName()
            );
        }

        System.out.println("\n======================================");
    }
}
