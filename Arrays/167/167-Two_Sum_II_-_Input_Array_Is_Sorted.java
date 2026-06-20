class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int p2 = numbers.length - 1;
        int p1 = 0;
        int[] arr = new int[2];
        boolean found = false;

        while (!found) {
            int sum = numbers[p1] + numbers[p2];
            if (sum > target) {
                p2--;
            }

            if (sum < target) {
                p1++;
            }

            if (sum == target) {
                arr[0] = p1+1;
                arr[1] = p2+1;
                found = true;
            }
        }
        return arr;
    }
}
