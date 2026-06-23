class Solution {
    public int missingNumber(int[] nums) {
        int x = 0;
        for (int i = 0; i < nums.length; i++) {
            x += nums[i];
        }
        
        int y = ((nums.length) * (nums.length + 1))/2;

        return y-x;
    }
}
