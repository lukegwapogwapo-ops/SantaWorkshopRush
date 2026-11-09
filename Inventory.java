
import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private HashMap<String, Integer> stock;

    public Inventory() {
        stock = new HashMap<>();

        stock.put("Basketball", 5);
        stock.put("Toy Car", 5);
        stock.put("Teddy Bear", 5);
        stock.put("Barbie Doll", 5);
    }

    public boolean hasStock(String giftName) {
        return stock.getOrDefault(giftName, 0) > 0;
    }

    public void useStock(String giftName) {
        if (hasStock(giftName)) {
            stock.put(giftName, stock.get(giftName) - 1);
        }
    }

    public void displayInventory() {
        System.out.println("\n===== WORKSHOP INVENTORY =====");

        for (Map.Entry<String, Integer> item : stock.entrySet()) {
            System.out.println(item.getKey() + ": " + item.getValue());
        }
    }
}
