package UseCases;

/**
 * The DayCellModel class provides utility methods for calculating coordinates
 * used in painting the red dots representing shifts on DayCells.
 */
public final class DayCellModel { //not really a model in the sense that it doesn't get updated

    /**
     * Calculates the Y coordinate for rendering a shift dot based on the shift's time.
     *
     * @param hour The hour component of the shift time.
     * @param minute The minute component of the shift time.
     * @param height The total height available for rendering.
     * @param width The total width available for rendering.
     * @param prev The previous Y coordinate for avoiding overlapping shifts.
     * @return The calculated Y coordinate for rendering the shift.
     */
    public static int getYcoord(int hour, int minute, float height, int prev){
        int y = (int) ((hour * 60.0 + minute) / (60.0 * 24.0) * height * 0.7);
        y = (int) Math.max((height / 15 + prev), y);
        return y;
    }
    /**
     * Calculates the X coordinate for rendering a shift dot within the available width.
     *
     * @param width The total width available for rendering.
     * @return The calculated X coordinate for rendering the shift dot.
     */
    public static int getXcoord(float width){
        return (int) (2.6 * width / 3.0);
    }
}
