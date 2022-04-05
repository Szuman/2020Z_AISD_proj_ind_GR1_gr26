package aisd.proj_ind.gr26;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class FileReaderTest {

    private String fakePath;
    private FileReader fileReader;

    @Before
    public void setUp() throws Exception {
        fakePath = "fakePath";
        fileReader = new FileReader();
    }

    @Test(expected = NullPointerException.class)
    public void should_throwFileNotFoundException() {
        try {
            fileReader.loadData(fakePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert false;
    }
}