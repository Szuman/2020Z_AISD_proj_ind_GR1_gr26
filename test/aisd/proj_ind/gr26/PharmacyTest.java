package aisd.proj_ind.gr26;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PharmacyTest {

    private Pharmacy pharmacy;
    private int x;

    @Before
    public void setUp() throws Exception {
        pharmacy = new Pharmacy(0, "Name", 300);
        x = 150;
    }

    @Test
    public void addDailyReaminsTest() {
        pharmacy.addDailyReamins(x);
        assertEquals(450, pharmacy.getDailyRemains());
    }

    @Test
    public void decreaseDailyRemains() {
        pharmacy.decreaseDailyRemains(x);
        assertEquals(150, pharmacy.getDailyRemains());
    }
}