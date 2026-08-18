class CustomStack {
    Deque<Integer> stack;
    int[] arr;
    int maxx;

    public CustomStack(int maxSize) {
        stack = new ArrayDeque(maxSize);
        arr = new int[1000];
        maxx = maxSize;
    }
    
    public void push(int x) {
        if (stack.size() == maxx) return;
        else stack.push(x);
    }
    
    public int pop() {
        if (stack.isEmpty()) return -1;
        else {
            int incr = arr[stack.size() - 1];
            arr[stack.size() - 1] = 0;
            return stack.pop() + incr;
        }
    }
    
    public void increment(int k, int val) {
        if (k > stack.size()) k = stack.size();
        for (int i = 0; i < k; i++) {
            arr[i]+=val;
        }
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */
