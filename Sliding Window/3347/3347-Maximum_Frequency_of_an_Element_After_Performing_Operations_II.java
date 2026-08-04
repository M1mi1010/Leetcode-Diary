class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int answer = 0;
        int maxFreqValue = 0;
        
        List<Integer> targets = new ArrayList<>(map.keySet());
        Collections.sort(targets);

        for (int target : targets) {
            while (right < nums.length && nums[right] <= target + k) {
                right++;
            }

            while (left < nums.length && nums[left] < target - k) {
                left++;
            }

            int current = right - left - map.get(target) > numOperations ? numOperations : right - left - map.get(target); 

            answer = map.get(target) + current > answer ? map.get(target) + current : answer;
        }

        //Target not in array
        left = 0;

        for (right = 0; right < nums.length; right++) {
            while (nums[right] - nums[left] > 2*k) {
                left++;
            }

            int curr = right - left + 1 < numOperations ? right - left + 1: numOperations;
            answer = answer > curr ? answer : curr;
        }

        return answer;
    }
}
