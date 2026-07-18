class Solution {
    public int longestMountain(int[] arr) {
        int[] up = new int[arr.length];
        int[] down = new int[arr.length];
        int longest = 0;

        // Find out how many steps up to each point i
        up[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i-1] < arr[i]) {up[i] = up[i-1] + 1;}
        }

        // Find out how many steps down to each point i
        down[down.length - 1] = 0;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > arr[i+1]) {down[i] = down[i+1] + 1;}

            if (up[i] > 0 && down[i] > 0) {
                longest = Math.max(longest, up[i] + down[i] + 1);
            }
        }
        
        return longest;
    }
}
