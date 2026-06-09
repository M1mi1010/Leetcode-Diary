class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> intersect = new HashSet<Integer>();

        for (int num: nums1) {
            set1.add(num);
        }

        for (int num: nums2) {
            if (set1.contains(num)) {
                intersect.add(num);
            }
        }

        int arr[] = new int[intersect.size()];
        int i = 0;
        for (int num : intersect) {
            arr[i] = num;
            i++;
        }

        return arr;
    }
}
