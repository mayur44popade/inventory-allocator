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

    @Test
    public void handlesSplitAcrossInventory(){
        //Setup
        Map<String, Integer> order = new HashMap();
        order.put("apple", 10);
        List<Warehouse> warehouseDetails = new ArrayList();
        Map<String, Integer> inventory1 = new HashMap<>();
        inventory1.put("apple", 5);
        Warehouse warehouse1 = new Warehouse("owd", inventory1);
        warehouseDetails.add(warehouse1);
        Map<String, Integer> inventory2 = new HashMap<>();
        inventory2.put("apple", 5);
        Warehouse warehouse2 = new Warehouse("dm", inventory2);
        warehouseDetails.add(warehouse2);

        //Actual
        List<Shipment> actual = new ArrayList();
        actual = allocator.createShipments(order, warehouseDetails);

        //Expected
        String expected= "[{owd: {apple=5}}, {dm: {apple=5}}]";

        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void handlesExactMatchWithMultipleKeysInOrder(){
        //Setup
        Map<String, Integer> order = new HashMap();
        order.put("apple", 10);
        order.put("banana", 10);
        List<Warehouse> warehouseDetails = new ArrayList();
        Map<String, Integer> inventory1 = new HashMap<>();
        inventory1.put("apple", 10);
        inventory1.put("banana", 10);
        Warehouse warehouse1 = new Warehouse("owd", inventory1);
        warehouseDetails.add(warehouse1);

        //Actual
        List<Shipment> actual = new ArrayList();
        actual = allocator.createShipments(order, warehouseDetails);

        //Expected
        String expected= "[{owd: {banana=10, apple=10}}]";

        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void handlesGeneralScenarioWithEquallyDistributedItems(){
        //Setup
        Map<String, Integer> order = new HashMap();
        order.put("apple", 10);
        order.put("banana", 10);
        order.put("orange", 10);
        List<Warehouse> warehouseDetails = new ArrayList();
        Map<String, Integer> inventory1 = new HashMap<>();
        inventory1.put("apple", 5);
        inventory1.put("banana", 5);
        inventory1.put("orange", 5);
        Warehouse warehouse1 = new Warehouse("dm", inventory1);
        warehouseDetails.add(warehouse1);
        Map<String, Integer> inventory2 = new HashMap<>();
        inventory2.put("apple", 5);
        inventory2.put("banana", 5);
        inventory2.put("orange", 5);
        Warehouse warehouse2 = new Warehouse("owd", inventory2);
        warehouseDetails.add(warehouse2);

        //Actual
        List<Shipment> actual = new ArrayList();
        actual = allocator.createShipments(order, warehouseDetails);

        //Expected
        String expected= "[{dm: {banana=5, orange=5, apple=5}}, {owd: {banana=5, orange=5, apple=5}}]";

        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void handlesGeneralScenarioWithRandomlyDistributedItems(){
        //Setup
        Map<String, Integer> order = new HashMap();
        order.put("apple", 5);
        order.put("banana", 2);
        order.put("orange", 7);
        List<Warehouse> warehouseDetails = new ArrayList();
        Map<String, Integer> inventory1 = new HashMap<>();
        inventory1.put("apple", 2);
        inventory1.put("banana", 1);
        inventory1.put("orange", 1);
        Warehouse warehouse1 = new Warehouse("dm", inventory1);
        warehouseDetails.add(warehouse1);
        Map<String, Integer> inventory2 = new HashMap<>();
        inventory2.put("apple", 1);
        inventory2.put("banana", 1);
        inventory2.put("orange", 2);
        Warehouse warehouse2 = new Warehouse("owd", inventory2);
        warehouseDetails.add(warehouse2);
        Map<String, Integer> inventory3 = new HashMap<>();
        inventory3.put("apple", 2);
        inventory3.put("banana", 1);
        inventory3.put("orange", 10);
        Warehouse warehouse3 = new Warehouse("pix", inventory3);
        warehouseDetails.add(warehouse3);

        //Actual
        List<Shipment> actual = new ArrayList();
        actual = allocator.createShipments(order, warehouseDetails);

        //Expected
        String expected= "[{dm: {banana=5, orange=5, apple=5}}, {owd: {banana=5, orange=5, apple=5}}]";

        Assert.assertEquals(expected, actual.toString());
    }
}
