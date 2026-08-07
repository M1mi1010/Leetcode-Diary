class FreqStack {
    Deque<int[]> stack;
    Map<Integer, Integer> map;
    Map<Integer, Deque<Integer>> freqStacks;
    int highestFreq = 0;

    public FreqStack() {
        freqStacks = new HashMap<>();
        map = new HashMap<>();
    }
    
    public void push(int val) {
        int freq = map.getOrDefault(val, 0) + 1;
        map.put(val, freq);

        highestFreq = freq > highestFreq ? freq : highestFreq;

        freqStacks.computeIfAbsent(freq, x -> new ArrayDeque<>()).push(val);
    }
    
    public int pop() {
        int returned = freqStacks.get(highestFreq).pop();

        int newFreq = map.get(returned) - 1;
        map.put(returned, newFreq);

        if (freqStacks.get(highestFreq).isEmpty()) {
            highestFreq--;
        }

        return returned;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */
