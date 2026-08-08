class Solution {
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        int sign = 1;
        int num = 0;

        for (char c: s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            else if (c == '+') {
                result += sign * num;
                sign = 1;
                num = 0;
            }
            else if (c == '-') {
                result += sign * num;
                sign = -1;
                num = 0;
            }
            else if (c == '(') {
                stack.push(result);
                stack.push(sign);

                result = 0;
                sign = 1;
                num = 0;
            }
            else if (c == ')') {
                result += sign * num;

                int savedSign = stack.pop();
                int savedResult = stack.pop();

                result = savedResult + savedSign * result;
                num = 0;
            }
        }
        result += sign * num;
        return result;
    }
}
