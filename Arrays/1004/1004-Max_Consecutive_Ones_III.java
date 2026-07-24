class Solution {
    public int longestOnes(int[] nums, int k) {
        int p1 = 0;
        int p2 = 0;
        int noZeros = 0;
        int max = 0;

        for (;p2 < nums.length; p2++) {
            if (nums[p2] == 0) noZeros++;
            
            while (noZeros > k) { 
                if (nums[p1] == 0) noZeros--;
                p1++;
            }

            max = Math.max(max, p2 - p1 + 1);
        }

        return max;
    }
}
