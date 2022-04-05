package aisd.proj_ind.gr26;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolverTest {

    private Solver solver;

    @Before
    public void setUp() throws Exception {
        solver = new Solver("przyklad_danych.txt");
    }

    @Test
    public void should_getCorrectSum() {
        solver.calculate();
        double sum = solver.getSum();
        double expectedSum = 189145.5;
        assertEquals(expectedSum, sum, 0.005);
    }
}