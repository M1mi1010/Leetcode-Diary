class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) { 
            int left = i+1;
            int right = nums.length - 1;

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                }

                else if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                }

                else {
                    list.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    left++;
                    right--;

                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                }
            }
        }

        return list;
    }
}
