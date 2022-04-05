package aisd.proj_ind.gr26;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConnectionTest {

    private Connection connection1;
    private Connection connection2;
    private Connection connection3;
    private Connection connection4;

    @Before
    public void setUp() throws Exception {
        Manufacturer manufacturer = new Manufacturer(0, "x", 300);
        Pharmacy pharmacy = new Pharmacy(0, "y", 150);
        connection1 = new Connection(manufacturer, pharmacy, 450, 50.99);
        connection2 = new Connection(manufacturer, pharmacy, 450, 100);
        connection3 = new Connection(manufacturer, pharmacy, 450, 25.50);
        connection4 = new Connection(manufacturer, pharmacy, 750, 50.99);
    }

    @Test
    public void compareToTest() {
        int compare12 = connection1.compareTo(connection2);
        int compare13 = connection1.compareTo(connection3);
        int compare14 = connection1.compareTo(connection4);

        assertEquals(-1, compare12);
        assertEquals(1, compare13);
        assertEquals(1, compare14);
    }
}