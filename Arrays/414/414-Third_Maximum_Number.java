class Solution {
    public int thirdMax(int[] nums) {

        Arrays.sort(nums);
        int i = nums.length - 1;

        int max = nums[i];

        int second = max;

        while (i >= 0 && second == max) {
            second = nums[i--];
        }
        
        int third = second;

        while (i >= 0 && third == second) {
            third = nums[i--];
        }

        if (third == second) {
            return max;
        }

        return third;
    }    
}
