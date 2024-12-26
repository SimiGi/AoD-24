import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

void main() {
    int[][] testValues = getLists();
    int[] list1 = getSortedList(testValues, 0);
    int[] list2 = getSortedList(testValues, 1);
    for (int i = 0; i < testValues.length; i++) {
        testValues[i][0] = list1[i];
        testValues[i][1] = list2[i];
    }

    int totalDistance = 0;
    for (int[] testValue : testValues) {
        totalDistance += Math.abs(testValue[0] - testValue[1]);
    }

    System.out.println(totalDistance);
}

int[][] getLists() {
    Path path = Path.of("quest1/lists.txt");

    try (Stream<String> lines = Files.lines(path)) {
        return lines
                .map(line -> line.split(" {3}"))
                .map(strArr -> Arrays.stream(strArr)
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .toArray(int[][]::new);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

int[] getSortedList(int[][] lists, int column) {
    return Arrays.stream(lists)
            .mapToInt(row -> row[column])
            .sorted()
            .toArray();
}
