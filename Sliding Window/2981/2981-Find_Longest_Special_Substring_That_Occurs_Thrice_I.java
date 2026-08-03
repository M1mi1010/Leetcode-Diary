class Solution {
    public int maximumLength(String s) {
        Map<String, Integer> map = new HashMap<>();
        int longestString = -1;
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            int p = i;

            while (p < s.length() && s.charAt(i) == s.charAt(p)) {
                p++;

                count = 0;

                //Add the substring to a frequency map
                String substring = s.substring(i, p);
                count = map.getOrDefault(substring, 0) + 1;
                
                map.put(substring, count);
                if (count >= 3 && substring.length() > longestString) longestString = substring.length();
            }
        }
        
        return longestString;
    }
}
