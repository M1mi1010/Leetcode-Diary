class Solution {
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        char operator = ' ';

        for (int i = 0; i < s.length();) {
            char c = s.charAt(i);
            if (c == '*' || c == '/' || c == '-' || c == '+') {
                operator = c;
                i++;
            }
            else if (c == ' ') {i++;}
            else {
                int num = 0;

                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i)- '0');
                    i++;
                }

                if (operator == '*') {
                    stack.push(stack.pop() * num);
                }
                else if (operator == '/') {
                    stack.push(stack.pop() / num);
                }
                else if (operator == '-') {
                    stack.push(num * -1);
                }
                else {
                    stack.push(num);
                }
            }
        }

        int size = stack.size();
        int result = 0;
        for (int i = 0; i < size; i++) {
            result += stack.pop();
        }

        return result;
    }
}
