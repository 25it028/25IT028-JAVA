
package PRACTICAL13;
public class BoundedStats {
    public static <N extends Number> double average(N[] values) {
        double sum = 0;
        for (N num : values) {
            sum += num.doubleValue();
        }
        return sum / values.length;
    }
    public static void main(String[] args) {
        Integer[] a = {10, 20, 30, 40};
        Double[] b = {10.5, 20.5, 30.5};
        Long[] c = {100L, 200L, 300L};

        System.out.println("Integer Average: " + average(a));
        System.out.println("Double Average: " + average(b));
        System.out.println("Long Average: " + average(c));
    }
}
