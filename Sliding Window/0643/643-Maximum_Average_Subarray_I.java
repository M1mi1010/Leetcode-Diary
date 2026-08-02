class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int maxx = Integer.MIN_VALUE;
        int curMax = 0;

        for (int i = 0; i < k; i++) {
            curMax += nums[i];
        }

        maxx = Math.max(curMax, maxx);

        //Sliding window
        for (int i = k; i < nums.length; i++) {
            curMax += nums[i] - nums[i-k];
            if (curMax > maxx) maxx = curMax;
        }
        
        return (double) maxx / k;
    }
}
