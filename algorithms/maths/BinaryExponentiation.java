class BinaryExponentiation {
  // Recursive
  public static long power(int base, int exponent) {
    if (exponent == 0) {
      return 1L;
    }
    long half = power(base, exponent / 2);
    return exponent % 2 == 1 ? base * half * half : half * half;
  }

  // Iterative
  long powerIterative(long base, long exponent) {
    long res = 1;

    while (exponent > 0) {
      if (exponent % 2 == 1) {
        res = res * base;
      }
      base = base * base;
      exponent /= 2;
    }

    return res;
  }
}
