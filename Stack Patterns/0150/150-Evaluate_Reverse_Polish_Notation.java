class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("*") ||
                tokens[i].equals("/") ||
                tokens[i].equals("-") ||
                tokens[i].equals("+")) {

                int num2 = stack.pop();
                int num1 = stack.pop();

                switch (tokens[i]) {
                    case "*": stack.push(num1 * num2); break;
                    case "/": stack.push(num1 / num2); break;
                    case "-": stack.push(num1 - num2); break;
                    case "+": stack.push(num1 + num2); break;
                }
            }
            else {
                stack.push(Integer.parseInt(tokens[i]));
            }
        }

        return stack.pop();
    }
}
