package inventory.allocator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InventoryAllocator {

    public List<Shipment> createShipments(Map<String, Integer> order, List<Warehouse> warehouseDetails){
        List<Shipment> finalShipment = new ArrayList();
        if(null == order || null == warehouseDetails || warehouseDetails.size() == 0 || order.size() == 0){
            return finalShipment;
        }

        return finalShipment;
    }

}
