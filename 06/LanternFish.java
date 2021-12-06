import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LanternFish {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Path.of("input.txt"));
            String[] startingFish = lines.get(0).split(",");
            List<Integer> fish = new ArrayList<>();

            for (var sf : startingFish) {
                fish.add(Integer.parseInt(sf));
            }

            for (int i = 0; i < 80; i++) {
                int fishToAdd = 0;
                for (int j = 0; j < fish.size(); j++) {
                    Integer f = fish.get(j);
                    if (f.equals(0)) {
                        f = 6;
                        fishToAdd++;
                    } else {
                        f--;
                    }
                    fish.set(j, f);
                }

                for (int j = 0; j < fishToAdd; j++) {
                    fish.add(8);
                }
            }

            System.out.println(fish.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}