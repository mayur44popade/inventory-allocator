package inventory.allocator;

import java.util.*;

public class InventoryAllocator {

    public List<Shipment> createShipments(Map<String, Integer> order, List<Warehouse> warehouseDetails){

        List<Shipment> finalShipment = new ArrayList();

        if(null == order || null == warehouseDetails || warehouseDetails.size() == 0 || order.size() == 0){
            return finalShipment;
        }

        for(Warehouse warehouse : warehouseDetails){
            Shipment shipment = new Shipment();
            shipment.setWarehouseName(warehouse.getName());

            for(String itemName: warehouse.inventory.keySet()){
                if(order.containsKey(itemName)){
                    if(order.get(itemName) == warehouse.inventory.get(itemName)){
                        shipment.shipmentDetails.put(itemName, order.get(itemName));
                        order.remove(itemName);
                        warehouse.inventory.put(itemName, 0);
                    }else if(order.get(itemName) < warehouse.inventory.get(itemName)){
                        shipment.shipmentDetails.put(itemName, order.get(itemName));
                        warehouse.inventory.put(itemName, warehouse.inventory.get(itemName)-order.get(itemName));
                        order.remove(itemName);
                    }else{
                        if(warehouse.inventory.get(itemName) != 0){
                            shipment.shipmentDetails.put(itemName, warehouse.inventory.get(itemName));
                            order.put(itemName, order.get(itemName)-warehouse.inventory.get(itemName));
                            warehouse.inventory.put(itemName, 0);
                        }
                    }
                }
                //Break if all orders are fulfilled
                if(order.size() == 0){
                    break;
                }
            }
            if(shipment.shipmentDetails.size() != 0){
                finalShipment.add(shipment);
            }
            //Break if all orders are fulfilled
            if(order.size() == 0){
                break;
            }
        }

        return finalShipment;
    }

}
