class Solution {
    public int[] searchRange(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        int firstInstance = -1;
        int mid;
        int[] result = new int[]{-1,-1};

        //Find instant of target
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) lo = mid + 1;
            else if (nums[mid] == target) {
                firstInstance = mid;
                //Continue searching left to find the first instance
                hi = mid - 1;
            }
            else hi = mid - 1;
        }

        if (firstInstance == -1) return result;
        result[0] = firstInstance;

        //move right
        hi = nums.length - 1;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) lo = mid + 1;
            else if (nums[mid] == target) {
                firstInstance = mid;
                //Continue searching left to find the first instance
                lo = mid + 1;
            }
            else hi = mid - 1;
        }
      

        result[1] = firstInstance;

        return result;
    }
}
