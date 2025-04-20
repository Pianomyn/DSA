import java.util.Arrays;

/*
 * All primes in a range
 *
 * Time
 * O(n * log(log(n)))
 */
class SieveOfEratosthenese {
  public static boolean[] sieveOfEratosthenese(int upperBound) {
    boolean[] isPrime = new boolean[upperBound + 1];
    int maxFactor = (int)Math.sqrt(upperBound);

    Arrays.fill(isPrime, true);
    isPrime[0] = isPrime[1] = false;

    for (int divisor = 2; divisor <= maxFactor; divisor++) {
      if (!isPrime[divisor]) {
        continue;
      }

      for (
        int multiple = divisor * divisor;
        multiple <= upperBound;
        multiple += divisor
      ) {
        isPrime[multiple] = false;
      }
    }
    return isPrime;
  }

  public static void main(String[] args) {
    int upperBound = 50;
    boolean[] isPrime = sieveOfEratosthenese(upperBound);

    for(int i = 0; i < isPrime.length; i++) {
      System.out.println(String.format("%d: %b", i, isPrime[i]));
    }

  }
}
