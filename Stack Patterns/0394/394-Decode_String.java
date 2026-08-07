class Solution {
    public String decodeString(String s) {
        Deque<StringBuilder> stringStack = new ArrayDeque<>();
        Deque<Integer> countStack = new ArrayDeque<>();

        StringBuilder substr = new StringBuilder();
        int count  = 0;

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                count =  count * 10 + (s.charAt(i) - '0');
            }
            else if (s.charAt(i) == '[') {
                stringStack.push(substr);
                countStack.push(count);

                substr = new StringBuilder();
                count = 0;
            }
            else if (Character.isLetter(s.charAt(i))) {
                substr.append(s.charAt(i));
            }
            else if (s.charAt(i) == ']') {
                String currentString = substr.toString();
                substr = stringStack.pop();
                substr.append(currentString.repeat(countStack.pop()));
            }
        }

        return substr.toString();
    }
}
