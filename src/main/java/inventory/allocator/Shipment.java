package inventory.allocator;

import java.util.HashMap;
import java.util.Map;

public class Shipment {

    String warehouseName;
    Map<String, Integer> shipmentDetails = new HashMap<>();

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Map<String, Integer> getShipmentDetails() {
        return shipmentDetails;
    }

    public void setShipmentDetails(Map<String, Integer> shipmentDetails) {
        this.shipmentDetails = shipmentDetails;
    }

    @Override
    public String toString() {
        return "{" +
                "" + warehouseName +
                ": " + shipmentDetails +
                '}';
    }
}
