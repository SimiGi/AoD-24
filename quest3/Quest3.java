import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

void main() {
    String input = readInput();
    String filtered = input.replaceAll(".*?mul\\((\\d+),(\\d+)\\)", "$1,$2,");
    int[] inputsToMultiply = Arrays.stream(filtered.split(","))
            .filter(s -> s.matches("\\d+"))
            .mapToInt(Integer::parseInt)
            .toArray();
    System.out.println(STR."Sum of multiplication operations: \{sumUp(inputsToMultiply)}");
}

int sumUp(int[] inputsToMultiply) {
    int sum = 0;
    for (int i = 0; i < inputsToMultiply.length - 1; i = i + 2) {
        sum += inputsToMultiply[i] * inputsToMultiply[i + 1];
    }
    return sum;
}

String readInput() {
    Path path = Path.of("quest3/mul-input.txt");

    try {
        return Files.readString(path).replaceAll("\\n", "");
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
