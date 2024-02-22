import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RunTest {

    @BeforeAll
    public static void before() {
        System.out.println("Start tests");
    }

    @AfterAll
    public static void after() {
        System.out.println("End tests");
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0",
            "3, 0, 90",
            "6, 0, 180",
            "9, 0, 90",
    })
    void testCalculateClockAngle(int hours, int minutes, double expectedAngle) {
        double actualAngle = ClockAngleCalculator.calculateClockAngle(hours, minutes);
        assertEquals(expectedAngle, actualAngle, 0.1,
                () -> "Неверный угол для времени: " + hours + ":" + minutes);
    }

    @Test
    public void testAngleAtElevenFiftyNine() {
        assertEquals(5.5, ClockAngleCalculator.calculateClockAngle(11, 59), "Угол в 11:59 должен быть 5.5 градусов");
    }

    @Test
    public void testAngleAtTwelveThirty() {
        assertEquals(165, ClockAngleCalculator.calculateClockAngle(12, 30), "Угол в 12:30 должен быть 165 градусов");
    }

    @Test
    public void testAngleAtFourFiftyNine() {
        assertEquals(155.5, ClockAngleCalculator.calculateClockAngle(4, 59), "Угол в 4:59 должен быть 155.5 градусов");
    }

    @Test
    public void testAngleAtFiveOClock() {
        assertEquals(150, ClockAngleCalculator.calculateClockAngle(5, 0), "Угол в 5:00 должен быть 150 градусов");
    }

    @Test
    public void testAngleAtFiveOOne() {
        assertEquals(144.5, ClockAngleCalculator.calculateClockAngle(5, 1), "Угол в 5:01 должен быть 144.5 градусов");
    }

    @Test
    public void testCalculateClockAngleWithNegativeHour() {
        assertThrows(IllegalArgumentException.class,
                () -> ClockAngleCalculator.calculateClockAngle(-1, 30),
                "Должно быть выброшено исключение для отрицательного значения часа");
    }

    @Test
    public void testCalculateClockAngleWithHourGreaterThan12() {
        assertThrows(IllegalArgumentException.class,
                () -> ClockAngleCalculator.calculateClockAngle(13, 0),
                "Должно быть выброшено исключение для часов больше 12");
    }

    @Test
    public void testCalculateClockAngleWithNegativeMinute() {
        assertThrows(IllegalArgumentException.class,
                () -> ClockAngleCalculator.calculateClockAngle(10, -1),
                "Должно быть выброшено исключение для отрицательного значения минут");
    }

    @Test
    public void testCalculateClockAngleWithMinuteGreaterThan59() {
        // test with error
        assertThrows(IllegalArgumentException.class,
                () -> ClockAngleCalculator.calculateClockAngle(11, 59),
                "Должно быть выброшено исключение для минут больше 59");
    }
}