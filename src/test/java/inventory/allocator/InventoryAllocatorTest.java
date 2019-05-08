package inventory.allocator;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryAllocatorTest {

    InventoryAllocator allocator = new InventoryAllocator();

    @Test
    public void handlesNullInputs(){
        //Setup
        Map order = new HashMap();
        List warehouseDetails = new ArrayList();

        //Expected
        List expected = new ArrayList();

        //Actual
        List actual = allocator.createShipments(order, warehouseDetails);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void handlesEmptyWareHouseDetails(){
        //Setup
        Map order = new HashMap();
        order.put("apple", 5);
        order.put("banana", 5);
        List warehouseDetails = new ArrayList();

        //Expected
        List expected = new ArrayList();

        //Actual
        List actual = allocator.createShipments(order, warehouseDetails);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void handlesEmptyOrder(){
        //Setup
        Map order = new HashMap();
        List warehouseDetails = new ArrayList();
        warehouseDetails.add("owd");
        warehouseDetails.add("dm");

        //Expected
        List expected = new ArrayList();

        //Actual
        List actual = allocator.createShipments(order, warehouseDetails);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void handlesExactMatch(){
        //Setup
        Map<String, Integer> order = new HashMap();
        order.put("apple", 1);
        List<Warehouse> warehouseDetails = new ArrayList();
        Map<String, Integer> inventory1 = new HashMap<>();
        inventory1.put("apple", 1);
        Warehouse warehouse1 = new Warehouse("owd", inventory1);
        warehouseDetails.add(warehouse1);

        //Actual
        List<Shipment> actual = new ArrayList();
        actual = allocator.createShipments(order, warehouseDetails);

        //Expected
        String expected= "[{owd: {apple=1}}]";

        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void handlesNotEnoughInventory(){
        //Setup
        Map<String, Integer> order = new HashMap();
        order.put("apple", 1);
        List<Warehouse> warehouseDetails = new ArrayList();
        Map<String, Integer> inventory1 = new HashMap<>();
        inventory1.put("apple", 0);
        Warehouse warehouse1 = new Warehouse("owd", inventory1);
        warehouseDetails.add(warehouse1);

        //Actual
        List<Shipment> actual = new ArrayList();
        actual = allocator.createShipments(order, warehouseDetails);

        //Expected
        String expected= "[]";

        Assert.assertEquals(expected, actual.toString());
    }

}
