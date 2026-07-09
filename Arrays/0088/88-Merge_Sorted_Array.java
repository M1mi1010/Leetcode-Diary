class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int writeTo = nums1.length - 1;

        if (nums1.length == 1) {
            if (nums2.length == 0) return;
            nums1[0] = nums2[0];
        }

        while (p1 >= 0 && p2 >= 0) {

            if (nums1[p1] > nums2[p2]) {
                nums1[writeTo] = nums1[p1];
                p1--;
                writeTo--;
            }
            else {
                nums1[writeTo] = nums2[p2];
                writeTo--;
                p2--;
                continue;
            }
        }

        // Copy rest of nums2 elements
        while (p2 >= 0) {
            nums1[writeTo] = nums2[p2];
            writeTo--;
            p2--;
        }
    }
}
