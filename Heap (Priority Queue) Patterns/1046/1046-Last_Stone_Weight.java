class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) {
            pq.offer(stone);
        }

        while (pq.size() > 1) {
            int item = Math.abs(pq.poll() - pq.poll());
            if (item != 0) pq.offer(item);
        }
        
        return pq.size() == 0 ? 0 : pq.poll();
    }
}
