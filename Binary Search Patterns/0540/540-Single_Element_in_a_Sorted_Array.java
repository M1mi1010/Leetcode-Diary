class Solution {
    public int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int mid;
        int hi = nums.length - 1;

        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (mid % 2 != 0) mid--;
            
            if (hi == lo) return nums[hi];
            if (nums[mid] == nums[mid + 1]) lo = mid + 2;
            else if (nums[mid] != nums[mid + 1]) hi = mid;
        }

        return -1;
    }
}
