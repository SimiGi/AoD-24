import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

void main() {
    Set<int[]> matrix = getMatrix();
    System.out.println(STR."Total reports: \{matrix.size()}");

    for (Iterator<int[]> iterator = matrix.iterator(); iterator.hasNext(); ) {
        int[] row = iterator.next();
        Boolean isIncreasing = null;
        try {
            for (int i = 0; i < row.length -1; i++) {
                if (i + 1 < row.length && ValueRange.of(1, 3).isValidIntValue(Math.abs(row[i] - row[i + 1]))) {
                    if (isIncreasing == null ) {
                        isIncreasing = row[i] < row[i + 1];
                    } else {
                        if (isIncreasing != row[i] < row[i + 1]) {
                            throw new RuntimeException("Switched increasing/decreasing");
                        }
                    }
                }
                else {
                    throw new RuntimeException("Levels differences are out of bounds [0, 4]");
                }
            }
        } catch (RuntimeException exception) {
            iterator.remove();
        }
    }

    System.out.println(STR."Safe reports: \{matrix.size()}");
}

Set<int[]> getMatrix() {
    Path path = Path.of("quest2/matrix.txt");

    try (Stream<String> lines = Files.lines(path)) {
        return lines
                .map(line -> line.split(" "))
                .map(strArr -> Arrays.stream(strArr)
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .collect(Collectors.toSet());
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
