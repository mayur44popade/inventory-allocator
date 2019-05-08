package inventory.allocator;

import java.util.*;

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
                        order.remove(itemName);
                        inventory.put(itemName, 0);
                    }else if(order.get(itemName) < inventory.get(itemName)){
                        shipmentDetails.put(itemName, order.get(itemName));
                        inventory.put(itemName, inventory.get(itemName)-order.get(itemName));
                        order.remove(itemName);
                    }else{
                        if(inventory.get(itemName) != 0){
                            shipmentDetails.put(itemName, inventory.get(itemName));
                            order.put(itemName, order.get(itemName)-inventory.get(itemName));
                            inventory.put(itemName, 0);
                        }
                    }
                }
                shipment.setShipmentDetails(shipmentDetails);

                if(order.size() == 0){
                    break;
                }
            }
            if(shipment.shipmentDetails.size() != 0){
                finalShipment.add(shipment);
            }
            if(order.size() == 0){
                break;
            }
        }

        return finalShipment;
    }

}
