class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> (nums1[a[0]] + nums2[a[1]])
                    - (nums1[b[0]] + nums2[b[1]])
        );

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            pq.offer(new int[]{i, 0});
        }

        while (result.size() < k && !pq.isEmpty()) {
            int[] pair = pq.poll();

            int i = pair[0];
            int j = pair[1];

            result.add(Arrays.asList(nums1[i], nums2[j]));

            // Move to the next pair in this row
            if (j + 1 < nums2.length) {
                pq.offer(new int[]{i, j + 1});
            }
        }

        return result;
    }
}
