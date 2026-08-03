class Solution {
    public int takeCharacters(String s, int k) {
        //Count frequencies
        int[] freq = new int[3];

        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        
        for (int f : freq) {
            if (f < k) return -1;
        }

        //What can i keep
        freq[0] -= k;
        freq[1] -= k;
        freq[2] -= k;

        int[] currWindow = new int[3];
        int left = 0;
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            currWindow[index]++;

            while (left < s.length() && currWindow[index] > freq[index]) {
                currWindow[s.charAt(left) - 'a']--;
                left++;
            }

            max = i - left + 1 > max ? i - left + 1 : max;
        }

        return s.length() - max;
    }
}
