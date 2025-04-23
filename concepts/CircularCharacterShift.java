import java.util.List;

class CircularCharacterShift {
  public static char lowercaseCharShift(char c, int offset) {
    char shifted = (char)('a' + ((c - 'a' + offset) % 26 + 26) % 26);
    return shifted;
  }

  public static void main(String[] args) {
    record LowercaseTest(char c, int offset, char expected) {}

    List<LowercaseTest> lowercaseTests = List.of(
        new LowercaseTest('y', 3, 'b'), new LowercaseTest('b', -3, 'y'),
        new LowercaseTest('y', 29, 'b'), new LowercaseTest('b', -29, 'y'),
        new LowercaseTest('b', 0, 'b'));

    for (LowercaseTest t : lowercaseTests) {
      System.out.println(String.format(
          "%c %c", lowercaseCharShift(t.c, t.offset), t.expected));
    }
  }
}
