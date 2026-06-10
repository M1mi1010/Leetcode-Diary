class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> l = new ArrayList<>();
        int[] arr = new int[nums.length];

        for (int i = 1; i <= nums.length; i++) {
            int val = nums[i-1];
            arr[val - 1] = -1;
        }

        for (int i = 0; i < nums.length; i++) {
            if (arr[i] != -1) {
                l.add(i+1);
            }
        }
        return l;
    }
}
