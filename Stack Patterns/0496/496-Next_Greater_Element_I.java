class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }

        for (int i = 0; i < nums1.length; i++) {
            int idx2 = map.get(nums1[i]);
            while (idx2 < nums2.length) {
                if (nums2[idx2] > nums1[i]) {
                    nums1[i] = nums2[idx2];
                    break;
                }
                idx2++;
            }
            if (idx2 == nums2.length) nums1[i] = -1;
        }

        return nums1;
        
    }
}
