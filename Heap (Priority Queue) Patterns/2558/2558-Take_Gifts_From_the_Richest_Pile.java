class Solution {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        long sum = 0;

        for (int gift : gifts) {
            pq.offer(gift);
            sum += gift;
        }

        while (k > 0) {
            int top = pq.poll();
            int update = (int) Math.sqrt(top);
            pq.offer(update);
            sum -= top - update;
            k--;
        }

        return sum;
    }
}
