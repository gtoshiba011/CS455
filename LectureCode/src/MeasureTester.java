import java.awt.*;

public class MeasureTester {

    public static void main(String[] args) {

        Rectangle[] rects = new Rectangle[]
            {
                new Rectangle(5, 10, 15, 20),
                new Rectangle(1, 3, 5, 7),
                new Rectangle(2, 4, 6, 8)
            };
        System.out.println(Data.average(rects, new AreaMeasurer()));
    }
}
