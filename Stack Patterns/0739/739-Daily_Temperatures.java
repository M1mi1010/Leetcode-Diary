class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] arr = new int[temperatures.length];
        stack.push(0);

        for (int i = 1; i < temperatures.length; i++) {
            int prev = 0;
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                prev = stack.pop();
                arr[prev] = i - prev;
            }
            stack.push(i);
        }

        return arr;
    }
}
