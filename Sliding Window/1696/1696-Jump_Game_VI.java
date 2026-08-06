class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];

        Deque<Integer> deque = new ArrayDeque<>(); // stores indices
        deque.offerLast(0);

        for (int i = 1; i < n; i++) {
            // 1. Remove indices from the front that are out of the window [i-k, i-1]
            while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }

            // 2. The front of the deque now holds the best j in range — use it
            dp[i] = dp[deque.peekFirst()] + nums[i];

            // 3. Maintain decreasing order: remove from the back anything
            //    with a dp value <= dp[i], since dp[i] is now more useful
            //    (it's just as good or better, AND it's more recent, so it'll
            //    stay in range longer)
            while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                deque.pollLast();
            }

            // 4. Add i itself as a future candidate
            deque.offerLast(i);
        }

        return dp[n - 1];
    }
}
