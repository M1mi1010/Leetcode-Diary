class Solution {
    public int maxFrequency(int[] nums, int k) {
        //maybe need to find the pairings with minimal difference which is within k and then a window of values which can all have operations less than k

        int maxx = 0;
        int p1 = 0;
        int p2 = 0;
        long sum = 0;
        //maybe sort array
        Arrays.sort(nums);

        //Cost is (target * window size ) - sum of window
        for (; p2 < nums.length; p2++) {
            //If a valid range
            sum += nums[p2];
           
            long cost = (nums[p2] *  (long)(p2 - p1 + 1)) - sum;
            while (cost > k) {
                sum -= nums[p1];
                p1++;
                cost = (nums[p2] *  (long)(p2 - p1 + 1)) - sum;
            }
            maxx = Math.max(maxx, p2 - p1 + 1);
        }

        return maxx;
    }
}
