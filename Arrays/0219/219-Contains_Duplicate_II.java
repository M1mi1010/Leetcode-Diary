class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // Use a set, add each item into the set, if adding returns true check if 
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {   
            if (map.containsKey(nums[i])) {
                if (Math.abs(i - map.get(nums[i])) <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
