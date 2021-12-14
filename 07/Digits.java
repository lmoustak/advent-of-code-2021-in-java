import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Digits {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Path.of("input.txt"));
            lines = lines.stream()
                    .map(line -> line.split("\\|")[1].trim())
                    .collect(Collectors.toList());
            int count = 0;
            Set<Integer> acceptableNumberOfSegments = Set.of(2, 4, 3, 7);
            for (var line : lines) {
                String[] signals = line.split("\\s+");
                for (var signal : signals) {
                    if (acceptableNumberOfSegments.contains(signal.length())) {
                        count++;
                    }
                }
            }

            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
