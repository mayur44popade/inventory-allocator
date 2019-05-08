package inventory.allocator;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class InventoryAllocatorTest {

    InventoryAllocator allocator;

    public void setup(){
        allocator = new InventoryAllocator();
    }

    @Test
    public void handlesNullInputs(){
        //Setup
        Map order = null;
        List warehouseDetails = null;

        //Expected
        List expected = new ArrayList();

        //Actual
        List actual = allocator.createShipments(order, warehouseDetails);

        Assert.assertEquals(expected, actual);
    }
}
