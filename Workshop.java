
import java.util.LinkedList;
import java.util.Queue;

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

    public void addOrder(String childName, String giftName) {
        GiftOrder order = new GiftOrder(nextOrderId, childName, giftName);
        giftOrders.add(order);
        nextOrderId++;

        System.out.println("New Christmas wish received!");
    }

    public void viewOrders() {
        if (giftOrders.isEmpty()) {
            System.out.println("No gift orders available.");
            return;
        }

        System.out.println("\n===== GIFT ORDERS =====");

        for (GiftOrder order : giftOrders) {
            order.displayOrder();
            System.out.println("-----------------------");
        }
    }

    // BUDDY ASSEMBLES THE NEXT GIFT
    public void assembleNextGift() {
        if (giftOrders.isEmpty()) {
            System.out.println("No gifts to assemble!");
            return;
        }

        GiftOrder order = giftOrders.peek();

        if (order.isAssembled()) {
            System.out.println("This gift is already assembled!");
            return;
        }

        if (!inventory.hasStock(order.getGiftName())) {
            System.out.println("Not enough stock for: " + order.getGiftName());
            return;
        }

        inventory.useStock(order.getGiftName());
        order.assembleGift();

        elf.performTask();

        System.out.println("Gift assembled successfully!");
        System.out.println("Gift: " + order.getGiftName());
        System.out.println("For: " + order.getChildName());
    }

    // SANTA DELIVERS THE ASSEMBLED GIFT
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
        System.out.println("Child: " + order.getChildName());
        System.out.println("+10 points!");
        System.out.println("Current Score: " + score);
    }

    public void processOrder() {
        assembleNextGift();
        deliverNextGift();
    }

    public void viewInventory() {
        inventory.displayInventory();
    }

    public void findOrder(int orderId) {
        for (GiftOrder order : giftOrders) {
            if (order.getOrderId() == orderId) {
                System.out.println("\nGift order found!");
                order.displayOrder();
                return;
            }
        }

        System.out.println("Order not found in pending orders.");
    }

    public int getScore() {
        return score;
    }

    public int getPendingOrderCount() {
        return giftOrders.size();
    }

    public void showWorkers() {
        WorkshopWorker[] workers = { santa, elf };

        System.out.println("\n===== WORKSHOP WORKERS =====");

        for (WorkshopWorker worker : workers) {
            worker.performTask();
        }
    }
}
