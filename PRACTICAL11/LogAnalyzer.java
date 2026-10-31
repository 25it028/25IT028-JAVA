
import java.nio.file.*;
import java.io.IOException;
import java.util.*;

public class LogAnalyzer {
    public static void main(String[] args) {
        String[] files = {"log1.txt", "log2.txt", "log3.txt"};
        String keyword = "ERROR";

        int totalLines = 0;
        int errorLines = 0;

        try {
            Files.write(Paths.get("log1.txt"),
                Arrays.asList("INFO: Started", "ERROR: Failed", "INFO: Running"));

            Files.write(Paths.get("log2.txt"),
                Arrays.asList("ERROR: Login failed", "INFO: Success"));

            Files.write(Paths.get("log3.txt"),
                Arrays.asList("INFO: Closed", "ERROR: Connection lost"));

            for (String file : files) {
                Path path = Paths.get(file);

                List<String> lines = Files.readAllLines(path);

                totalLines += lines.size();

                for (String line : lines) {
                    if (line.contains(keyword)) {
                        errorLines++;
                    }
                }
                long size = Files.size(path);
                System.out.println("File: " + file);
                System.out.println("Size: " + size + " bytes");
                System.out.println();
            }
            System.out.println("Total Lines: " + totalLines);
            System.out.println("ERROR Lines: " + errorLines);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
