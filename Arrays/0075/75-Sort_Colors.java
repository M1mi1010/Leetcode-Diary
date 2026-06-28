class Solution {
    public void sortColors(int[] nums) {
        //Insert all zeros and then insert all 2s to end
        int zeroCount = 0;
        int oneCount = 0;
        int twoCount = 0;

        for (int num : nums) {
            if (num == 0) zeroCount++;
            else if (num == 1) oneCount++;
            else twoCount++;
        }

        for (int i = 0; i < nums.length; i++) {
            if (zeroCount > 0) {
                nums[i] = 0;
                zeroCount--;
            }
            else if (oneCount > 0) {
                nums[i] = 1;
                oneCount--;
            }

            else {
                nums[i] = 2;
            }
        }
        
    }
}
