/*
 * choose(n, k) = choose(n-1, k-1) + choose(n, k-1)
 * Take/Don't take are mutually exclusive events so add num of permutations.
 * Precompute combos with DP
 */

class PascalsIdentity {
  public int[][] pascalsIdentityDP(int poolSize, int sampleSize) {
    int[][] dp = new int[poolSize + 1][sampleSize + 1];
    dp[0][0] = 1;
    for (int n = 1; n <= poolSize; n++) {
      dp[n][0] = 1;
      for (int k = 1; k <= Math.min(n, sampleSize); k++) {
        dp[n][k] = dp[n - 1][k - 1] + dp[n - 1][k];
      }
    }
    return dp;
  }
}
