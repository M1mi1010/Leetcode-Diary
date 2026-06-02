class Solution {
    public int removeElement(int[] nums, int val) {
        // p1 reads until match is found
        int count = 0;
        int read = 0;
        int write = 0;

        while (read < nums.length) {
            if (nums[read] == val) {
                read++;
            }
            else {
                nums[write] = nums[read];
                count++;
                write++;
                read++;
            }
        }
        return count;
    }
}
