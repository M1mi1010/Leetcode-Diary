class Solution {
    public long continuousSubarrays(int[] nums) {
        if (nums.length == 0) return 0;

        long count = 0;
        int left = 0;

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int right = 0; right < nums.length; right++) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            while (map.lastKey() - map.firstKey() > 2) {
                int newCount = map.get(nums[left]) - 1;

                if (newCount == 0) {
                    map.remove(nums[left]);
                }
                else {
                    map.put(nums[left], newCount);
                }
                left++;
            }
            count += right - left + 1;
        }

        return count;
    }
}
