import java.util.*;

class Solution {
    public int findMinDifference(List<String> timePoints) {

        List<Integer> times = new ArrayList<>();

        // Convert HH:MM to minutes
        for (String time : timePoints) {
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(3, 5));

            times.add(hour * 60 + minute);
        }

        // Sort times
        Collections.sort(times);

        int minDiff = Integer.MAX_VALUE;

        // Compare adjacent times
        for (int i = 1; i < times.size(); i++) {
            minDiff = Math.min(minDiff, times.get(i) - times.get(i - 1));
        }

        // Compare last time with first time across midnight
        int circularDiff = (1440 - times.get(times.size() - 1))
                          + times.get(0);

        minDiff = Math.min(minDiff, circularDiff);

        return minDiff;
    }
}