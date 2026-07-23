class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        if (s.length() == 1) return 1;

        int p1 = 0;
        int p2 = 0;
        int max = 0;
        Set<Character> set = new HashSet<>();
        while (p2 < s.length() && p2 >= p1) {
            while (set.contains(s.charAt(p2))) {
                set.remove(s.charAt(p1));
                p1++;
            }

            set.add(s.charAt(p2));

            max = Math.max(max, p2 - p1 + 1);

            p2++;

        }

        return max;

    }
}
