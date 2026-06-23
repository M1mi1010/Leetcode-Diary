class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();

        Arrays.sort(nums);

        //Set up two pointer
        
        for (int a = 0; a < nums.length - 3; a++) {

            if (nums[a] > 0 && nums[a] > target) break;

            if ( a > 0 && nums[a] == nums[a-1]) {
                continue;
            }

            for (int b = a + 1; b < nums.length - 2; b++) {

                if (nums[a] + nums[b] > target && nums[b] >= 0) break;

                if (b > a+1 && nums[b] == nums[b-1]) {
                    continue;
                }

                int c = b+1;
                int d = nums.length - 1;

                // Two sum
                while ( c < d) {

                    long sum = (long) nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum == target) {
                        list.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));

                        // Skip duplicates if a match is found
                        c++;
                        while (c < d && nums[c - 1] == nums[c]) {
                            c++;
                        }
                    }

                    else if (sum < target) {
                        c++;
                    }

                    else{
                        d--;
                    }
                }
            }
           
        }
        
        return list;  
    }
}
