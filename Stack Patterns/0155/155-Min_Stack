class MinStack {
    Deque<int[]> stack;
    int min = Integer.MAX_VALUE;

    public MinStack() {
        stack = new ArrayDeque<>();
    }
    
    public void push(int value) {
        if (value < min) min = value;
        stack.push(new int[]{value, min});
    }
    
    public void pop() {
        stack.pop();
        if (stack.isEmpty()) min = Integer.MAX_VALUE;
        else min = stack.peek()[1];
    }
    
    public int top() {
        return stack.peek()[0];
    }
    
    public int getMin() {
        return stack.peek()[1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
