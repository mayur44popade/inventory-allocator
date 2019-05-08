package inventory.allocator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryAllocator {

    public List<Shipment> createShipments(Map<String, Integer> order, List<Warehouse> warehouseDetails){
        List<Shipment> finalShipment = new ArrayList();
        if(null == order || null == warehouseDetails || warehouseDetails.size() == 0 || order.size() == 0){
            return finalShipment;
        }

        for(Warehouse warehouse : warehouseDetails){
            String warehouseName = warehouse.getName();
            Map<String, Integer> inventory = warehouse.getInventory();
            Shipment shipment = new Shipment();
            shipment.setWarehouseName(warehouseName);
            Map<String, Integer> shipmentDetails = new HashMap();

            for(String itemName: inventory.keySet()){
                if(order.containsKey(itemName)){
                    if(order.get(itemName) == inventory.get(itemName)){
                        shipmentDetails.put(itemName, order.get(itemName));
                    }
                }
                shipment.setShipmentDetails(shipmentDetails);
                if(shipment.shipmentDetails.size() != 0){
                    finalShipment.add(shipment);
                }
            }
        }

        return finalShipment;
    }

}
