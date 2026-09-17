class Solution {

    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;

        // best[i] = shortest subarray with sum target
        // that ends at or before index i
        int[] best = new int[n];

        int INF = 1000000000;

        for (int i = 0; i < n; i++) {
            best[i] = INF;
        }

        int left = 0;
        int sum = 0;
        int answer = INF;
        int minLength = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            // Shrink window if sum becomes too large
            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            // We found a subarray with sum = target
            if (sum == target) {

                int length = right - left + 1;

                // Check if there is a previous
                // non-overlapping subarray
                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(
                        answer,
                        length + best[left - 1]
                    );
                }

                // Store the shortest subarray found so far
                minLength = Math.min(minLength, length);
            }

            // Best answer up to current index
            best[right] = minLength;
        }

        return answer == INF ? -1 : answer;
    }
}