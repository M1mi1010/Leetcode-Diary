class Solution {
    public int maximumBeauty(int[] nums, int k) {
        
        Arrays.sort(nums);

        int answer = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {

            while (nums[right] - nums[left] > 2 * k) {
                left++;
            }

            answer = right - left + 1 > answer ? right - left + 1 : answer;
        }

        return answer;
    }
}
