class Solution {
    public int characterReplacement(String s, int k) {
        int left = 0;
        int max = 0;
        int maxFrequency = 0;
        int[] freq = new int[26];

        for (int right = 0; right < s.length(); right++) {

            int cur = ++freq[s.charAt(right) - 'A'];
            maxFrequency = cur > maxFrequency ? cur : maxFrequency;

            while (left < s.length() && (right - left + 1) - maxFrequency > k) {
                freq[s.charAt(left) - 'A']--;

                left++;
            }

            max = right - left + 1 > max ? right - left + 1 : max;
        }

        return max;
    }
}
