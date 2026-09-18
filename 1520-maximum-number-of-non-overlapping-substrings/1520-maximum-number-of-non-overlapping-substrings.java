import java.util.*;

class Solution {

    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        // first[i] = first occurrence of character i
        // last[i] = last occurrence of character i
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        // Find first and last occurrence
        for (int i = 0; i < n; i++) {

            int ch = s.charAt(i) - 'a';

            first[ch] = Math.min(first[ch], i);
            last[ch] = i;
        }

        // Store valid intervals
        List<int[]> intervals = new ArrayList<>();

        // Try every character
        for (int ch = 0; ch < 26; ch++) {

            // Character does not exist
            if (last[ch] == -1) {
                continue;
            }

            int start = first[ch];
            int end = last[ch];

            boolean valid = true;

            // Check all characters inside the interval
            for (int i = start; i <= end; i++) {

                int current = s.charAt(i) - 'a';

                // This character appeared before start.
                // Therefore we cannot make a valid substring
                // starting at 'start'.
                if (first[current] < start) {
                    valid = false;
                    break;
                }

                // We must include all occurrences of this character.
                end = Math.max(end, last[current]);
            }

            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        /*
         * Choose maximum number of non-overlapping intervals.
         *
         * Since every valid interval is a minimal valid interval,
         * choosing intervals with the earliest ending position
         * gives the maximum number.
         */
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> answer = new ArrayList<>();

        int prevEnd = -1;

        for (int[] interval : intervals) {

            int start = interval[0];
            int end = interval[1];

            if (start > prevEnd) {

                answer.add(s.substring(start, end + 1));

                prevEnd = end;
            }
        }

        return answer;
    }
}