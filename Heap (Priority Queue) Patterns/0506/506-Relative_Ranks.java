class Solution {
    public String[] findRelativeRanks(int[] score) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> score[b] - score[a]);

        for (int i = 0; i < score.length; i++) {
            pq.offer(i);
        }

        String[] arr = new String[score.length];
        int counter = 1;

        while (!pq.isEmpty()) {
            if (counter == 1) arr[pq.poll()] = "Gold Medal";
            else if (counter == 2) arr[pq.poll()] = "Silver Medal";
            else if (counter == 3) arr[pq.poll()] = "Bronze Medal";
            else arr[pq.poll()] = String.valueOf(counter);
            counter++;
        }

        return arr;
    }
}
