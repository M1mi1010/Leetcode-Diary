class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int[] answer = new int[nums.length - k + 1];

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < k; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for (int i = 0; i < answer.length; i++) {

            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
                if (a[1] != b[1]) {
                    return b[1] - a[1];
                }
                return b[0] - a[0];}
                );

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                pq.offer(new int[]{entry.getKey(), entry.getValue()});
            }

            //Get top x occurrences
            int sum = 0;
            for (int j = 0; j < x; j++) {
                if (pq.isEmpty()) break;
                int[] item = pq.poll();
                sum += (item[0] * item[1]);
            }
            answer[i] = sum;

            // Update count
            int count = map.getOrDefault(nums[i], 0) - 1;
            if (count > 0) {
                map.put(nums[i], map.get(nums[i]) - 1);
            }
            else {
                map.remove(nums[i]);
            }

            if (i == answer.length -1) break;

            map.put(nums[i+k], map.getOrDefault(nums[i+k], 0) + 1);
        }
        return answer;
    }
}
