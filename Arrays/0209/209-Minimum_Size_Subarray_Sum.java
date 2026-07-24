class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;

        int p1 = 0;
        int p2 = 0;
        int currentSum = 0;

        while (p2 < nums.length) {
            currentSum += nums[p2];

            while (p1 <= p2 && currentSum >= target) {
                minLength = Math.min(p2 - p1 + 1, minLength);
                currentSum -= nums[p1];
                p1++;
            }

            p2++;
        }


        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
