public class Data {

    public static double average(Object[] objects, Measurer meas) {

        double total = 0;
        for (Object obj: objects) {
            total += meas.measure(obj);
        }
        return (objects.length == 0) ? 0 : total / objects.length;
    }
}
