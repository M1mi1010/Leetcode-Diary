class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        //Get first window
        long sum = 0;
        long max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < k; i++) {
            sum += nums[i];
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        if (map.size() == k) max = sum > max ? sum : max;

        int left = 0;

        for (int right = k; right < nums.length; right++) {
            int count = map.get(nums[left]) - 1;
            if (count == 0) {
                map.remove(nums[left]);
            }
            else {
                map.put(nums[left], count);
            }

            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            sum-=nums[left];
            sum+=nums[right];
            
            if (map.size() == k) max = sum > max ? sum : max;
            left++;
        }

        return max;
    }
}
