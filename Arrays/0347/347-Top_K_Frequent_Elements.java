class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Populate a frequency map for each number
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            
        }

        int[] results = new int[k];
        int index = 0;

        // a[0] = number
        // a[1] = frequency

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            //Add new array into the queue
            heap.offer(new int[]{entry.getKey(), entry.getValue()});

            if (heap.size() > k) {
                //Remove items when the heap is greater than the size k
                heap.poll();
            }
        }
        
        for (int i = 0; i < k; i++) {
            results[i] = heap.poll()[0];
        }
        return results;
    }
}
