package aisd.proj_ind.gr26;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ManufacturerTest {

    private Manufacturer manufacturer;
    private int x;

    @Before
    public void setUp() throws Exception {
        manufacturer = new Manufacturer(0, "Name", 300);
        x = 150;
    }

    @Test
    public void addDailyReaminsTest() {
        manufacturer.addDailyReamins(x);
        assertEquals(450, manufacturer.getDailyRemains());
    }

    @Test
    public void decreaseDailyRemainsTest() {
        manufacturer.decreaseDailyRemains(x);
        assertEquals(150, manufacturer.getDailyRemains());
    }
}