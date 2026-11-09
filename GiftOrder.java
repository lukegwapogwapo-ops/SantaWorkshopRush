public class GiftOrder {

    private int orderId;
    private String childName;
    private String giftName;
    private boolean assembled;
    private boolean completed;

    public GiftOrder(int orderId, String childName, String giftName) {
        this.orderId = orderId;
        this.childName = childName;
        this.giftName = giftName;
        this.assembled = false;
        this.completed = false;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getChildName() {
        return childName;
    }

    public String getGiftName() {
        return giftName;
    }

    public boolean isAssembled() {
        return assembled;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void assembleGift() {
        assembled = true;
    }

    public void completeOrder() {
        completed = true;
    }

    public void displayOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Child: " + childName);
        System.out.println("Gift: " + giftName);
        System.out.println("Assembly: " + (assembled ? "Ready" : "Not assembled"));
        System.out.println("Status: " + (completed ? "Delivered" : "Pending"));
    }
}