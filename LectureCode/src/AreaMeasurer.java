import java.awt.*;

public class AreaMeasurer implements Measurer {

    public double measure (Object object) {
        Rectangle rect = (Rectangle) object;
        return rect.getWidth() * rect.getHeight();
    }
}