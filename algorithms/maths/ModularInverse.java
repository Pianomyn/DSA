/*
 * Useful where we need division under modulo (Div doesn't work under mod). Eg with combinatorics.
 * (a/b) % m => need to do (a*b^-1) % m.
 *              AKA use mod inverse of b.
 *
 * Modular Inverse
 *    x % m
 * =>
 *    x * x^-1 = 1 % m
 *
 * Fermat's Little Theorem
 *    x^(m-1) % m = 1
 *    if m is prime and x is not divisible by m (usually m is 10^9 + 7)
 * =>
 *    x * x^-1 = x^(m-1) % m
 * =>
 *    x^-1 = x^(m-2) % m
 * So mod inverse of x under m is x^(m-2) % m
 *
 * Fermat's Little Theorem is a special case of Euler's Theorem.
 * Totient Function from Euler's Theorem is based on Lagrange's Theorem
 * CP handbook p.201-203
 */

class ModularInverse {
  public static long modInverse(long x, long mod) {
    return binaryExponentiation(x, mod - 2, mod);
  }

  public static long binaryExponentiation(long base, long exponent, long mod) {
    if (exponent == 0) {
      return 1L;
    }

    long half = binaryExponentiation(base, exponent / 2, mod);
    long result = (half * half) % mod;
    if (exponent % 2 == 1) {
      result = (result * base) % mod;
    }
    return result;
  }
}
