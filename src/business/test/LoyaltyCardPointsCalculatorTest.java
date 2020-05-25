package business.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoyaltyCardPointsCalculatorTest {
    private LoyaltyCardPointsCalculator pointsUpdater;

    @BeforeEach
    void setUp() {
        pointsUpdater = new LoyaltyCardPointsCalculator();
    }

    @Test
    void add() {
        assertEquals(840, pointsUpdater.add(800, 4.5));
        assertEquals(1000, pointsUpdater.add(800, 20.5));
    }

    @Test
    void subtract() {
        assertEquals(500, pointsUpdater.subtract(2000, 1500));
        assertEquals(1300, pointsUpdater.subtract(1500, 200));
    }
}