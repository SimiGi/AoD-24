import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

char[] XMAS = new char[]{'X', 'M', 'A', 'S'};
String[][] matrix = readInput();

void main() {
    List<Coordinates[]> listOfCoordinates = new ArrayList<>();
    for (int y = 0; y < matrix.length; y++) {
        for (int x = 0; x < matrix[y].length; x++) {
            if (matrix[y][x].charAt(0) == XMAS[0]) {
                listOfCoordinates.addAll(getCoordinatesOfSurroundingMatches(y, x));
            }
        }
    }
    System.out.println(STR."Amount of word \{new String(XMAS)}: \{listOfCoordinates.size()}");
}

List<Coordinates[]> getCoordinatesOfSurroundingMatches(final int yParam, final int xParam) {
    record SearchDirections(int searchDirectionY, int searchDirectionX) {
    }
    SearchDirections[] searchDirections = new SearchDirections[]{
            new SearchDirections(0, -1),
            new SearchDirections(0, 1),
            new SearchDirections(-1, 0),
            new SearchDirections(1, 0),
            new SearchDirections(-1, -1),
            new SearchDirections(-1, 1),
            new SearchDirections(1, -1),
            new SearchDirections(1, 1),
    };

    List<Coordinates[]> list = new ArrayList<>();
    for (SearchDirections searchDirection : searchDirections) {
        Coordinates[] coordinates = searchCoordinates(searchDirection.searchDirectionY, searchDirection.searchDirectionX, yParam, xParam);
        if (coordinates != null) {
            System.out.println(STR."Found coordinates: \{Arrays.toString(coordinates)}");
            list.add(coordinates);
        }
    }

    return list;
}

Coordinates[] searchCoordinates(final int stepY, final int stepsX, final int yParam, final int xParam) {
    int x = xParam + stepsX;
    int y = yParam + stepY;
    Coordinates[] coordinates = new Coordinates[XMAS.length - 1];
    for (int i = 0; i < XMAS.length - 1; i++) {
        if (isWithinBoundaries(y, x) && matrix[y][x].charAt(0) == XMAS[i + 1]) {
            coordinates[i] = new Coordinates(y, x);
            x += stepsX;
            y += stepY;
        } else {
            return null;
        }
    }
    return coordinates;
}

boolean isWithinBoundaries(int y, int x) {
    return (y >= 0 && y < matrix.length && x >= 0 && x < matrix[y].length);
}

String[][] readInput() {
    Path path = Path.of("quest4/word-search.txt");

    try (Stream<String> lines = Files.lines(path)) {
        return lines
                .map(line -> line.split(""))
                .toArray(String[][]::new);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

record Coordinates(int y, int x) {
    @Override
    public String toString() {
        return STR."[y=\{y}, x=\{x}]";
    }
}