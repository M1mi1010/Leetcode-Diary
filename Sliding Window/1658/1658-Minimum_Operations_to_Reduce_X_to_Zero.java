class Solution {
    public int minOperations(int[] nums, int x) {
        int p1 = 0;

        int sum = 0;
        int target = 0;
        int maxx = -1;

        for (int num : nums) {
            target += num;
        }

        target -= x;

        for (int p2 = 0; p2 < nums.length; p2++) {
            sum += nums[p2];

            while (p1 < nums.length && sum > target) {
                sum -= nums[p1++];
            }   

            if (sum == target) {
                maxx = p2 - p1 + 1 > maxx ? p2 - p1 + 1 : maxx;
            }
        }

        return maxx == -1 ? -1 : nums.length - maxx;
    }
}
