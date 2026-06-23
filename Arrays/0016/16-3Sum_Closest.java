class Solution {
    public int threeSumClosest(int[] nums, int target) {
        //For each triplet found, add to a list of lists and store how far from the total it is
        
        Arrays.sort(nums);

        int lastMin = Integer.MAX_VALUE;
        int lastSum = 0;

        for (int i = 0; i < nums.length - 2; i++) {

            if (i > 0 && nums[i - 1] == nums [i]) continue;
            
            int a = i + 1;
            int b = nums.length - 1;

            while ( a < b ) {
                int sum = nums[i]  + nums[a] + nums[b];
                int currMin = Math.abs(sum - target);

                if (currMin < lastMin) {
                    lastMin = currMin;
                    lastSum = sum;
                }

                if (sum < target) {
                    a++;
                }
                else if (sum > target) {
                    b--;
                }
                else { 
                    return target;
                }
            }
        }

        return lastSum;
    }
}
