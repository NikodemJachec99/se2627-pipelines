package se2627;

public class HRCalculator {

    public static int calculateMaxHR(int age) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Age out of bounds");
        }
        return 220 - age;
    }

    public static String getWorkoutZone(int age, int bpm) {
        if (bpm <= 0) {
            throw new IllegalArgumentException("BPM must be positive");
        }
        int maxHR = calculateMaxHR(age);
        double ratio = (double) bpm / maxHR;

        if (ratio < 0.50) return "Resting";
        if (ratio < 0.60) return "Recovery";
        if (ratio < 0.70) return "Fat Burn";
        if (ratio < 0.80) return "Aerobic";
        if (ratio < 0.90) return "Anaerobic";
        return "VO2 Max";
    }
}
