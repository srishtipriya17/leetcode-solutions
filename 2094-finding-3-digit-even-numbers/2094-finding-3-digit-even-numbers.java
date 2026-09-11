import java.util.*;

class Solution {
    public int[] findEvenNumbers(int[] digits) {

        // Count how many times each digit appears
        int[] count = new int[10];

        for (int digit : digits) {
            count[digit]++;
        }

        List<Integer> result = new ArrayList<>();

        // Try every 3-digit number
        for (int num = 100; num <= 999; num++) {

            // Number must be even
            if (num % 2 != 0) {
                continue;
            }

            int a = num / 100;          // hundreds digit
            int b = (num / 10) % 10;    // tens digit
            int c = num % 10;           // ones digit

            // Check whether we have enough copies
            count[a]--;
            count[b]--;
            count[c]--;

            if (count[a] >= 0 && count[b] >= 0 && count[c] >= 0) {
                result.add(num);
            }

            // Restore the counts
            count[a]++;
            count[b]++;
            count[c]++;
        }

        // Convert List<Integer> to int[]
        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}