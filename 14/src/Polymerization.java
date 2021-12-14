import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Polymerization {
    public static void main(String[] args) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Path.of("input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String start = lines.get(0);
        Map<String, Long> pairCounts = new HashMap<>();
        for (int i = 1; i < start.length(); i++) {
            String pair = String.format("%s%s", start.charAt(i - 1), start.charAt(i));
            pairCounts.merge(pair, 1L, Long::sum);
        }

        Map<String, String> insertions = lines.subList(2, lines.size())
                                              .stream()
                                              .collect(Collectors.toMap(line -> line.split("\\s+->\\s+")[0],
                                                                        line -> line.split("\\s+->\\s+")[1]));

        for (int i = 0; i < 40; i++) {
            Map<String, Long> currentPairCounts = Map.copyOf(pairCounts);
            for (var entry : currentPairCounts.entrySet()) {
                String pair = entry.getKey();
                Long count = entry.getValue();
                String insertion = insertions.get(pair);
                if (insertion != null) {
                    pairCounts.compute(pair, (k, v) -> v - count);
                    String[] elements = pair.split("");
                    pairCounts.merge(elements[0] + insertion, count, Long::sum);
                    pairCounts.merge(insertion + elements[1], count, Long::sum);
                }
            }
        }

        Map<String, Long> counters = new HashMap<>();
        for (var entry : pairCounts.entrySet()) {
            String pair = entry.getKey();
            String[] elements = pair.split("");
            Long count = entry.getValue();
            for (var element : elements) {
                counters.merge(element, count, Long::sum);
            }
        }

        counters.replaceAll((pair, count) -> count / 2);
        counters.merge(Character.toString(start.charAt(0)), 1L, Long::sum);
        counters.merge(Character.toString(start.charAt(start.length() - 1)), 1L, Long::sum);

        long mostCommonQuantity = 0;
        long leastCommonQuantity = Long.MAX_VALUE;
        for (var count : counters.values()) {
            mostCommonQuantity = Math.max(mostCommonQuantity, count);
            leastCommonQuantity = Math.min(leastCommonQuantity, count);
        }

        System.out.println(mostCommonQuantity - leastCommonQuantity);
    }
}