class Solution {
    public String longestPalindrome(String s) {
        int start = 0;
        int maxLength = 0;

        for (int i = 0; i < s.length(); i ++) {
            // Even
            int left = i;
            int right = i + 1;

            while (left >= 0 && right < s.length()) { 
                if (s.charAt(left) == s.charAt(right)) {
                    left--;
                    right++;
                    if (right - left - 1 > maxLength) {
                        maxLength = right - left - 1;
                        start = left + 1;
                    }
                }
                else break;
            }

            //Odd
            left = i;
            right = i;

            while (left >= 0 && right < s.length()) { 
                if (s.charAt(left) == s.charAt(right)) {
                    left--;
                    right++;
                    if (right - left - 1 > maxLength) {
                        maxLength = right - left - 1;
                        start = left + 1;
                    }
                }
                else break;
            }
        }

        return s.substring(start, start + maxLength);
    }
}
