package PRACTICAL12;
import java.util.*;
public class UniqueVisitors {
    public static void main(String[] args) {
        String[] logins = {
            "Tanvi", "Happy", "Jash", "Tanvi",
            "Riya", "Happy", "Meet", "Jash"
        };
        HashSet<String> set = new HashSet<>();
        LinkedHashSet<String> linkedSet = new LinkedHashSet<>();
        for (String name : logins) {
            set.add(name);
            linkedSet.add(name); }
        System.out.println("Unique Visitors Count: " + set.size());
        System.out.println("First Three Unique Visitors:");
        int count = 0;
        for (String name : linkedSet) {
            if (count == 3) {
                break;
            }
            System.out.println(name);
            count++;
        }}
}
