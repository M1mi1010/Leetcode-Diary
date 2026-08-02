class Solution {
    public int[] resultsArray(int[] nums, int k) {
        int[] answer = new int[nums.length  - k + 1];

        int streak = 1;

        if (k == 1) {
            return nums;
        }
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                streak++;
            }
            else {
                streak = 1;
            }

            if (streak >= k) {
                answer[i - k + 1] = nums[i];
            }
            else if (i >= k-1) { //At end of window
                answer[i - k + 1] = -1;
            }
        }

        return answer;
    }
}
