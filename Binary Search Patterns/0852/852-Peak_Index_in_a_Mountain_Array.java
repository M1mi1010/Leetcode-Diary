class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int lo = 0;
        int mid;
        int hi = arr.length - 1;

        while (lo < hi) {
            mid = lo + (hi - lo) / 2;

            if (arr[mid] < arr[mid + 1]) lo = mid + 1;
            else hi = mid;
        }

        return hi;
    }
}
