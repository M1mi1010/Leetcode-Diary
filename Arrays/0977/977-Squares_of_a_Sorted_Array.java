class Solution {
    public int[] sortedSquares(int[] nums) {
        int[] arr = new int[nums.length];
        
        int left = 0;
        int right = nums.length - 1;
        int pos = right;

        while (left <= right) {
            int cr = Math.abs(nums[right]);
            int cl = Math.abs(nums[left]);

            if (cr >= cl) {
                arr[pos] = cr * cr;
                right--;
                pos--;
            }
            
            else {
                arr[pos] = cl * cl;
                left++;
                pos--;
            }
        }

        return arr;
    }
}
