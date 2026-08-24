class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int lo = 0;
        int hi = arr.length - k; //Because searching for the start index of the window
        int mid;
        List<Integer> result = new ArrayList<>(k);

        //Binary search to left most
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;

            if (x - arr[mid] > arr[mid + k] - x) lo = mid + 1;
            else hi = mid;
        }

        for (int i = lo; i < lo + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }
}
