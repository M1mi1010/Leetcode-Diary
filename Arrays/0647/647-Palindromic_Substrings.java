class Solution {
    public int countSubstrings(String s) {
        // Count is always equal to the number 
        int count = 0;
        //Edge case empty string
        if (s.length() == 0) return 0;
        //Edge case one character
        if (s.length() == 1) return 1;

        for (int i = 0; i < s.length(); i++) {
            //Even length substring centered at i
            int left = i;
            int right = i + 1;
            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) == s.charAt(right)) {
                    count++;
                    left--;
                    right++;
                }
                else break;
            }

            //Odd length substring centered at i and i plus 1
            left = i;
            right = i;

            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) == s.charAt(right)) {
                    count++;
                    left--;
                    right++;
                }
                else break;
            }
            
        }
        return count;
    }
}
