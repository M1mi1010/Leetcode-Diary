class Solution {
    public int removeDuplicates(int[] nums) {
        int p1 = 0;
        int p2 = 1;
        for (int i = 0; i < nums.length - 1; i++){
            while (p2 < nums.length && nums[p1] == nums[p2]) {
                p2++;
            }
            if (!(p2 < nums.length)) {
                return p1 + 1;
            }
            p1++;
            nums[p1] = nums[p2];
        }
        return p1 + 1;
    }
}
