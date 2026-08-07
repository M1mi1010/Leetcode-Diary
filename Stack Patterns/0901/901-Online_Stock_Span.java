class StockSpanner {
    Deque<int[]> stack;

    public StockSpanner() {
        stack = new ArrayDeque<>();
    }
    
    public int next(int price) {
        // Maintain a monotonically decreasing stack
        int span = 1;

        if (stack.isEmpty()) {
            stack.push(new int[]{price, 1});
        }
        else {
            while (!stack.isEmpty() && price >= stack.peek()[0]) {
                span += stack.peek()[1];
                stack.pop();
            }

            stack.push(new int[]{price, span});
        }
        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
