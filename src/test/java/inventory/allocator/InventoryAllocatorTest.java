package inventory.allocator;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

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
}
