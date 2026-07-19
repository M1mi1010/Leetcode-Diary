class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();

        //Populate frequency map
        for (Character c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        //Make a priority queue which stores in descending order, arr[0] = character, arr[1] = freq
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        //Add each entry from freq map to pq
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.offer(new int[]{entry.getKey() - 'a', entry.getValue()});
        }
        
        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            int[] item = pq.poll();
            for (int j = 0; j < item[1]; j++) {
                sb.append((char) (item[0] + 'a'));
            }
        }

        return sb.toString();
    }
}
