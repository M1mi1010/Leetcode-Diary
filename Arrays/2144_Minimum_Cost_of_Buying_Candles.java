class Solution {
    public int minimumCost(int[] cost) {
        // Sort into order descending order
        // Merge sort
        mergeSort(cost, 0, cost.length - 1);
        int totalCost = 0;

        for (int i = cost.length - 1; i >= 0; i -= 3) {
            totalCost += cost[i];

            if (i - 1 >= 0) {
                totalCost += cost[i - 1];
            }
        }
        
        return totalCost;
    }

    private void mergeSort(int[] arr, int l, int r) {
        if (l < r) { //End of merge is not reached
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    private void merge(int[] arr, int l, int m, int r) {
        //Calc sizes of arrays to merge
        int size1 = m - l + 1;
        int size2 = r - m;

        //Create temp arrays

        int[] leftTemp = new int[size1];
        int[] rightTemp = new int[size2];

        //Copy from main arrays to temp ones
        for (int i = 0; i < size1; i++ ) {
            leftTemp[i] = arr[l+i];
        }
        for (int j = 0; j < size2; j++ ) {
            rightTemp[j] = arr[m + 1 + j];
        }
        
         // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray
        int k = l;
        while (i < size1 && j < size2) {
            if (leftTemp[i] <= rightTemp[j]) {
                arr[k] = leftTemp[i];
                i++;
            } else {
                arr[k] = rightTemp[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of LetfTemp[] if any
        while (i < size1) {
            arr[k] = leftTemp[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightTemp[] if any
        while (j < size2) {
            arr[k] = rightTemp[j];
            j++;
            k++;
        }
    }
}
