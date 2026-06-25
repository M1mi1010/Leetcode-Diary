class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int write = 0;
        int read = nums.length - 1;

        while (read > write) {

            while (write < nums.length && nums[write] % 2 == 0) {
                write++;
            }

            while (read >= 0 && nums[read] % 2 != 0) {
                read--;
            }

            if (read > write) {
                int temp = nums[write];
                nums[write] = nums[read];
                nums[read] = temp;
                write++;
                read--;
            }
            
        } 

        return nums;
    }
}
