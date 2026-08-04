class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // Need to check if a window contains only characters from s1
        int[] freq = new int[26];

        for (char c : s1.toCharArray()) {
            freq[c - 'a']++;
        }

        int left = 0;
        int needed = s1.length();

        for (int right = 0; right < s2.length(); right++) {
            int idx = s2.charAt(right) - 'a';

            //Look at new item
            if (freq[idx] > 0) {
                //Item is in s2
                needed--;
            }

            freq[idx]--;

            while (left < s2.length() && right - left + 1 > s1.length()) {
                //Shrink window
                idx = s2.charAt(left) - 'a';
                freq[idx]++; //Perform shrink before updating needed

                if (freq[idx] > 0) {
                    needed++;
                }
                left++;
            }

            if (needed == 0 && right - left + 1 == s1.length()) {
                return true;
            }

        }

        return false;

    }
}
