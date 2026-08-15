class Solution {
    public int findKthPositive(int[] arr, int k) {
        int noMissing = arr[arr.length - 1] - arr.length;
        
        int right = arr.length - 1;
        int left = 0;
        int mid = 0;

        while (left <= right) {
            mid = left + (right - left) / 2;
            noMissing = arr[mid] - (mid + 1);
            if (noMissing < k) left = mid + 1;
            else right = mid - 1;
        }

        return left + k;
    
    }
}
