class Solution {
    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();
        int p = s.length() - 1;
        int skips = 0;

        while (p >= 0) {
            if (s.charAt(p) == '*') {
                skips++;
                p--;
            }
            else if (skips > 0) {
                p--;
                skips--;
            }
            else {
                sb.append(s.charAt(p));
                p--;
            }
        }

        return sb.reverse().toString();
    }
}
