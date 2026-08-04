class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int[] freq = new int[26];

        for (int i = 0; i < p.length(); i++) {
            freq[p.charAt(i) - 'a']++;
        }

        int left = 0;
        int needed = p.length();
        for (int right = 0; right < s.length(); right++) {

            int index = s.charAt(right) - 'a';
            if (freq[index] > 0) {
                needed--;
            }
            freq[index]--;


            while (left < s.length() && right - left + 1 > p.length()) {
                
                index = s.charAt(left) - 'a';

                freq[index]++;

                if (freq[index] > 0) {
                    needed++;
                }
                left++;
            }

            if (needed == 0) {
                list.add(left);
            }

        }

        return list;
    }
}
