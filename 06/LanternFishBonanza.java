import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class LanternFishBonanza {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Path.of("input.txt"));
            String[] startingFish = lines.get(0).split(",");
            long[] gestationDays = new long[9];
            Arrays.fill(gestationDays, 0L);

            for (var fish : startingFish) {
                gestationDays[Integer.parseInt(fish)]++;
            }

            final int finalDay = 256;

            for (int i = 0; i < finalDay; i++) {
                long fishGivingBirth = gestationDays[0];
                for (int j = 0; j < 8; j++) {
                    gestationDays[j] = gestationDays[j + 1];
                }
                gestationDays[8] = fishGivingBirth;
                gestationDays[6] += fishGivingBirth;
            }

            System.out.println(LongStream.of(gestationDays).sum());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}