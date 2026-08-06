class Solution {
    public int minAddToMakeValid(String s) {
        int count = 0;

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.push('(');
            else {
                if (stack.isEmpty()) count++;
                else {
                    stack.pop();
                }
            }
        }

        return count + stack.size();
    }
}
