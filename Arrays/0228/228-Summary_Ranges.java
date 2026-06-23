class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> l = new ArrayList<>();

        if (nums.length == 0){
            return l;
        }
        int start = nums[0];
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1 || nums[i] != nums[i+1] - 1) {
                end = nums[i];
                if (end - start == 0) {
                    l.add(String.valueOf(end));
                }
                
                else{
                    l.add(String.valueOf(start) + "->" + String.valueOf(end));
                }

                if (i + 1 < nums.length) {
                    start = nums[i+1];
                }
            }
        }
        return l;
    }
}
