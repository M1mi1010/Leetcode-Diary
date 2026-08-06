class Solution {
    public String minRemoveToMakeValid(String s) {
        int balance = 0;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            result.append(s.charAt(i));

            if (s.charAt(i) == '(') balance++;
            if (s.charAt(i) == ')') {
                balance--;

                if (balance < 0) {
                    result.deleteCharAt(result.length()-1);
                    balance = 0;
                }
            }
        }

        //While there are still extras to remove
        for (int i = result.length() - 1; i >= 0 && balance > 0; i--) {
            if (result.charAt(i) == '(') {
                result.deleteCharAt(i);
                balance--;
            }
        }

        return result.toString();
    }
}
