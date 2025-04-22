class ExponentiationBySquares {
  public static long power(int base, int exponent) {
    if (exponent == 0) {
      return 1L;
    }

    long half = power(base, exponent / 2);

    if (exponent % 2 == 1) {
      return base * half * half;
    }
    return half * half;
  }
}
