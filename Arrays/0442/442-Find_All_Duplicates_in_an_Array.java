class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        //The ones that are positive
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]);
            if (nums[val - 1] < 0) list.add(val);
            nums[val - 1] *= -1;
        }

        return list;
    }
}
