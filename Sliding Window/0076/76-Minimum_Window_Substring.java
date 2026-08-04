class Solution {
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) return "";

        int start = 0;
        int end = 0;
        int minLength = Integer.MAX_VALUE;;
        int left = 0;
        int noMatched = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int right = 0; right < s.length(); right++) {
            if (map.containsKey(s.charAt(right))) {
                int currCount = map.get(s.charAt(right)) - 1;

                if (currCount >= 0) {
                    noMatched++;
                }
                map.put(s.charAt(right), currCount);
            }

            while (noMatched == t.length()) {
                //Update answer
                if (minLength > right - left + 1) {
                    start = left;
                    end = right;
                    minLength = right - left + 1;
                }

                //Shrink
                if (map.containsKey(s.charAt(left))) {
                    int count = map.get(s.charAt(left)) + 1;
                    map.put(s.charAt(left), count);

                    if (count > 0) noMatched--;
                }
                left++;
            
            }

        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(start, end + 1);
    }
}
