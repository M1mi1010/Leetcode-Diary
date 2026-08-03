import java.util.*;

class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);
        int n = nums.length;
        int maxFreq = 0;

        // Frequency map for quick "already equal" count
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        int minVal = nums[0];
        int maxVal = nums[n - 1];

        // Try every integer between min and max as target
        for (int target = minVal; target <= maxVal; target++) {
            int startIndex = binarySearchLeft(nums, target - k);
            int endIndex = binarySearchRight(nums, target + k);

            int countInRange = endIndex - startIndex;          // all nums in [target-k, target+k]
            int alreadyEqual = freq.getOrDefault(target, 0);   // how many are already == target
            int convertible = countInRange - alreadyEqual;     // others we could change to target

            int ops = Math.min(convertible, numOperations);
            int possible = alreadyEqual + ops;

            if (possible > maxFreq) {
                maxFreq = possible;
            }
        }

        return maxFreq;
    }

    private int binarySearchLeft(int[] nums, int val) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < val) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private int binarySearchRight(int[] nums, int val) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] <= val) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
