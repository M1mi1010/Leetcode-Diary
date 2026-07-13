class Solution {
    public void rotate(int[] nums, int k) {
        if (nums.length == 1) return;

        int p1 = 0;
        int p2 = nums.length - 1;

        //Reverse entire array 
        while (p1 < p2) {
            int temp = nums[p2];
            nums[p2] = nums[p1];
            nums[p1] = temp;

            p1++;
            p2--;
        }

        //Reverse first part of array
        p1 = 0;
        p2 = (k % nums.length) - 1;
        while (p1 < p2) {
            int temp = nums[p2];
            nums[p2] = nums[p1];
            nums[p1] = temp;

            p1++;
            p2--;
        }

        //Reverse last part of array
        p1 = k % nums.length;
        p2 = nums.length - 1;

        while (p1 < p2) {
            int temp = nums[p2];
            nums[p2] = nums[p1];
            nums[p1] = temp;

            p1++;
            p2--;
        }
    }
}
