//Attempt 1 passed
class Solution {
    public int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        for (int i = 0; i < nums.length + 1; i++) {
            if (!set.remove(i)) {
                return i;
            }
        }
        return -1;
    }
}
																									 
																								
// Attempt 2 passed
class Solution {
    public int missingNumber(int[] nums) {
        
        int[] arr = new int[nums.length+1];
        for (int i = 0; i < nums.length + 1; i++) {
            arr[i] = i;
        }

        int x = IntStream.of(nums).sum();
        int y = IntStream.of(arr).sum();

        return y-x;
    }
}
