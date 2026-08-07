class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int num : asteroids) {
            
            boolean survived = true;

            while (!stack.isEmpty() && num < 0 && stack.peek() > 0) {
                int top = stack.peek();

                if (top < -num) {
                    stack.pop();
                }
                else if (top == -num) {
                    stack.pop();
                    survived = false;
                    break;
                }
                else {
                    survived = false;
                    break;
                }
            }

            if (survived) {
                stack.push(num);
            }
            
        }

        int size = stack.size();
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[size - i - 1] = stack.pop();
        }

        return arr;
    }
}
