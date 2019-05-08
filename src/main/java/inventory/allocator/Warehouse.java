package inventory.allocator;

import java.util.Map;

public class Warehouse {

    String name;
    Map<String, Integer> inventory;

    public Warehouse(String name, Map<String, Integer> inventory) {
        this.name = name;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }
}
