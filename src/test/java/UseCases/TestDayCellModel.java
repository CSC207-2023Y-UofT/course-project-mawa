package UseCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestDayCellModel {

    @Test
    void testGetYcoord() {
        int hour = 8;
        int minute = 30;
        float height = 600.0f;
        int prev = 100;

        int result = DayCellModel.getYcoord(hour, minute, height, prev);

        // Calculate the expected Y coordinate based on the formula in DayCellModel
        int expected = (int) ((hour * 60.0 + minute) / (60.0 * 24.0) * height * 0.7);
        expected = (int) Math.max((height / 15 + prev), expected);

        assertEquals(expected, result);
    }

    @Test
    void testGetXcoord() {
        float width = 800.0f;

        int result = DayCellModel.getXcoord(width);

        int expected = (int) (2.6 * width / 3.0);

        assertEquals(expected, result);
    }
}
