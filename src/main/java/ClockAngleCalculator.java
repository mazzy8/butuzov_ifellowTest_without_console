public class ClockAngleCalculator {

    public static double calculateClockAngle(int hours, int minutes) {
        if (hours < 0 || hours > 12 || minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Неправильный формат времени.");
        }
        if (hours == 12) {
            hours = 0;
        }
        double hourAngle = (30 * hours) + (0.5 * minutes);
        double minuteAngle = 6 * minutes;

        double angle = Math.abs(hourAngle - minuteAngle);

        if (angle > 180) {
            angle = 360 - angle;
        }

        return angle;
    }
}