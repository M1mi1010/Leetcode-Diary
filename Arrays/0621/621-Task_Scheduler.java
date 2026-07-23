class Solution {
    public int leastInterval(char[] tasks, int n) {
        //Add everything to a frequency map

        Map<Integer, Integer> frequMap = new HashMap<>();

        for (char t:tasks) {
            frequMap.put((int) t, frequMap.getOrDefault((int)t, 0) + 1);
        }

        //Add to a priority queue for tasks that are available
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[1] - a[1]);
        //Cooldown queue for tasks that are not available
        PriorityQueue<int[]> cooldown = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        for (Map.Entry<Integer, Integer> entry : frequMap.entrySet()) {
            // {A, 3, nextAvailable}
            pq.offer(new int[]{entry.getKey(), entry.getValue()});
        }

        //Keeping track of how many tasks have been processed, number of cycles taken
        int clock = 0;
        int completed = 0;

        while (completed < tasks.length) {
            //Check if any tasks have finished cooling down and the next item in pq can't be processed
            while (!cooldown.isEmpty() && cooldown.peek()[2] <= clock) {
                //Add to the queue of ta(sks that can be done
                int[] task = cooldown.poll();
                pq.offer(new int[]{task[0], task[1]});
            }
            //Check if we can process a task
            if (!pq.isEmpty()) {
                int[] item = pq.poll();
                completed++;

                //Add to cooldown queue
                int remaining = item[1] - 1;

                if (remaining > 0) {
                    cooldown.offer(new int[]{item[0], remaining, clock + n + 1});
                }
            }
            else {
                // Jump clock forward to when next task is available
                if (!cooldown.isEmpty()) {
                    clock = cooldown.peek()[2];
                }
                continue;
            }
            clock++;
        }

        return clock;
    }
}
