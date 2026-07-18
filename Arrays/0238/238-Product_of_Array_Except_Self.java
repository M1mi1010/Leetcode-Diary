class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        int lastRight = 1;
        int lastNum = 0;

        left[0] = 1;
        //Everything left of last element
        for (int i = 0; i < nums.length - 1; i++) {
            left[i+1] = nums[i] * left[i];
        }

        //Everything right of first element
        for (int i = nums.length - 1; i > 0; i--) {
            lastNum = nums[i];
            nums[i] = left[i] * lastRight;
            lastRight *= lastNum;
        }

        nums[0] = left[0] * lastRight;
        
        return nums;
    }
}
