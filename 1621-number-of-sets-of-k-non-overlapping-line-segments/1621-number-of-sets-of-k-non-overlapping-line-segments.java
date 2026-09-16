class Solution {

    static final int MOD = 1000000007;

    public int numberOfSets(int n, int k) {

        long[][] dp = new long[n][k + 1];

        // 0 segments -> 1 way
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j <= k; j++) {

            long sum = 0;

            for (int i = 1; i < n; i++) {

                // Add possibilities where the new segment
                // starts at or before i - 1
                sum = (sum + dp[i - 1][j - 1]) % MOD;

                // Don't use point i
                dp[i][j] = dp[i - 1][j];

                // Use a segment ending at i
                dp[i][j] = (dp[i][j] + sum) % MOD;
            }
        }

        return (int) dp[n - 1][k];
    }
}