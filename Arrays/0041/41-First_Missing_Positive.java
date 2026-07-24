class Solution {
    public int firstMissingPositive(int[] nums) {

        for (int i = 0; i < nums.length ; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length) continue;
            //Swap
            while (nums[i] > 0 && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
                if (nums[i] <= 0 || nums[i] > nums.length) break;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i+1;
        }
        
       return nums.length + 1;
    }
}
