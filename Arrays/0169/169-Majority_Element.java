class Solution {
    public int majorityElement(int[] nums) {
        int m = nums.length / 2;
        Map<Integer, Integer> map = new HashMap<>(); 

        if (nums.length == 1) {
            return nums[0]; 
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.replace(nums[i], map.get(nums[i]) + 1);
                if (map.get(nums[i]) > m) {
                    return nums[i];
                }
            }
            else {
                map.put(nums[i], 1);
            }
        }
        return -1;
    }
}
