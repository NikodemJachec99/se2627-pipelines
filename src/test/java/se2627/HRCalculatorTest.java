package se2627;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HRCalculatorTest {

    @Test
    public void testValidMaxHR() {
        assertEquals(195, HRCalculator.calculateMaxHR(25));
    }

    @Test
    public void testInvalidMaxHR_ageTooHigh() {
        assertThrows(IllegalArgumentException.class,
                () -> HRCalculator.calculateMaxHR(500));
    }

    @Test
    public void testInvalidMaxHR_ageTooLow() {
        assertThrows(IllegalArgumentException.class,
                () -> HRCalculator.calculateMaxHR(-1));
    }

    @Test
    public void testZone_Resting() {
        assertEquals("Resting", HRCalculator.getWorkoutZone(40, 80));
    }

    @Test
    public void testZone_Recovery_lowerBound() {
        // 50% of maxHR(40)=180 → 90 bpm
        assertEquals("Recovery", HRCalculator.getWorkoutZone(40, 90));
    }

    @Test
    public void testZone_FatBurn_lowerBound() {
        // 60% of 180 = 108
        assertEquals("Fat Burn", HRCalculator.getWorkoutZone(40, 108));
    }

    @Test
    public void testZone_Aerobic_lowerBound() {
        // 70% of 180 = 126
        assertEquals("Aerobic", HRCalculator.getWorkoutZone(40, 126));
    }

    @Test
    public void testZone_Anaerobic_lowerBound() {
        // 80% of 180 = 144
        assertEquals("Anaerobic", HRCalculator.getWorkoutZone(40, 144));
    }

    @Test
    public void testZone_Anaerobic_exampleFromSpec() {
        // age=40, bpm=150 → 150/180 ≈ 83%
        assertEquals("Anaerobic", HRCalculator.getWorkoutZone(40, 150));
    }

    @Test
    public void testZone_VO2Max_lowerBound() {
        // 90% of 180 = 162
        assertEquals("VO2 Max", HRCalculator.getWorkoutZone(40, 162));
    }

    @Test
    public void testZone_VO2Max_above() {
        assertEquals("VO2 Max", HRCalculator.getWorkoutZone(40, 180));
    }

    @Test
    public void testZone_invalidBpm() {
        assertThrows(IllegalArgumentException.class,
                () -> HRCalculator.getWorkoutZone(40, 0));
    }

    @Test
    public void testZone_invalidAge() {
        assertThrows(IllegalArgumentException.class,
                () -> HRCalculator.getWorkoutZone(-1, 100));
    }
}
